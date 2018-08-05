package com.finvendor.serverwebapi.resources.companyprofile.companyprofile;

import static com.finvendor.common.exception.ExceptionEnum.COMPANY_PROFILE;
import static com.finvendor.common.exception.ExceptionEnum.COMPANY_RECORD_STATS;
import static com.finvendor.common.exception.ExceptionEnum.COMPANY_RESEARCH_REPORT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.finvendor.common.util.ErrorUtil;
import com.finvendor.server.companyprofile.companyprofile.service.ICompanyProfileService;
import com.finvendor.serverwebapi.exception.WebApiException;

/**
 * @author ayush on April 30, 2018
 */
@Controller
public class WebCompanyProfileImpl implements IWebCompanyProfile {
	@Autowired
	ICompanyProfileService service;

	// Tab1 - Profile + Summary
	@Override
	public ResponseEntity<?> getCompanyProfile(@RequestParam(value = "isinCode", required = true) String isinCode)
			throws WebApiException {
		try {
			String companyProfileData = service.getCompanyProfile(isinCode);
			return new ResponseEntity<String>(companyProfileData, HttpStatus.OK);
		} catch (Exception e) {
			ErrorUtil.logError("IWebCompanyProfile -> getCompanyProfile(...) method", e);
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
			String companyProfileRecordStat = service.getCompanyProfileRecordStat(isinCode, perPageMaxRecords);
			return new ResponseEntity<String>(companyProfileRecordStat, HttpStatus.OK);
		} catch (Exception e) {
			ErrorUtil.logError("IWebCompanyProfile -> getCompanyProfileRecordStats(...) method", e);
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
			String companyResearchReportData = service.getCompanyProfileResearchReport(isinCode, pageNumber,
					perPageMaxRecords, sortBy, orderBy);
			return new ResponseEntity<String>(companyResearchReportData, HttpStatus.OK);
		} catch (Exception e) {
			ErrorUtil.logError("IWebCompanyProfile -> getCompanyProfileResearchReport(...) method", e);
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
