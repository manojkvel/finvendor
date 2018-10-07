package com.finvendor.serverwebapi.resources.bhavprice;

import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.WebUriConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(WebUriConstants.BASE_URI)
public interface IWebBhavPrice {
    @RequestMapping(value = "/bhavprices/recordstats", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<?> getBhavPriceRecordStats(String type, String perPageMaxRecords) throws WebApiException;

    @RequestMapping(value = "/bhavprices", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<?> getBhavPrices(String type, String order, String pageNumber, String perPageMaxRecords) throws WebApiException;

    @RequestMapping(value = "/bhavprices/upload", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<?> uploadBhavPrice(HttpServletRequest request, HttpServletResponse response) throws WebApiException;
}
