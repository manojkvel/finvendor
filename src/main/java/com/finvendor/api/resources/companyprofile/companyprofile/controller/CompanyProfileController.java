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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.finvendor.common.exception.ExceptionEnum.*;

/**
 * @author ayush on April 30, 2018
 */
@Controller
@RequestMapping(WebUriConstants.BASE_URI)
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

    // // Tab3
    // @Override
    // public String getTechnicalDataTab() throws WebApiException {
    // return null;
    // }
    //
    // // Tab4
    // @Override
    // public String getCalendarDataTab() throws WebApiException {
    // return null;
    // }
    //
    // // Tab5
    // @Override
    // public String getShareHoldingDataTab() throws WebApiException {
    // return null;
    // }

}
