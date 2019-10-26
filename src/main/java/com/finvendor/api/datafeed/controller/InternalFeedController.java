package com.finvendor.api.datafeed.controller;

import com.finvendor.api.datafeed.enums.FeedTypeEnum;
import com.finvendor.api.datafeed.service.internalfeed.screener.ScreenerFeedFacade;
import com.finvendor.api.exception.WebApiException;
import com.finvendor.common.util.ErrorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import static com.finvendor.common.exception.ExceptionEnum.DATA_FEED;

@ApiIgnore
@RestController
@RequestMapping(value = "/api")
public class InternalFeedController {
    private static final Logger logger = LoggerFactory.getLogger(InternalFeedController.class.getName());

    @Autowired
    @Qualifier(value = "screenerFeedFacade")
    private ScreenerFeedFacade screenerFeedFacade;

    /**
     * Feed Data based on type
     */
    @GetMapping(value = "/internaldatafeeds/screeners")
    public ResponseEntity<?> feedInternalData(@RequestParam(value = "type") FeedTypeEnum feedTypeEnum) throws WebApiException {
        try {
                logger.info("feedInternalData, type: {}", feedTypeEnum.name());
            screenerFeedFacade.processAndFeed(feedTypeEnum);
            return new ResponseEntity<>("Data feed done for type: " + feedTypeEnum.name() + " successfully.", HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("Error has occurred while performing data feed for type: " + feedTypeEnum.name(), e);
            return ErrorUtil.getError(DATA_FEED.getCode(), DATA_FEED.getUserMessage(), e);
        }
    }
}
