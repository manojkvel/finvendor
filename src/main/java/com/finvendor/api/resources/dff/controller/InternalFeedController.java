package com.finvendor.api.resources.dff.controller;

import com.finvendor.api.exception.WebApiException;
import com.finvendor.api.resources.dff.enums.FeedTypeEnum;
import com.finvendor.api.resources.dff.service.InternalFeedFacade;
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

import static com.finvendor.common.exception.ExceptionEnum.DATA_FEED;

@RestController
@RequestMapping(value = "/system/api")
public class InternalFeedController {
    private static final Logger logger = LoggerFactory.getLogger(InternalFeedController.class.getName());

    @Autowired
    private InternalFeedFacade iff;

    /**
     * Feed Data based on type
     * type: news|corpaction|companycal
     */
    @GetMapping(value = "/internaldatafeeds")
    public ResponseEntity<?> feedInternalData(@RequestParam(value = "type") FeedTypeEnum type) throws WebApiException {
        try {
            logger.info("feedInternalData, type: {}", type.name());
            switch (type) {
            case KENNETH:
                iff.processAndFeed_KennethFisherStrategy();
                break;
            case BENJAMIN:
                iff.processAndFeed_BenjaminGrahamStrategy();
                break;
            }
            return new ResponseEntity<>("Data feed done for type:" + type + " successfully.", HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("Error has occurred while performing data feed for type: " + type, e);
            return ErrorUtil.getError(DATA_FEED.getCode(), DATA_FEED.getUserMessage(), e);
        }
    }
}
