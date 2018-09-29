package com.finvendor.serverwebapi.resources.metrics.impl;

import com.finvendor.common.util.ErrorUtil;
import com.finvendor.server.metrics.service.MetricService;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.metrics.IWebMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import static com.finvendor.common.exception.ExceptionEnum.CONSUMER_ANALYTICS_EQTY_RESEARCH;
import static com.finvendor.common.exception.ExceptionEnum.REQUEST_METRICS;

@Controller
public class WebMetricsImpl implements IWebMetrics {
    private static final Logger logger = LoggerFactory.getLogger(WebMetricsImpl.class.getName());
    @Autowired
    private MetricService metricsService;

    @Override
    public ResponseEntity<?> getAllMetrics(@RequestParam(value = "type") String type) throws WebApiException {
        try {
            final String requestMetrics = metricsService.getRequestMetrics(type);
            return new ResponseEntity<>(requestMetrics, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while getting metrics, error - ", e);
            return ErrorUtil.getError(REQUEST_METRICS.getCode(), REQUEST_METRICS.getUserMessage(), e);
        }
    }

    @Override
    public ResponseEntity<?> getYearMetrics(@RequestParam(value = "type") String type,
                                            @RequestParam(value = "year") String year) throws WebApiException {
        return null;
    }

    @Override
    public ResponseEntity<?> getMonthMetrics(@RequestParam(value = "type") String type,
                                             @RequestParam(value = "year") String year,
                                             @RequestParam(value = "month") String month) throws WebApiException {
        return null;
    }

    @Override
    public ResponseEntity<?> getDayMetrics(@RequestParam(value = "type") String type,
                                           @RequestParam(value = "year") String year,
                                           @RequestParam(value = "month") String month,
                                           @RequestParam(value = "day") String day) throws WebApiException {
        return null;
    }

    @Override
    public ResponseEntity<?> getConsumerAnalyticsRecordStats(@RequestParam(value = "type") String type,@RequestParam(value = "perPageMaxRecords") String perPageMaxRecords) throws WebApiException {
        try {
            String consumerAnalyticsRecordStats = metricsService.getConsumerAnalyticsRecordStats(type, perPageMaxRecords);
            return new ResponseEntity<>(consumerAnalyticsRecordStats, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while getting ConsumerAnalytics, error - ", e);
            return ErrorUtil.getError(CONSUMER_ANALYTICS_EQTY_RESEARCH.getCode(), CONSUMER_ANALYTICS_EQTY_RESEARCH.getUserMessage(), e);
        }
    }

    @Override
    public ResponseEntity<?> getConsumerAnalytics(@RequestParam(value = "type") String type,
                                                  @RequestParam(value = "pageNumber") String pageNumber,
                                                  @RequestParam(value = "perPageMaxRecords") String perPageMaxRecords) throws WebApiException {
        try {
            String consumerAnalytics = metricsService.getConsumerAnalytics(type, pageNumber, perPageMaxRecords);
            return new ResponseEntity<>(consumerAnalytics, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while getting ConsumerAnalytics, error - ", e);
            return ErrorUtil.getError(CONSUMER_ANALYTICS_EQTY_RESEARCH.getCode(), CONSUMER_ANALYTICS_EQTY_RESEARCH.getUserMessage(), e);
        }
    }
}
