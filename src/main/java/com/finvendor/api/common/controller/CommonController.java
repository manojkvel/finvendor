package com.finvendor.api.common.controller;

import com.finvendor.api.common.dto.StockReturnDto;
import com.finvendor.api.common.service.CommonService;
import com.finvendor.api.exception.WebApiException;
import com.finvendor.api.subscription.dto.SubscriptionDto;
import com.finvendor.api.subscription.enums.SubscriptionTypeEnum;
import com.finvendor.api.subscription.service.SubscriptionService;
import com.finvendor.api.user.service.UserService;
import com.finvendor.api.webutil.WebUtils;
import com.finvendor.api.webutil.WebUtils.SqlData;
import com.finvendor.common.commondao.ICommonDao;
import com.finvendor.common.enums.ApiMessageEnum;
import com.finvendor.common.enums.SqlEnum;
import com.finvendor.common.response.ApiResponse;
import com.finvendor.common.util.ErrorUtil;
import com.finvendor.common.util.JsonUtil;
import com.finvendor.model.FinVendorUser;
import com.finvendor.modelpojo.staticpojo.admindashboard.ResearchReportFor;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.finvendor.api.webutil.WebUtils.buildResponse;
import static com.finvendor.api.webutil.WebUtils.buildResponseEntity;
import static com.finvendor.common.exception.ExceptionEnum.*;

/**
 * @author ayush on Feb 17, 2018
 */
@Controller
@RequestMapping(value = "/api")
public class CommonController {

    private static final Logger logger = LoggerFactory.getLogger(CommonController.class.getName());

    private final ICommonDao commonDao;

    private final CommonService commonService;

    private final UserService userService;
    private final SubscriptionService subscriptionService;
    private static Map<String, String> filterDataMap = new HashMap<>();

    @Autowired
    public CommonController(ICommonDao commonDao,
            CommonService commonService, UserService userService, SubscriptionService subscriptionService) {
        this.commonDao = commonDao;
        this.commonService = commonService;
        this.userService = userService;
        this.subscriptionService = subscriptionService;
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/researchreportfor")
    public ResponseEntity<?> getResearchReportFor(@RequestParam("researchAreaId") String researchAreaId) {
        try {
            List<ResearchReportFor> researchReportFor = new ArrayList<>();
            if ("7".equals(researchAreaId)) {
                researchReportFor = commonDao.getCompanyDetails(SqlEnum.VO_COMPANY_DETAILS.valueOf(), researchAreaId);
            }
            else if ("2".equals(researchAreaId)) {
                String INDUSTRY_SUB_TYPE_NAMES = "select a.id,trim(a.industry_sub_type_name) name from industry_sub_type a where a.rsch_area_id=2 order by trim(a.industry_sub_type_name)";
                researchReportFor = commonDao.getIndustrySubTypes(INDUSTRY_SUB_TYPE_NAMES, null);
            }
            return new ResponseEntity<>(researchReportFor, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("WebApiCommon -> getResearchReportFor(...) method", e);
            return ErrorUtil.getError(COMPANY_DETAILS.getCode(), COMPANY_DETAILS.getUserMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/filterdata")
    public ResponseEntity<?> getResearchFilterData(@RequestParam("type") String type) {
        try {
            switch (type) {
            case "researchDate":
                return new ResponseEntity<>(WebUtils.EQUITY_RESEARCH_FILTER_VALUE_RESEARCH_DATE_JSON, HttpStatus.OK);
            case "stockReturn":
                return new ResponseEntity<>(WebUtils.EQUITY_RESEARCH_FILTER_VALUE_STOCK_RETURN_JSON, HttpStatus.OK);
            case "others":
                return new ResponseEntity<>(WebUtils.EQUITY_RESEARCH_FILTER_VALUE_OTHERS_JSON, HttpStatus.OK);
            case "upside":
                return new ResponseEntity<>(WebUtils.EQUITY_RESEARCH_FILTER_VALUE_UPSIDE_JSON, HttpStatus.OK);
            case "analystType":
                return new ResponseEntity<>(WebUtils.EQUITY_RESEARCH_FILTER_VALUE_ANALYST_TYPE_JSON, HttpStatus.OK);
            case "marketcapital":
                return new ResponseEntity<>(WebUtils.EQUITY_RESEARCH_FILTER_VALUE_MCAP_JSON, HttpStatus.OK);
            }
            String jsonResult = filterDataMap.get(type);
            if (jsonResult == null) {
                final SqlData sqlData = WebUtils.filterTypeMap.get(type);
                jsonResult = commonDao.runSql(sqlData.getSql(), sqlData.getColumnNameAndNewValueMap(),
                        sqlData.getConitionValue(), sqlData.getFirstDefaultParamsMap(), sqlData.getLastDefaultParamsMap(),
                        sqlData.getColIndex());
                filterDataMap.put(type, jsonResult);
            }
            return new ResponseEntity<>(jsonResult, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("WebApiCommon -> getResearchFilterData(...) method", e);
            return ErrorUtil.getError(EQUITY_RESEARCH_FILTER.getCode(), EQUITY_RESEARCH_FILTER.getUserMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/analystTypes")
    public ResponseEntity<?> getAnalystType() {
        try {
            final SQLQuery nativeQuery = commonDao
                    .getNativeQuery("SELECT distinct analystType FROM vendor where analystType is not null", null);
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
                }
                else {
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

    @Transactional(readOnly = false)
    @PutMapping(value = "/analystTypes/{analystType}")
    public ResponseEntity<?> updateAnalystType(HttpServletRequest request, @PathVariable("analystType") String analystType)
            throws WebApiException {
        try {
            final User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
            if (loggedInUser == null) {
                return ErrorUtil.getError(FIND_USER_FROM_SESSION.getCode(), FIND_USER_FROM_SESSION.getUserMessage());
            }
            final String userName = loggedInUser.getUsername();
            final FinVendorUser userDetailsByName = userService.getUserDetailsByUsername(userName);
            final String vendorId = userDetailsByName.getVendor().getId();
            final SQLQuery nativeQuery = commonDao
                    .getNativeQuery("update vendor set analystType=? where vendor_id=?", new String[] { analystType, vendorId });
            final int update = nativeQuery.executeUpdate();

            if (update == 1) {
                return new ResponseEntity<>("{\"staus\":\"AnalystType updated successfully\"}", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("{\"staus\":\"Unable to updated AnalystType\"}", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            ErrorUtil.logError("WebApiCommon -> updateAnalystyType(...) method", e);
            return ErrorUtil.getError(REQUEST_UPDATE_ANALYST_TYPE.getCode(), REQUEST_UPDATE_ANALYST_TYPE.getUserMessage(), e);
        }
    }

    @GetMapping("/insert/vo")
    public ResponseEntity<?> insertVo() {
        try {
            commonService.insertVo();
            return new ResponseEntity<>("Done", HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("WebApiCommon -> insertVo(...) method", e);
            return ErrorUtil.getError(REQUEST_UPDATE_ANALYST_TYPE.getCode(), REQUEST_UPDATE_ANALYST_TYPE.getUserMessage(), e);
        }
    }

    @PostMapping(value = "/stockReturns")
    public ResponseEntity<ApiResponse<String, Map<String, String>>> findStockReturn(@RequestBody StockReturnDto stockReturnDto)
            throws Exception {
        logger.info("## CONTROLLER findStockReturn - START stockReturnDto: {}", stockReturnDto);
        Map<String, String> stockReturns = commonService.findStockReturns(stockReturnDto);
        ApiResponse<String, Map<String, String>> apiResponse = buildResponse(ApiMessageEnum.SUCCESS, stockReturns, HttpStatus.OK);
        logger.info("## CONTROLLER findStockReturn - END");
        return buildResponseEntity(apiResponse);
    }

    @PostMapping(value = "/users/defaultSubscriptionUpdate")
    public ResponseEntity<ApiResponse<String, String>> addFreeSubscriptionToExistingUsers() throws Exception {
        List<FinVendorUser> userDetails = userService.getUserDetails();
        for (FinVendorUser user : userDetails) {
            String userName = user.getUserName();
            SubscriptionDto subscriptionDto = new SubscriptionDto();
            subscriptionDto.setSubscriptionType(SubscriptionTypeEnum.FREE.name());
            subscriptionService.saveUserSubscription(userName, subscriptionDto);
        }

        ApiResponse<String, String> apiResponse = buildResponse(ApiMessageEnum.SUCCESS, null, HttpStatus.OK);
        return buildResponseEntity(apiResponse);
    }
}
