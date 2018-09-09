package com.finvendor.serverwebapi.resources.companyprofile.companyprofile;

import com.finvendor.common.util.ErrorUtil;
import com.finvendor.server.companyprofile.companyprofile.service.ICompanyProfileService;
import com.finvendor.serverwebapi.exception.WebApiException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import static com.finvendor.common.exception.ExceptionEnum.*;

/**
 * @author ayush on April 30, 2018
 */
@Controller
public class WebCompanyProfileImpl implements IWebCompanyProfile {
    private static final Logger logger = LogManager.getLogger(WebCompanyProfileImpl.class.getName());

    @Autowired
    ICompanyProfileService service;

    // Tab1 - Profile + Summary
    @Override
    public ResponseEntity<?> getCompanyProfile(@RequestParam(value = "isinCode", required = true) final String isinCode)
            throws WebApiException {
        try {
            final String companyProfileData = service.getCompanyProfile(isinCode);
            return new ResponseEntity<String>(companyProfileData, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("IWebCompanyProfile -> getCompanyProfile(...) method", e);
            return ErrorUtil.getError(COMPANY_PROFILE.getCode(), COMPANY_PROFILE.getUserMessage(), e);
        }
    }

    // Tab2 Research Report
    @Override
    public ResponseEntity<?> getCompanyProfileRecordStats(
            @RequestParam(value = "isinCode", required = true) String isinCode,
            @RequestParam(value = "perPageMaxRecords", required = true) String perPageMaxRecords)
            throws WebApiException {
        try {
            final String companyProfileRecordStat = service.getCompanyProfileRecordStat(isinCode, perPageMaxRecords);
            return new ResponseEntity<String>(companyProfileRecordStat, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("IWebCompanyProfile -> getCompanyProfileRecordStats(...) method", e);
            return ErrorUtil.getError(COMPANY_RECORD_STATS.getCode(), COMPANY_RECORD_STATS.getUserMessage(), e);
        }
    }

    // Tab2 Research Report
    @Override
    public ResponseEntity<?> getCompanyProfileResearchReport(
            @RequestParam(value = "isinCode", required = true) String isinCode,
            @RequestParam(value = "pageNumber", required = true) String pageNumber,
            @RequestParam(value = "perPageMaxRecords", required = true) String perPageMaxRecords,
            @RequestParam(value = "sortBy", required = true) String sortBy,
            @RequestParam(value = "orderBy", required = true) String orderBy) throws WebApiException {
        try {
            final String companyResearchReportData = service.getCompanyProfileResearchReport(isinCode, pageNumber,
                    perPageMaxRecords, sortBy, orderBy);
            return new ResponseEntity<String>(companyResearchReportData, HttpStatus.OK);
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
