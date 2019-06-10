package com.finvendor.api.metrics.controller;

import com.finvendor.common.util.ErrorUtil;
import com.finvendor.common.util.Pair;
import com.finvendor.modelpojo.staticpojo.StatusPojo;
import com.finvendor.api.exception.WebApiException;
import com.finvendor.api.metrics.service.MetricService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

import static com.finvendor.common.exception.ExceptionEnum.*;

@Controller
@RequestMapping(value = "/system/api")
public class MetricsController {
    private static final Logger logger = LoggerFactory.getLogger(MetricsController.class.getName());
    @Autowired
    private MetricService metricsService;

    @RequestMapping(value = "/metrics", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMetrics(@RequestParam(value = "type") String type) throws WebApiException {
        try {
            final String requestMetrics = metricsService.getRequestMetrics(type);
            return new ResponseEntity<>(requestMetrics, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while getting metrics, error - ", e);
            return ErrorUtil.getError(REQUEST_METRICS.getCode(), REQUEST_METRICS.getUserMessage(), e);
        }
    }

    @RequestMapping(value = "/consumeranalytics/recordstats", method = RequestMethod.GET)
    public ResponseEntity<?> getConsumerAnalyticsRecordStats(@RequestParam(value = "type") String type,
                                                             @RequestParam(value = "subType") String subType,
                                                             @RequestParam(value = "perPageMaxRecords") String perPageMaxRecords,
                                                             @RequestParam(value = "breachFlag") String breachFlag) throws WebApiException {
        try {
            if (!("equity".equals(type))) {
                throw new Exception("type QueryParam should be equity");
            }

            if (!("rf".equals(subType) || "d".equals(subType))) {
                throw new Exception("subType QueryParam should be either rf|d");
            }

            if (breachFlag != null && !("y".equals(breachFlag) || "n".equals(breachFlag) || "all".equals(breachFlag))) {
                throw new Exception("breachFlag value should be y|n|all");
            }
            String consumerAnalyticsRecordStats = metricsService.getConsumerAnalyticsRecordStats(type, subType, perPageMaxRecords, breachFlag);

            return new ResponseEntity<>(consumerAnalyticsRecordStats, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while getting ConsumerAnalytics, error - ", e);
            return ErrorUtil.getError(CONSUMER_ANALYTICS_EQTY_RESEARCH.getCode(), CONSUMER_ANALYTICS_EQTY_RESEARCH.getUserMessage(), e);
        }
    }

    @RequestMapping(value = "/consumeranalytics", method = RequestMethod.GET)
    public ResponseEntity<?> getConsumerAnalytics(@RequestParam(value = "type") String type,
                                                  @RequestParam(value = "subType") String subType,
                                                  @RequestParam(value = "pageNumber") String pageNumber,
                                                  @RequestParam(value = "perPageMaxRecords") String perPageMaxRecords,
                                                  @RequestParam(value = "breachFlag") String breachFlag) throws WebApiException {
        try {
            if (!("equity".equals(type))) {
                throw new Exception("type QueryParam should be equity");
            }

            if (!("rf".equals(subType) || "d".equals(subType))) {
                throw new Exception("subType QueryParam should be either rf|d");
            }

            if (breachFlag != null && !("y".equals(breachFlag) || "n".equals(breachFlag) || "all".equals(breachFlag))) {
                throw new Exception("breachFlag value should be y|n|all");
            }

            String consumerAnalytics = metricsService.getConsumerAnalytics(type, subType, pageNumber, perPageMaxRecords, breachFlag);
            return new ResponseEntity<>(consumerAnalytics, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error has occurred while getting ConsumerAnalytics, error - ", e);
            return ErrorUtil.getError(CONSUMER_ANALYTICS_EQTY_RESEARCH.getCode(), CONSUMER_ANALYTICS_EQTY_RESEARCH.getUserMessage(), e);
        }
    }

    @RequestMapping(value = "/consumeranalytics/download", method = RequestMethod.GET)
    public ResponseEntity<?> downloadConsumerAnalytics(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "type") String type,
                                                       @RequestParam(value = "subType") String subType) throws WebApiException {
        try {
            if (!("equity".equals(type))) {
                throw new Exception("type QueryParam should be equity");
            }

            if (!("rf".equals(subType) || "d".equals(subType))) {
                throw new Exception("subType QueryParam should be either rf|d");
            }

            final Pair<Long, InputStream> download = metricsService.downloadConsumerAnalytics(type, subType);
            if (download == null || download.getElement2() == null) {
                throw new Exception("Unable to download Customer Analytics Report");
            }
            response.setHeader("Content-Disposition", "attachment; filename=" + "ConsumerAnalyticsReport.csv");
            response.setHeader("Content-Length", String.valueOf(download.getElement1()));
            FileCopyUtils.copy(download.getElement2(), response.getOutputStream());
            final StatusPojo statusPojo = new StatusPojo("true", "Consumer Analytics report downloaded successfully.");
            return new ResponseEntity<>(statusPojo, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("WebMetricsImpl -> downloadConsumerAnalytics(...) method", e);
            return ErrorUtil.getError(CONSUMER_ANALYTICS_DOWNLOAD.getCode(), CONSUMER_ANALYTICS_DOWNLOAD.getUserMessage(), e);
        }
    }
}