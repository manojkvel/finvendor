package com.finvendor.api.fvreport.controller;

import com.finvendor.api.fvreport.dto.UserWatchListData;
import com.finvendor.api.fvreport.service.FvReportService;
import com.finvendor.api.fvreport.service.userwatchlist.UserWatchListService;
import com.finvendor.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/system/api")
public class FvReportController {
    @Autowired
    private FvReportService fvReportService;
    @Autowired
    private UserWatchListService userWatchListService;

    @GetMapping(value = "/sendreports/daily", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendDailyReport() {
        try {
//            EmailUtil.sendMailWithAttachment("jbytrain@gmail.com","TestMail","Hey you have many attachments", new String[]{"d:\\sample.pdf"});
//            EmailUtil.sendMail("jbytrain@gmail.com","TestMail","Hey you have many attachments");
            List<UserWatchListData> userWatchlistData = userWatchListService.findUserWatchlistData();
            for(UserWatchListData uwd:userWatchlistData) {
                fvReportService.sendReport(uwd);
            }
        } catch (Exception e) {

        }

        return null;
    }
}
