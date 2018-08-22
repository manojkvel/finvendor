package com.finvendor.serverwebapi.resources.common.impl;

import java.util.*;

import com.finvendor.common.util.JsonUtil;
import com.finvendor.model.FinVendorUser;
import com.finvendor.service.UserService;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.common.enums.SqlEnum;
import com.finvendor.common.util.ErrorUtil;
import com.finvendor.modelpojo.staticpojo.admindashboard.CompanyDetails;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.common.IWebCommon;
import com.finvendor.serverwebapi.webutil.WebUtil;
import com.finvendor.serverwebapi.webutil.WebUtil.SqlData;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.finvendor.common.exception.ExceptionEnum.*;

/**
 * @author ayush on Feb 17, 2018
 */
@Controller
public class WebCommonImpl implements IWebCommon {

    @Autowired
    private ICommonDao commonDao;

    @Resource(name = "userService")
    private UserService userService;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> getCompanyDetails(String researchAreaId) throws WebApiException {
        try {
            Set<CompanyDetails> companyDetails = commonDao.getCompanyDetails(SqlEnum.VO_COMPANY_DETAILS.valueOf(),
                    researchAreaId);
            return new ResponseEntity<>(companyDetails, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("WebApiCommon -> getCompanyDetails(...) method", e);
            return ErrorUtil.getError(COMPANY_DETAILS.getCode(), COMPANY_DETAILS.getUserMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> getResearchFilterData(String type) throws WebApiException {
        try {
            switch (type) {
                case "brokerYrOfInCorp":
                    return new ResponseEntity<String>(WebUtil.EQUITY_RESEARCH_FILTER_VALUE_BROKER_YR_OF_IN_CORP_JSON, HttpStatus.OK);
                case "brokerRank":
                    return new ResponseEntity<String>(WebUtil.EQUITY_RESEARCH_FILTER_VALUE_BROKER_RANK_JSON, HttpStatus.OK);
                case "others":
                    return new ResponseEntity<String>(WebUtil.EQUITY_RESEARCH_FILTER_VALUE_OTHERS_JSON, HttpStatus.OK);
                case "upside":
                    return new ResponseEntity<String>(WebUtil.EQUITY_RESEARCH_FILTER_VALUE_UPSIDE_JSON, HttpStatus.OK);
                case "analystType":
                    return new ResponseEntity<>(WebUtil.EQUITY_RESEARCH_FILTER_VALUE_ANALYST_TYPE_JSON, HttpStatus.OK);
            }

            SqlData sqlData = WebUtil.typeMap.get(type);
            String jsonResult = commonDao.runSql(sqlData.getSql(), sqlData.getColumnNameAndNewValueMap(),
                    sqlData.getConitionValue(), sqlData.getFirstDefaultParamsMap(), sqlData.getLastDefaultParamsMap(),
                    sqlData.getColIndex());
            return new ResponseEntity<String>(jsonResult, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("WebApiCommon -> getResearchFilterData(...) method", e);
            return ErrorUtil.getError(EQUITY_RESEARCH_FILTER.getCode(), EQUITY_RESEARCH_FILTER.getUserMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> getAnalystType() throws WebApiException {
        try {
            SQLQuery nativeQuery = commonDao.getNativeQuery("SELECT distinct analystType FROM vendor", null);
            List<Object> rows = (List<Object>) nativeQuery.list();
            Map<String, Object> paramsMap = new LinkedHashMap<>();
            List<String> analystTypePojoList = new ArrayList<>();
            Iterator<Object> itr = rows.iterator();
            while (itr.hasNext()) {
                Object array = itr.next();
                if (array instanceof String) {
                    String v = (String) array;
                    String analystType = v != null ? v : "";
                    analystTypePojoList.add(analystType);
                }
            }
            paramsMap.put("analystType", analystTypePojoList);
            String jsonFromParamsMap = JsonUtil.createJsonFromParamsMap(paramsMap);
            return new ResponseEntity<>(jsonFromParamsMap, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("WebApiCommon -> getAnalystType(...) method", e);
            return ErrorUtil.getError(REQUEST_ANALYST_TYPE.getCode(), REQUEST_ANALYST_TYPE.getUserMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<?> updateAnalystyType(HttpServletRequest request, String analystType) throws WebApiException {
        try {
            User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
            if (loggedInUser == null) {
                return ErrorUtil.getError(FIND_USER_FROM_SESSION.getCode(), FIND_USER_FROM_SESSION.getUserMessage());
            }
            String userName = loggedInUser.getUsername();
            FinVendorUser userDetailsByUsername = userService.getUserDetailsByUsername(userName);
            String vendorId = userDetailsByUsername.getVendor().getId();
            SQLQuery nativeQuery = commonDao.getNativeQuery("update vendor set analystType=? where vendor_id=?", new String[]{analystType, vendorId});
            int update = nativeQuery.executeUpdate();

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
}
