package com.finvendor.serverwebapi.resources.metrics;

import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.WebUriConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(WebUriConstants.BASE_URI)
public interface IWebMetrics {

    @RequestMapping(value = "/metrics", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<?> getAllMetrics(String type) throws WebApiException;

    @RequestMapping(value = "/yermetrics", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<?> getYearMetrics(String type, String year) throws WebApiException;

    @RequestMapping(value = "/yearlmonthmetrics", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<?> getMonthMetrics(String type, String year, String month) throws WebApiException;

    @RequestMapping(value = "/yearmonthdaymetrics", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<?> getDayMetrics(String type, String year, String month, String day) throws WebApiException;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //
    // ~~~~~~~        Consumer Analytics API     ~~~~~~~~
    //
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @RequestMapping(value = "/consumeranalytics/recordstats", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<?> getConsumerAnalyticsRecordStats(String type, String subType, String perPageMaxRecords, String breachFlag) throws WebApiException;

    @RequestMapping(value = "/consumeranalytics", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<?> getConsumerAnalytics(String type, String subType, String pageNumber, String perPageMaxRecords, String breachFlag) throws WebApiException;

    @RequestMapping(value = "/consumeranalytics/download", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<?> downloadConsumerAnalytics(HttpServletRequest request, HttpServletResponse response, String type, String subType) throws WebApiException;
}