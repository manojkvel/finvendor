package com.finvendor.serverwebapi.resources.homepagesearch.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.finvendor.common.util.ExceptionUtil;
import com.finvendor.server.homepagesearch.service.IHomePageSearchService;
import com.finvendor.server.researchreport.dto.filter.EquityResearchFilter;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.homepagesearch.IWebHomePageSearch;
import com.finvendor.serverwebapi.resources.researchreport.impl.WebEquityResearchReportImpl;

@Controller
public class WebHomePageSearchImpl implements IWebHomePageSearch {
	private static Logger logger = LoggerFactory.getLogger(WebEquityResearchReportImpl.class);

	@Autowired
	IHomePageSearchService homePageSearchService;

	//Homepage search
	@Override
	public String getCompanyData(@RequestParam(value = "initialHint", required = true) String initialHint)
			throws WebApiException {
		try {
			String jsonStr = homePageSearchService.getCompanyData(initialHint);
			return jsonStr;
		} catch (Exception e) {
			String apiErrorMessage = ExceptionUtil.buildErrorMessage("Error has occurred in WebHomePageSearch -> getCompanyData(...) method", e);
			logger.error("Web API Error: " + apiErrorMessage);
			throw new WebApiException(apiErrorMessage);
		}
	}
	
	//Tab1 - Summary
	@Override
	public String getCompanyProfileData(@RequestBody EquityResearchFilter filter, @RequestParam(value = "isinCode", required = true) String isinCode) throws WebApiException {
		try {
			String companyProfileData = homePageSearchService.getCompanyProfileData(isinCode,filter);
			return companyProfileData;
		} catch (Exception e) {
			String apiErrorMessage = ExceptionUtil.buildErrorMessage("Error has occurred in WebHomePageSearch -> getCompanyProfileData(...) method", e);
			logger.error("Web API Error: " + apiErrorMessage);
			throw new WebApiException(apiErrorMessage);
		}
	}

	
	//Tab2 Research Report
	@Override
	public String getCompanyRecordStatistics(@RequestParam(value = "isinCode", required = true) String isinCode,
			@RequestBody EquityResearchFilter rrfilter, @RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "perPageMaxRecords", required = true) String perPageMaxRecords)
					throws WebApiException {
		try {
			String companyRecordStatistics = homePageSearchService.getCompanyRecordStatistics(isinCode, rrfilter, perPageMaxRecords);
			return companyRecordStatistics;
		} catch (Exception e) {
			String apiErrorMessage = ExceptionUtil.buildErrorMessage("Error has occurred in WebHomePageSearch -> getCompanyRecordStatistics(...) method", e);
			logger.error("Web API Error: " + apiErrorMessage);
			throw new WebApiException(apiErrorMessage);
		}
	}
	
	//Tab2 Research Report
	@Override
	public String getCompanyResearchReportData(@RequestParam(value = "isinCode", required = true) String isinCode,
			@RequestBody EquityResearchFilter filter,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "pageNumber", required = true) String pageNumber,
			@RequestParam(value = "perPageMaxRecords", required = true) String perPageMaxRecords,
			@RequestParam(value = "sortBy", required = true) String sortBy,
			@RequestParam(value = "orderBy", required = true) String orderBy)
			throws WebApiException {
		try {
			String companyResearchReportData = homePageSearchService.getCompanyResearchReportData(isinCode, filter, pageNumber, perPageMaxRecords, sortBy, orderBy);
			return companyResearchReportData;
		} catch (Exception e) {
			String apiErrorMessage = ExceptionUtil.buildErrorMessage("Error has occurred in WebHomePageSearch -> getResearchReportDataTab(...) method", e);
			logger.error("Web API Error: " + apiErrorMessage);
			throw new WebApiException(apiErrorMessage);
		}
	}

//	// Tab3
//	@Override
//	public String getTechnicalDataTab() throws WebApiException {
//		return null;
//	}
//
//	// Tab4
//	@Override
//	public String getCalendarDataTab() throws WebApiException {
//		return null;
//	}
//
//	// Tab5
//	@Override
//	public String getShareHoldingDataTab() throws WebApiException {
//		return null;
//	}
	

}
