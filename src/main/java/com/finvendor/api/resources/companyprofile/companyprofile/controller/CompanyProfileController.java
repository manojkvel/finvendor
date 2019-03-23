package com.finvendor.api.resources.companyprofile.companyprofile.controller;

import com.finvendor.common.util.ErrorUtil;
import com.finvendor.api.exception.WebApiException;
import com.finvendor.api.resources.WebUriConstants;
import com.finvendor.api.resources.companyprofile.companyprofile.service.CompanyProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.finvendor.common.exception.ExceptionEnum.*;

/**
 * @author ayush on April 30, 2018
 */
@Controller
@RequestMapping(value= WebUriConstants.BASE_URI)
public class CompanyProfileController {
    private static final Logger logger = LoggerFactory.getLogger(CompanyProfileController.class.getName());

    @Autowired
    CompanyProfileService cps;

    // Tab1 - Profile + Summary
    @RequestMapping(value = "/companyprofile", method = RequestMethod.GET)
    public ResponseEntity<?> getCompanyProfile(@RequestParam(value = "isinCode", required = true) final String isinCode)
            throws WebApiException {
        try {
            final String companyProfileData = cps.getCompanyProfile(isinCode);
            return new ResponseEntity<>(companyProfileData, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("IWebCompanyProfile -> getCompanyProfile(...) method", e);
            return ErrorUtil.getError(COMPANY_PROFILE.getCode(), COMPANY_PROFILE.getUserMessage(), e);
        }
    }

    // Tab2 Research Report
    @RequestMapping(value = "/companyprofile/recordstat", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCompanyProfileRecordStats(
            @RequestParam(value = "isinCode", required = true) String isinCode,
            @RequestParam(value = "perPageMaxRecords", required = true) String perPageMaxRecords)
            throws WebApiException {
        try {
            final String companyProfileRecordStat = cps.getCompanyProfileRecordStat(isinCode, perPageMaxRecords);
            return new ResponseEntity<>(companyProfileRecordStat, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("IWebCompanyProfile -> getCompanyProfileRecordStats(...) method", e);
            return ErrorUtil.getError(COMPANY_RECORD_STATS.getCode(), COMPANY_RECORD_STATS.getUserMessage(), e);
        }
    }

    // Tab2 Research Report
    @RequestMapping(value = "/companyprofile/researchreport", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCompanyProfileResearchReport(
            @RequestParam(value = "isinCode", required = true) String isinCode,
            @RequestParam(value = "pageNumber", required = true) String pageNumber,
            @RequestParam(value = "perPageMaxRecords", required = true) String perPageMaxRecords,
            @RequestParam(value = "sortBy", required = true) String sortBy,
            @RequestParam(value = "orderBy", required = true) String orderBy) throws WebApiException {
        try {
            final String companyResearchReportData = cps.getCompanyProfileResearchReport(isinCode, pageNumber,
                    perPageMaxRecords, sortBy, orderBy);
            return new ResponseEntity<>(companyResearchReportData, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("IWebCompanyProfile -> getCompanyProfileResearchReport(...) method", e);
            return ErrorUtil.getError(COMPANY_RESEARCH_REPORT.getCode(), COMPANY_RESEARCH_REPORT.getUserMessage(), e);
        }
    }

    /**
     * Earning Preview
     */
    @GetMapping(value = "/companyprofile/earningpreview")
    public ResponseEntity<?> findEarningPreview(@RequestParam(value = "type") String type, @RequestParam(value = "isin") String isin) {
        try {
            return new ResponseEntity<>(cps.findEarningPreview(type, isin), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("IWebCompanyProfile -> findEarningPreview(...) method", e);
            return ErrorUtil.getError(COMPANY_PROFILE_EARNING_PREVIEW.getCode(), COMPANY_PROFILE_EARNING_PREVIEW.getUserMessage(), e);
        }
    }

    /**
     * Company News - Record Stats
     */
    @GetMapping(value = "/companyprofile/companynews/recordstat")
    public ResponseEntity<?> findCompanyNewsRecordstat(@RequestParam(value = "ticker") String ticker,
                                             @RequestParam(value = "perPageMaxRecords") String perPageMaxRecords) {
        try {
            return new ResponseEntity<>("{\"firstPageNumber\":1,\"lastPageNumber\":1,\"totalRecords\":4}", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("IWebCompanyProfile -> findCompanyNewsRecordstat(...) method", e);
            return ErrorUtil.getError(COMPANY_PROFILE_COMPANY_NEWS.getCode(), COMPANY_PROFILE_COMPANY_NEWS.getUserMessage(), e);
        }
    }

    /**
     * Company News
     */
    @GetMapping(value = "/companyprofile/companynews")
    public ResponseEntity<?> findCompanyNews(@RequestParam(value = "ticker") String ticker) {
        try {
            return new ResponseEntity<>(cps.findCompanyNews(ticker), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("IWebCompanyProfile -> findCompanyNews(...) method", e);
            return ErrorUtil.getError(COMPANY_PROFILE_COMPANY_NEWS.getCode(), COMPANY_PROFILE_COMPANY_NEWS.getUserMessage(), e);
        }
    }


    /**
     *  Corporate Action - Record Stats
     */
    @GetMapping(value = "/companyprofile/corpaction/recordstat")
    public ResponseEntity<?> findCorporateActionRecordStats(@RequestParam(value = "ticker") String ticker,
                                             @RequestParam(value = "perPageMaxRecords") String perPageMaxRecords) {
        try {
            return new ResponseEntity<>("{\"firstPageNumber\":1,\"lastPageNumber\":1,\"totalRecords\":4}", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("IWebCompanyProfile -> findCorporateActionRecordStats(...) method", e);
            return ErrorUtil.getError(COMPANY_PROFILE_CORP_ACTION.getCode(), COMPANY_PROFILE_CORP_ACTION.getUserMessage(), e);
        }
    }


    /**
     * Corporate Action
     */
    @GetMapping(value = "/companyprofile/corpaction")
    public ResponseEntity<?> findCorporateAction(@RequestParam(value = "ticker") String ticker) {
        try {
            return new ResponseEntity<>(cps.findCorporateAction(ticker), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("IWebCompanyProfile -> findCorporateAction(...) method", e);
            return ErrorUtil.getError(COMPANY_PROFILE_CORP_ACTION.getCode(), COMPANY_PROFILE_CORP_ACTION.getUserMessage(), e);
        }
    }


    /**
     *  Calendar - Record Stats
     */
    @GetMapping(value = "/companyprofile/calendar/recordstat")
    public ResponseEntity<?> findCalendarRecordStats(@RequestParam(value = "ticker") String ticker,
                                                            @RequestParam(value = "perPageMaxRecords") String perPageMaxRecords) {
        try {
            return new ResponseEntity<>("{\"firstPageNumber\":1,\"lastPageNumber\":1,\"totalRecords\":4}", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("IWebCompanyProfile -> findCalendarRecordStats(...) method", e);
            return ErrorUtil.getError(COMPANY_PROFILE_CALENDAR.getCode(), COMPANY_PROFILE_CALENDAR.getUserMessage(), e);
        }
    }

    /**
     * Calendar
     */
    @GetMapping(value = "/companyprofile/calendar")
    public ResponseEntity<?> findCalendar(@RequestParam(value = "ticker") String ticker) {
        try {
            return new ResponseEntity<>(cps.findCalendar(ticker), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("IWebCompanyProfile -> findCalendar(...) method", e);
            return ErrorUtil.getError(COMPANY_PROFILE_CALENDAR.getCode(), COMPANY_PROFILE_CALENDAR.getUserMessage(), e);
        }
    }

    /**
     *  Price History - Record Stats
     */
    @GetMapping(value = "/companyprofile/pricehistory/recordstat")
    public ResponseEntity<?> findPriceHistoryRecordStats(@RequestParam(value = "isin") String isin,
                                                     @RequestParam(value = "perPageMaxRecords") String perPageMaxRecords) {
        try {
            return new ResponseEntity<>("{\"firstPageNumber\":1,\"lastPageNumber\":1,\"totalRecords\":4}", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("IWebCompanyProfile -> findPriceHistoryRecordStats(...) method", e);
            return ErrorUtil.getError(COMPANY_PROFILE_PRICE_HISTORY.getCode(), COMPANY_PROFILE_PRICE_HISTORY.getUserMessage(), e);
        }
    }

    /**
     * Price History
     */
    @GetMapping(value = "/companyprofile/pricehistory")
    public ResponseEntity<?> findPriceHistory(@RequestParam(value = "isin") String isin) {
        try {
            return new ResponseEntity<>(cps.findPriceHistory(isin), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("IWebCompanyProfile -> findPriceHistory(...) method", e);
            return ErrorUtil.getError(COMPANY_PROFILE_PRICE_HISTORY.getCode(), COMPANY_PROFILE_PRICE_HISTORY.getUserMessage(), e);
        }
    }
}
