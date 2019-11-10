package com.finvendor.api.report.controller;

import com.finvendor.api.report.service.ReportService;
import com.finvendor.common.util.ErrorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.finvendor.common.exception.ExceptionEnum.REPORT;

@RestController
@RequestMapping(value = "/api")
public class ReportController {
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class.getName());

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PutMapping(value = "/users/{userId}/stock-reports", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendDailyReport(@PathVariable String userId) {
        logger.info("sendDailyReport - Controller START, userId: {}", userId);
        try {
            reportService.sendReports();
            logger.info("## Report sent successfully to FREE|SMART|SAGE users ");
            return new ResponseEntity<>("Report sent successfully", HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("FvReportController -> sendDailyReport(...) method", e);
            return ErrorUtil.getError(REPORT.getCode(), REPORT.getUserMessage(), e);
        }
    }
}
