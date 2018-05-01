package com.finvendor.serverwebapi.resources.profile.companyprofile.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.finvendor.common.util.ExceptionUtil;
import com.finvendor.server.profile.companyprofile.service.ICompanyProfileService;
import com.finvendor.server.researchreport.dto.filter.impl.EquityResearchFilter;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.profile.companyprofile.IWebCompnayProfile;

/**
 * @author ayush on April 30, 2018
 */
@Controller
public class WebCompanyProfileImpl implements IWebCompnayProfile {
	private static Logger logger = LoggerFactory.getLogger(WebCompanyProfileImpl.class);

	@Autowired
	ICompanyProfileService compnayProfileService;

	// Tab1 - Profile + Summary
	@Override
	public String getCompanyProfile(@RequestBody EquityResearchFilter filter,
			@RequestParam(value = "q", required = true) String q) throws WebApiException {
		try {
			String companyProfileData = compnayProfileService.getProfile(q, filter);
			return companyProfileData;
		} catch (Exception e) {
			String apiErrorMessage = ExceptionUtil.buildErrorMessage(
					"Error has occurred in WebHomePageSearch -> getCompanyProfileData(...) method", e);
			logger.error("Web API Error: " + apiErrorMessage);
			throw new WebApiException(apiErrorMessage);
		}
	}

	// Tab2 Research Report
	@Override
	public String getCompanyResearchReportRecordStatistics(@RequestBody EquityResearchFilter filter,
			@RequestParam(value = "isinCode", required = true) String isinCode,
			@RequestParam(value = "perPageMaxRecords", required = true) String perPageMaxRecords)
					throws WebApiException {
		try {
			String companyRecordStatistics = compnayProfileService.getResearchReportRecordStatistics(isinCode, filter,
					perPageMaxRecords);
			return companyRecordStatistics;
		} catch (Exception e) {
			String apiErrorMessage = ExceptionUtil.buildErrorMessage(
					"Error has occurred in WebHomePageSearch -> getCompanyRecordStatistics(...) method", e);
			logger.error("Web API Error: " + apiErrorMessage);
			throw new WebApiException(apiErrorMessage);
		}
	}

	// Tab2 Research Report
	@Override
	public String getCompanyResearchReport(@RequestBody EquityResearchFilter filter,
			@RequestParam(value = "isinCode", required = true) String isinCode,
			@RequestParam(value = "pageNumber", required = true) String pageNumber,
			@RequestParam(value = "perPageMaxRecords", required = true) String perPageMaxRecords,
			@RequestParam(value = "sortBy", required = true) String sortBy,
			@RequestParam(value = "orderBy", required = true) String orderBy) throws WebApiException {
		try {
			String companyResearchReportData = compnayProfileService.getResearchReport(isinCode, filter, pageNumber,
					perPageMaxRecords, sortBy, orderBy);
			return companyResearchReportData;
		} catch (Exception e) {
			String apiErrorMessage = ExceptionUtil.buildErrorMessage(
					"Error has occurred in WebHomePageSearch -> getCompanyResearchReportData(...) method", e);
			logger.error("Web API Error: " + apiErrorMessage);
			throw new WebApiException(apiErrorMessage);
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
