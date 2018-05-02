package com.finvendor.serverwebapi.resources.profile.companyprofile.impl;

import static com.finvendor.common.exception.ExceptionEnum.COMPANY_PROFILE;
import static com.finvendor.common.exception.ExceptionEnum.COMPANY_RECORD_STATS;
import static com.finvendor.common.exception.ExceptionEnum.COMPANY_RESEARCH_REPORT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.finvendor.common.util.ErrorUtil;
import com.finvendor.server.profile.companyprofile.service.ICompanyProfileService;
import com.finvendor.server.researchreport.dto.filter.impl.EquityResearchFilter;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.profile.companyprofile.IWebCompnayProfile;

/**
 * @author ayush on April 30, 2018
 */
@Controller
public class WebCompanyProfileImpl implements IWebCompnayProfile {
	@Autowired
	ICompanyProfileService compnayProfileService;

	// Tab1 - Profile + Summary
	@Override
	public ResponseEntity<?> getCompanyProfile(@RequestBody EquityResearchFilter filter,
			@RequestParam(value = "q", required = true) String q) throws WebApiException {
		try {
			String companyProfileData = compnayProfileService.getProfile(q, filter);
			return new ResponseEntity<String>(companyProfileData, HttpStatus.OK);
		} catch (Exception e) {
			ErrorUtil.logError("WebCompnayProfile -> getCompanyProfile(...) method", e);
			return ErrorUtil.getError(COMPANY_PROFILE.getCode(), COMPANY_PROFILE.getUserMessage(), e);
		}
	}

	// Tab2 Research Report
	@Override
	public ResponseEntity<?> getCompanyResearchReportRecordStatistics(@RequestBody EquityResearchFilter filter,
			@RequestParam(value = "isinCode", required = true) String isinCode,
			@RequestParam(value = "perPageMaxRecords", required = true) String perPageMaxRecords)
					throws WebApiException {
		try {
			String companyRecordStatistics = compnayProfileService.getResearchReportRecordStatistics(isinCode, filter,
					perPageMaxRecords);
			return new ResponseEntity<String>(companyRecordStatistics, HttpStatus.OK);
		} catch (Exception e) {
			ErrorUtil.logError("WebCompnayProfile -> getCompanyResearchReportRecordStatistics(...) method", e);
			return ErrorUtil.getError(COMPANY_RECORD_STATS.getCode(), COMPANY_RECORD_STATS.getUserMessage(), e);
		}
	}

	// Tab2 Research Report
	@Override
	public ResponseEntity<?> getCompanyResearchReport(@RequestBody EquityResearchFilter filter,
			@RequestParam(value = "isinCode", required = true) String isinCode,
			@RequestParam(value = "pageNumber", required = true) String pageNumber,
			@RequestParam(value = "perPageMaxRecords", required = true) String perPageMaxRecords,
			@RequestParam(value = "sortBy", required = true) String sortBy,
			@RequestParam(value = "orderBy", required = true) String orderBy) throws WebApiException {
		try {
			String companyResearchReportData = compnayProfileService.getResearchReport(isinCode, filter, pageNumber,
					perPageMaxRecords, sortBy, orderBy);
			return new ResponseEntity<String>(companyResearchReportData, HttpStatus.OK);
		} catch (Exception e) {
			ErrorUtil.logError("WebCompnayProfile -> getCompanyResearchReport(...) method", e);
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
