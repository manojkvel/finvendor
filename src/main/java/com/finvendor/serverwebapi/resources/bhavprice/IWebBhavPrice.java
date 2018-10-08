package com.finvendor.serverwebapi.resources.bhavprice;

import com.finvendor.server.bhavprice.dto.BhavPriceFilter;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.WebUriConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(WebUriConstants.BASE_URI)
public interface IWebBhavPrice {
    @RequestMapping(value = "/bhavprices/recordstats", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<?> getBhavPriceRecordStats(String type, String perPageMaxRecords, BhavPriceFilter bhavPriceFilter) throws WebApiException;

    @RequestMapping(value = "/bhavprices", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<?> getBhavPrices(String type, String order, String pageNumber, String perPageMaxRecords, BhavPriceFilter bhavPriceFilter) throws WebApiException;

    @RequestMapping(value = "/bhavprices/persist", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<?> persistFileToDb() throws WebApiException;

}
