package com.finvendor.api.resources.datafeed.controller;

import com.finvendor.api.exception.WebApiException;
import com.finvendor.api.resources.WebUriConstants;
import com.finvendor.common.infra.dff.DataFeedService;
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

import javax.annotation.Resource;
import java.util.Properties;

import static com.finvendor.common.exception.ExceptionEnum.DATA_FEED;

@RestController
@RequestMapping(value = WebUriConstants.BASE_URI)
public class DataFeedController {
    private static final Logger logger = LoggerFactory.getLogger(DataFeedController.class.getName());

    @Resource(name = "finvendorProperties")
    private Properties finvendorProperties;

    @Autowired
    @Qualifier(value = "newsFeed")
    private DataFeedService newsFeed;

    @Autowired
    @Qualifier(value = "CAFeed")
    private DataFeedService coprActionFeed;


    @Autowired
    @Qualifier(value = "calFeed")
    private DataFeedService calendarFeed;

    private static final String COMPANY_NEWS_URL = "https://www.nseindia.com/corporates/datafiles/AN_LATEST_ANNOUNCED.csv";
    private static final String CORP_ACTION_URL = "https://www.nseindia.com/corporates/datafiles/CA_ALL_FORTHCOMING.csv";
    private static final String COMPANY_CALENDAR_URL = "https://www.nseindia.com/corporates/datafiles/BM_All_Forthcoming.csv";

    /**
     * Feed Data based on type
     * type: news|corpaction|companycal
     */
    @GetMapping(value = "/datafeed")
    public ResponseEntity<?> feedData(@RequestParam(value = "type") String type) throws WebApiException {
        try {
            logger.info("persistCompanyNews-> COMPANY_NEWS_URL:{}", COMPANY_NEWS_URL);
            String downloadPath = finvendorProperties.getProperty("finvendo_tmp_path");

            switch (type) {
                case "news":
                    newsFeed.download(COMPANY_NEWS_URL, downloadPath).feed(downloadPath);
                    break;
                case "ca":
                    coprActionFeed.download(CORP_ACTION_URL, downloadPath).feed(downloadPath);
                    break;
                case "cal":
                    calendarFeed.download(COMPANY_CALENDAR_URL, downloadPath).feed(downloadPath);
                    break;
            }
            return new ResponseEntity<>("Data feed process done for type:" + type + " successfully.", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while performing data feed for type: {}, error - {}", type, e);
            return ErrorUtil.getError(DATA_FEED.getCode(), DATA_FEED.getUserMessage(), e);
        }
    }
}
