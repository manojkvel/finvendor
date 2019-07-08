package com.finvendor.api.fvreport.controller;

import com.finvendor.api.fvreport.service.FvReportService;
import com.finvendor.common.util.ErrorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.finvendor.common.exception.ExceptionEnum.FV_REPORT;

@RestController
@RequestMapping(value = "/system/api")
public class FvReportController {
    private static final Logger logger = LoggerFactory.getLogger(FvReportController.class.getName());

    @Autowired
    private FvReportService fvReportService;

//    @Autowired
//    private ReportUserService reportUserService;

    @GetMapping(value = "/reports", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendDailyReport() {
        try {

//            List<ReportUser> reportUsers = userService.findAllUsers();
//            for(ReportUser allUser: reportUsers) {
//                fvReportService.sendReport(allUser.getUserName());
//            }
            fvReportService.sendReport("ays_broker");
            return new ResponseEntity<>("Report sent successfully to each user", HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("FvReportController -> sendDailyReport(...) method", e);
            return ErrorUtil.getError(FV_REPORT.getCode(), FV_REPORT.getUserMessage(), e);
        }
    }
}
