package com.finvendor.api.datafeed.controller;

import com.finvendor.api.datafeed.enums.FeedTypeEnum;
import com.finvendor.api.datafeed.service.externalfeed.companyprofile.CompanyProfileDataFeedFacade;
import com.finvendor.api.datafeed.service.externalfeed.earningpreview.EarningPreviewDataFeedFacade;
import com.finvendor.api.exception.WebApiException;
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
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.Properties;

import static com.finvendor.common.exception.ExceptionEnum.DATA_FEED;

@ApiIgnore
@RestController
@RequestMapping(value = "/system/api")
public class ExternalFeedController {
    private static final Logger logger = LoggerFactory.getLogger(ExternalFeedController.class.getName());
    private static final String EARNING_PREVIEW_DATA__FOLDER_PATH = "";
    @Resource(name = "finvendorProperties")
    private Properties finvendorProperties;

    @Autowired
    private CompanyProfileDataFeedFacade companyProfileDataFeedFacade;

    @Autowired
    private EarningPreviewDataFeedFacade earningPreviewDataFeedFacade;

    /**
     * Feed Company profile data based on type : COMPANY_NEWS | CORP_ACTION | COMPANY_CALENDAR
     */
    @GetMapping(value = "/externaldatafeeds")
    public ResponseEntity<?> feedCompanyProfileData(@RequestParam(value = "type") FeedTypeEnum type) throws WebApiException {
        logger.info("feedCompanyProfileData, type:{}", type.name());
        try {
            String downloadPath = finvendorProperties.getProperty("finvendo_tmp_path");
            switch (type) {
            case COMPANY_NEWS:
                companyProfileDataFeedFacade.feedNewsData(downloadPath);
                break;
            case COPR_ACTION:
                companyProfileDataFeedFacade.feedCorpActionData(downloadPath);
                break;
            case COMPANY_CALENDAR:
                companyProfileDataFeedFacade.feedCalendarData(downloadPath);
                break;
            }
            return new ResponseEntity<>("Company profile data feed done for type:" + type + " successfully.", HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError(
                    "ExternalFeedController -> feedExternalData() :: Error has occurred while performing Company profile data feed for type: "
                            + type, e);
            return ErrorUtil.getError(DATA_FEED.getCode(), DATA_FEED.getUserMessage(), e);
        }
    }

    @GetMapping(value = "/externaldatafeeds/earningpreviews")
    public ResponseEntity<?> feedEarningPreviewData() throws WebApiException {
        try {
            earningPreviewDataFeedFacade.feedEarningPreviewData(EARNING_PREVIEW_DATA__FOLDER_PATH);
        }catch (Exception e){
            ErrorUtil.logError(
                    "ExternalFeedController -> feedEarningPreviewData() :: Error has occurred while performing Earning Preview  data feed", e);
            return ErrorUtil.getError(DATA_FEED.getCode(), DATA_FEED.getUserMessage(), e);
        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
