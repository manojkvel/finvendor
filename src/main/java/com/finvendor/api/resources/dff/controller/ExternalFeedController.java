package com.finvendor.api.resources.dff.controller;

import com.finvendor.api.exception.WebApiException;
import com.finvendor.api.resources.dff.enums.FeedTypeEnum;
import com.finvendor.api.resources.dff.service.externalfeed.ExternalFeedFacade;
import com.finvendor.common.util.ErrorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Properties;

import static com.finvendor.common.exception.ExceptionEnum.DATA_FEED;

@RestController
@RequestMapping(value = "/system/api")
public class ExternalFeedController {
    private static final Logger logger = LoggerFactory.getLogger(ExternalFeedController.class.getName());

    @Resource(name = "finvendorProperties")
    private Properties finvendorProperties;

    @Autowired
    private ExternalFeedFacade externalFeedFacade;

    /**
     * Feed Data based on type
     */
    @GetMapping(value = "/externaldatafeeds")
    public ResponseEntity<?> feedExternalData(@RequestParam(value = "type") FeedTypeEnum type) throws WebApiException {
        logger.info("feedExternalData, type:{}", type);
        try {
            String downloadPath = finvendorProperties.getProperty("finvendo_tmp_path");
            switch (type) {
            case COMPANY_NEWS:
                externalFeedFacade.newsDownload(downloadPath);
                externalFeedFacade.newsFeed(downloadPath);
                break;
            case COPR_ACTION:
                externalFeedFacade.caDownload(downloadPath);
                externalFeedFacade.caFeed(downloadPath);
                break;
            case COMPANY_CALENDAR:
                externalFeedFacade.calDownload(downloadPath);
                externalFeedFacade.calFeed(downloadPath);
                break;
            }
            return new ResponseEntity<>("Data feed done for type:" + type + " successfully.", HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("ExternalFeedController -> feedExternalData() :: Error has occurred while performing data feed for type: " + type, e);
            return ErrorUtil.getError(DATA_FEED.getCode(), DATA_FEED.getUserMessage(), e);
        }
    }
}
