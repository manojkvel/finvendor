package com.finvendor.serverwebapi.resources.common.impl;

import com.finvendor.common.enums.SqlEnum;
import com.finvendor.common.util.ErrorUtil;
import com.finvendor.common.util.JsonUtil;
import com.finvendor.model.FinVendorUser;
import com.finvendor.modelpojo.staticpojo.admindashboard.ResearchReportFor;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.common.IWebCommon;
import com.finvendor.serverwebapi.webutil.WebUtil;
import com.finvendor.serverwebapi.webutil.WebUtil.SqlData;
import com.finvendor.service.UserService;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.finvendor.common.exception.ExceptionEnum.*;

/**
 * @author ayush on Feb 17, 2018
 */
@Controller
public class WebCommonImpl implements IWebCommon {

    private static final Logger logger = LoggerFactory.getLogger(WebCommonImpl.class.getName());

    @Autowired
    private ICommonDao commonDao;

    @Resource(name = "userService")
    private UserService userService;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> getResearchReportFor(final String researchAreaId) {
        try {
            List<ResearchReportFor> researchReportFor = new ArrayList<>();
            if ("7".equals(researchAreaId)) {
                researchReportFor = commonDao.getCompanyDetails(SqlEnum.VO_COMPANY_DETAILS.valueOf(),
                        researchAreaId);
            } else if ("2".equals(researchAreaId)) {
                String INDUSTRY_SUB_TYPE_NAMES = "select c.id,trim(c.industry_sub_type_name) from research_area a, research_sub_area b, industry_sub_type c where a.research_area_id=b.research_area_id and c.rsch_sub_area_id=b.research_sub_area_id and  b.research_area_id=? order by trim(c.industry_sub_type_name) asc";
                researchReportFor = commonDao.getIndustrySubTypes(INDUSTRY_SUB_TYPE_NAMES, new String[]{researchAreaId});
            }
            return new ResponseEntity<>(researchReportFor, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("WebApiCommon -> getResearchReportFor(...) method", e);
            return ErrorUtil.getError(COMPANY_DETAILS.getCode(), COMPANY_DETAILS.getUserMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> getResearchFilterData(final String type) {
        try {
            switch (type) {
                case "researchDate":
                    return new ResponseEntity<>(WebUtil.EQUITY_RESEARCH_FILTER_VALUE_RESEARCH_DATE_JSON, HttpStatus.OK);
                case "brokerRank":
                    return new ResponseEntity<>(WebUtil.EQUITY_RESEARCH_FILTER_VALUE_BROKER_RANK_JSON, HttpStatus.OK);
                case "others":
                    return new ResponseEntity<>(WebUtil.EQUITY_RESEARCH_FILTER_VALUE_OTHERS_JSON, HttpStatus.OK);
                case "upside":
                    return new ResponseEntity<>(WebUtil.EQUITY_RESEARCH_FILTER_VALUE_UPSIDE_JSON, HttpStatus.OK);
                case "analystType":
                    return new ResponseEntity<>(WebUtil.EQUITY_RESEARCH_FILTER_VALUE_ANALYST_TYPE_JSON, HttpStatus.OK);
                case "marketcapital":
                    return new ResponseEntity<>(WebUtil.EQUITY_RESEARCH_FILTER_VALUE_MCAP_JSON, HttpStatus.OK);
            }
            final SqlData sqlData = WebUtil.filterTypeMap.get(type);
            final String jsonResult = commonDao.runSql(sqlData.getSql(), sqlData.getColumnNameAndNewValueMap(),
                    sqlData.getConitionValue(), sqlData.getFirstDefaultParamsMap(), sqlData.getLastDefaultParamsMap(),
                    sqlData.getColIndex());
            return new ResponseEntity<>(jsonResult, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("WebApiCommon -> getResearchFilterData(...) method", e);
            return ErrorUtil.getError(EQUITY_RESEARCH_FILTER.getCode(), EQUITY_RESEARCH_FILTER.getUserMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> getAnalystType() {
        try {
            final SQLQuery nativeQuery = commonDao.getNativeQuery("SELECT distinct analystType FROM vendor where analystType is not null", null);
            final List<Object> rows = (List<Object>) nativeQuery.list();
            final Map<String, Object> paramsMap = new LinkedHashMap<>();
            final List<String> analystTypePojos = new ArrayList<>();
            final Iterator<Object> itr = rows.iterator();
            while (itr.hasNext()) {
                final Object array = itr.next();
                if (array instanceof String) {
                    final String analystTypeStr = (String) array;
                    final String analystType = analystTypeStr != null ? analystTypeStr : "";
                    analystTypePojos.add(analystType);
                }
            }

            if (analystTypePojos.size() == 1) {
                if ("Independent Research Analyst".equals(analystTypePojos.get(0))) {
                    analystTypePojos.add("Research Broker");
                } else {
                    analystTypePojos.add("Independent Research Analyst");
                }
            }

            paramsMap.put("analystType", analystTypePojos);
            final String jsonFromParamsMap = JsonUtil.createJsonFromParamsMap(paramsMap);
            return new ResponseEntity<>(jsonFromParamsMap, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("WebApiCommon -> getAnalystType(...) method", e);
            return ErrorUtil.getError(REQUEST_ANALYST_TYPE.getCode(), REQUEST_ANALYST_TYPE.getUserMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<?> updateAnalystyType(final HttpServletRequest request, final String analystType) throws WebApiException {
        try {
            final User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
            if (loggedInUser == null) {
                return ErrorUtil.getError(FIND_USER_FROM_SESSION.getCode(), FIND_USER_FROM_SESSION.getUserMessage());
            }
            final String userName = loggedInUser.getUsername();
            final FinVendorUser userDetailsByName = userService.getUserDetailsByUsername(userName);
            final String vendorId = userDetailsByName.getVendor().getId();
            final SQLQuery nativeQuery = commonDao.getNativeQuery("update vendor set analystType=? where vendor_id=?", new String[]{analystType, vendorId});
            final int update = nativeQuery.executeUpdate();

            if (update == 1) {
                return new ResponseEntity<>("{\"staus\":\"AnalystType updated successfully\"}", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("{\"staus\":\"Unable to updated AnalystType\"}", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            ErrorUtil.logError("WebApiCommon -> updateAnalystyType(...) method", e);
            return ErrorUtil.getError(REQUEST_UPDATE_ANALYST_TYPE.getCode(), REQUEST_UPDATE_ANALYST_TYPE.getUserMessage(), e);
        }
    }

    public static void main(String[] args) {
        logger.info("Hello INFO");
        logger.info("Hello ERROR");
    }
}
