//package com.finvendor.serverwebapi.resources.researchreport;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.finvendor.server.researchreport.dto.filter.impl.EquityResearchFilter;
//import com.finvendor.serverwebapi.exception.WebApiException;
//import com.finvendor.serverwebapi.resources.WebUriConstants;
//
///**
// * @author ayush
// * @since 03-Feb-2018
// */
//@RequestMapping(WebUriConstants.BASE_URI)
//public interface IWebResearchReport {
//
//    /**
//     * @param type
//     * @return
//     * @throws WebApiException
//     */
//    @RequestMapping(value = WebUriConstants.ResearchReport.RESEARCH_REPORTS, method = RequestMethod.POST)
//    @ResponseBody
//    ResponseEntity<?> getResearchResultTableData(EquityResearchFilter equityResearchFilter,
//                                                 String type, String pageNumber, String perPageMaxRecords, String sortBy, String orderBy) throws WebApiException;
//
//    /**
//     * @param type
//     * @param productId
//     * @return
//     * @throws WebApiException
//     */
//    @RequestMapping(value = WebUriConstants.ResearchReport.DASHBOARD_RESEARCH_REPORTS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    ResponseEntity<?> getResearchResultDashboardData(EquityResearchFilter equityResearchFilter,
//                                                     String type, String ofPage, String perPageMaxRecords, String sortBy, String orderBy, String productId) throws WebApiException;
//
//    /**
//     * @param reportName
//     * @return
//     * @throws WebApiException
//     */
//    @RequestMapping(value = WebUriConstants.ResearchReport.DOWNLOAD_RESEARCH_REPORTS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    ResponseEntity<?> downloadResearchReport(HttpServletRequest request, HttpServletResponse response, String productId, String reportName) throws WebApiException;
//
//    /**
//     * @return
//     * @throws WebApiException
//     */
//    @RequestMapping(value = WebUriConstants.ResearchReport.RECORD_STATS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    ResponseEntity<?> getRecordStatistics(EquityResearchFilter equityResearchFilter, String type, String perPageMaxRecords) throws WebApiException;
//
//}
