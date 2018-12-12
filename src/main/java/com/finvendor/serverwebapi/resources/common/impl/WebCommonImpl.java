//package com.finvendor.serverwebapi.resources.common.impl;
//
//import com.finvendor.common.enums.SqlEnum;
//import com.finvendor.common.util.ErrorUtil;
//import com.finvendor.common.util.JsonUtil;
//import com.finvendor.model.FinVendorUser;
//import com.finvendor.modelpojo.staticpojo.admindashboard.ResearchReportFor;
//import com.finvendor.server.common.commondao.ICommonDao;
//import com.finvendor.serverwebapi.exception.WebApiException;
//import com.finvendor.serverwebapi.resources.WebUriConstants;
//import com.finvendor.serverwebapi.resources.homepage.dto.CompnyData;
//import com.finvendor.serverwebapi.webutil.WebUtil;
//import com.finvendor.serverwebapi.webutil.WebUtil.SqlData;
//import com.finvendor.service.UserService;
//import org.apache.commons.lang.StringUtils;
//import org.hibernate.SQLQuery;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.util.*;
//
//import static com.finvendor.common.exception.ExceptionEnum.*;
//
///**
// * @author ayush on Feb 17, 2018
// */
//@Controller
//@RequestMapping(value = WebUriConstants.BASE_URI)
//public class WebCommonImpl {
//
//    private static final Logger logger = LoggerFactory.getLogger(WebCommonImpl.class.getName());
//
//    @Autowired
//    private ICommonDao commonDao;
//
//    @Resource(name = "userService")
//    private UserService userService;
//
//    @Transactional(readOnly = true)
//    @GetMapping(value = "/researchreportfor")
//    public ResponseEntity<?> getResearchReportFor(@RequestParam("researchAreaId") String researchAreaId) {
//        try {
//            List<ResearchReportFor> researchReportFor = new ArrayList<>();
//            if ("7".equals(researchAreaId)) {
//                researchReportFor = commonDao.getCompanyDetails(SqlEnum.VO_COMPANY_DETAILS.valueOf(),
//                        researchAreaId);
//            } else if ("2".equals(researchAreaId)) {
//                String INDUSTRY_SUB_TYPE_NAMES = "select c.id,trim(c.industry_sub_type_name) from research_area a, research_sub_area b, industry_sub_type c where a.research_area_id=b.research_area_id and c.rsch_sub_area_id=b.research_sub_area_id and  b.research_area_id=? order by trim(c.industry_sub_type_name) asc";
//                researchReportFor = commonDao.getIndustrySubTypes(INDUSTRY_SUB_TYPE_NAMES, new String[]{researchAreaId});
//            }
//            return new ResponseEntity<>(researchReportFor, HttpStatus.OK);
//        } catch (Exception e) {
//            ErrorUtil.logError("WebApiCommon -> getResearchReportFor(...) method", e);
//            return ErrorUtil.getError(COMPANY_DETAILS.getCode(), COMPANY_DETAILS.getUserMessage(), e);
//        }
//    }
//
//
//    @Transactional(readOnly = true)
//    @GetMapping(value = "/filterdata")
//    public ResponseEntity<?> getResearchFilterData(@RequestParam("type") String type) {
//        try {
//            switch (type) {
//                case "researchDate":
//                    return new ResponseEntity<>(WebUtil.EQUITY_RESEARCH_FILTER_VALUE_RESEARCH_DATE_JSON, HttpStatus.OK);
//                case "brokerRank":
//                    return new ResponseEntity<>(WebUtil.EQUITY_RESEARCH_FILTER_VALUE_BROKER_RANK_JSON, HttpStatus.OK);
//                case "others":
//                    return new ResponseEntity<>(WebUtil.EQUITY_RESEARCH_FILTER_VALUE_OTHERS_JSON, HttpStatus.OK);
//                case "upside":
//                    return new ResponseEntity<>(WebUtil.EQUITY_RESEARCH_FILTER_VALUE_UPSIDE_JSON, HttpStatus.OK);
//                case "analystType":
//                    return new ResponseEntity<>(WebUtil.EQUITY_RESEARCH_FILTER_VALUE_ANALYST_TYPE_JSON, HttpStatus.OK);
//                case "marketcapital":
//                    return new ResponseEntity<>(WebUtil.EQUITY_RESEARCH_FILTER_VALUE_MCAP_JSON, HttpStatus.OK);
//            }
//            final SqlData sqlData = WebUtil.filterTypeMap.get(type);
//            final String jsonResult = commonDao.runSql(sqlData.getSql(), sqlData.getColumnNameAndNewValueMap(),
//                    sqlData.getConitionValue(), sqlData.getFirstDefaultParamsMap(), sqlData.getLastDefaultParamsMap(),
//                    sqlData.getColIndex());
//            return new ResponseEntity<>(jsonResult, HttpStatus.OK);
//        } catch (Exception e) {
//            ErrorUtil.logError("WebApiCommon -> getResearchFilterData(...) method", e);
//            return ErrorUtil.getError(EQUITY_RESEARCH_FILTER.getCode(), EQUITY_RESEARCH_FILTER.getUserMessage(), e);
//        }
//    }
//
//    @Transactional(readOnly = true)
//    @GetMapping(value = "/analystTypes")
//    public ResponseEntity<?> getAnalystType() {
//        try {
//            final SQLQuery nativeQuery = commonDao.getNativeQuery("SELECT distinct analystType FROM vendor where analystType is not null", null);
//            final List<Object> rows = (List<Object>) nativeQuery.list();
//            final Map<String, Object> paramsMap = new LinkedHashMap<>();
//            final List<String> analystTypePojos = new ArrayList<>();
//            final Iterator<Object> itr = rows.iterator();
//            while (itr.hasNext()) {
//                final Object array = itr.next();
//                if (array instanceof String) {
//                    final String analystTypeStr = (String) array;
//                    final String analystType = analystTypeStr != null ? analystTypeStr : "";
//                    analystTypePojos.add(analystType);
//                }
//            }
//
//            if (analystTypePojos.size() == 1) {
//                if ("Independent Research Analyst".equals(analystTypePojos.get(0))) {
//                    analystTypePojos.add("Research Broker");
//                } else {
//                    analystTypePojos.add("Independent Research Analyst");
//                }
//            }
//
//            paramsMap.put("analystType", analystTypePojos);
//            final String jsonFromParamsMap = JsonUtil.createJsonFromParamsMap(paramsMap);
//            return new ResponseEntity<>(jsonFromParamsMap, HttpStatus.OK);
//        } catch (Exception e) {
//            ErrorUtil.logError("WebApiCommon -> getAnalystType(...) method", e);
//            return ErrorUtil.getError(REQUEST_ANALYST_TYPE.getCode(), REQUEST_ANALYST_TYPE.getUserMessage(), e);
//        }
//    }
//
//
//    @Transactional(readOnly = false)
//    @PutMapping(value = "/analystTypes/{analystType}")
//    public ResponseEntity<?> updateAnalystyType(HttpServletRequest request, @PathVariable("analystType") String analystType) throws WebApiException {
//        try {
//            final User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
//            if (loggedInUser == null) {
//                return ErrorUtil.getError(FIND_USER_FROM_SESSION.getCode(), FIND_USER_FROM_SESSION.getUserMessage());
//            }
//            final String userName = loggedInUser.getUsername();
//            final FinVendorUser userDetailsByName = userService.getUserDetailsByUsername(userName);
//            final String vendorId = userDetailsByName.getVendor().getId();
//            final SQLQuery nativeQuery = commonDao.getNativeQuery("update vendor set analystType=? where vendor_id=?", new String[]{analystType, vendorId});
//            final int update = nativeQuery.executeUpdate();
//
//            if (update == 1) {
//                return new ResponseEntity<>("{\"staus\":\"AnalystType updated successfully\"}", HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("{\"staus\":\"Unable to updated AnalystType\"}", HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        } catch (Exception e) {
//            ErrorUtil.logError("WebApiCommon -> updateAnalystyType(...) method", e);
//            return ErrorUtil.getError(REQUEST_UPDATE_ANALYST_TYPE.getCode(), REQUEST_UPDATE_ANALYST_TYPE.getUserMessage(), e);
//        }
//    }
//
//    @GetMapping("/insert/vo")
//    public ResponseEntity<?> insert() {
//        try {
//            String query = "select * from ven_rsrch_rpt_offering a,ven_rsrch_rpt_dtls b,ven_rsrch_rpt_analyst_prof c where a.product_id=b.product_id and b.product_id=c.product_id limit 1 offset 0";
//            SQLQuery sqlQuery = commonDao.getNativeQuery(query, null);
//            List<Object[]> rows = sqlQuery.list();
//            for (Object[] row : rows) {
//                String productId = row[0] != null ? row[0].toString().trim() : "";
//                String productName = row[1] != null ? row[1].toString().trim() : "";
//                String productDescription = row[2] != null ? row[2].toString().trim() : "";
//                String vendorId = row[3] != null ? row[3].toString().trim() : "";
//                String launchedYear = row[4] != null ? row[4].toString().trim() : "";
//                String researchArea = row[5] != null ? row[5].toString().trim() : "";
//                String researchSubArea = row[6] != null ? row[6].toString().trim() : "";
//                String stockFundIssueCovered = row[7] != null ? row[7].toString().trim() : "";
//                String accessibility = row[9] != null ? row[9].toString().trim() : "";
//                String suitability = row[10] != null ? row[10].toString().trim() : "";
//                String subCostPy = row[11] != null ? row[11].toString().trim() : "";
//                String repFormat = row[12] != null ? row[12].toString().trim() : "";
//                String companyId = row[13] != null ? row[13].toString().trim() : "";
//                String repDate = row[14] != null ? row[14].toString().trim() : "";
//                String targetPrice = row[15] != null ? row[15].toString().trim() : "";
//                String rsrchRcommType = row[16] != null ? row[16].toString().trim() : "";
//                String priceAtRecomm = row[17] != null ? row[17].toString().trim() : "";
//                String rsrchReportDesc = row[18] != null ? row[18].toString().trim() : "";
//                String rsrchReportAccess = row[19] != null ? row[19].toString().trim() : "";
//                //String rsrchUploadReport = row[20] != null ? row[20].toString().trim() : "";
//                String reportName = row[21] != null ? row[21].toString().trim() : "";
//                String analystName = row[23] != null ? row[23].toString().trim() : "";
//                String analystAwards = row[24] != null ? row[24].toString().trim() : "";
//                String analystCfaCharter = row[25] != null ? row[25].toString().trim() : "";
//
//
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return new ResponseEntity<>("Done", HttpStatus.OK);
//
//    }
//
//    public static void main(String[] args) {
//        logger.info("Hello INFO");
//        logger.info("Hello ERROR");
//    }
//}
