//package com.finvendor.serverwebapi.resources.markets;
//
//import com.finvendor.serverwebapi.exception.WebApiException;
//import com.finvendor.serverwebapi.resources.WebUriConstants;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@RequestMapping(WebUriConstants.BASE_URI)
//public interface IWebMarkets {
//
//    @RequestMapping(value = "/markets/index/names", method = RequestMethod.GET)
//    @ResponseBody
//    ResponseEntity<?> getIndexNames() throws WebApiException;
//
//    @RequestMapping(value = "/markets/index/summary", method = RequestMethod.GET)
//    @ResponseBody
//    ResponseEntity<?> getIndexSummary(String indexFilter) throws WebApiException;
//
//    @RequestMapping(value = "/markets/analytics", method = RequestMethod.GET)
//    @ResponseBody
//    ResponseEntity<?> getMarketsAnalytics(String indexFilter, String type) throws WebApiException;
//
//    @RequestMapping(value = "/markets/recordstats", method = RequestMethod.GET)
//    @ResponseBody
//    ResponseEntity<?> getMarketsRecordStats(String indexFilter, String perPageMaxRecords) throws WebApiException;
//
//    @RequestMapping(value = "/markets", method = RequestMethod.GET)
//    @ResponseBody
//    ResponseEntity<?> getMarkets(String indexFilter, String type, String pageNumber, String perPageMaxRecords)
//            throws WebApiException;
//
//    /*
//     * Persisting File To DB API
//     *
//     **/
//    @RequestMapping(value = "/markets/persist", method = RequestMethod.GET)
//    @ResponseBody
//    ResponseEntity<?> persistMarkets() throws WebApiException;
//
//    @RequestMapping(value = "/niftyindices/persist", method = RequestMethod.GET)
//    @ResponseBody
//    ResponseEntity<?> persistNiftyIndices() throws WebApiException;
//}
