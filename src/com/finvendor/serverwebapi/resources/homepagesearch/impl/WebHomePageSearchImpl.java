package com.finvendor.serverwebapi.resources.homepagesearch.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.finvendor.common.util.ExceptionUtil;
import com.finvendor.server.homepagesearch.dto.staticpojo.CompanyWatchListPojo;
import com.finvendor.server.homepagesearch.service.IHomePageStockSearchService;
import com.finvendor.server.researchreport.dto.filter.impl.EquityResearchFilter;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.homepagesearch.IWebStockSearch;
import com.finvendor.serverwebapi.resources.researchreport.impl.WebEquityResearchReportImpl;

@Controller
public class WebHomePageSearchImpl implements IWebStockSearch {
	private static Logger logger = LoggerFactory.getLogger(WebEquityResearchReportImpl.class);

	@Autowired
	IHomePageStockSearchService homePageSearchService;

	//Homepage search
	@Override
	public String getCompanyHomePageSearchData(@RequestBody EquityResearchFilter filter, @RequestParam(value = "q", required = true) String q)
			throws WebApiException {
		try {
			String jsonStr = homePageSearchService.getHomePageSearchData(q, filter);
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

	@Override
	public String addCompanyWatchList(@RequestBody CompanyWatchListPojo companyWatchListPojo) throws WebApiException {
		try {
			int result = homePageSearchService.addCompanyWatchList(companyWatchListPojo);
			System.out.println("R="+result);
			if(result==1) {
				
			}
			return "{\"starus\":\"Company watch list added successfully\"}";
		} catch (Exception e) {
			String apiErrorMessage = ExceptionUtil.buildErrorMessage("Error has occurred in WebHomePageSearch -> addCompanyWatchList(...) method", e);
			logger.error("Web API Error: " + apiErrorMessage);
			throw new WebApiException(apiErrorMessage);
		}
	}
	
	@Override
	public List<CompanyWatchListPojo> findAllcompanywatchlist(@RequestParam(value = "userName", required = true) String userName) throws WebApiException {
		try {
			return homePageSearchService.findAllCompanyWatchListByUser(userName);
		} catch (Exception e) {
			String apiErrorMessage = ExceptionUtil.buildErrorMessage("Error has occurred in WebHomePageSearch -> findAllcompanywatchlist(...) method", e);
			logger.error("Web API Error: " + apiErrorMessage);
			throw new WebApiException(apiErrorMessage);
		}
	}
	
	//Tab2 Research Report
	@Override
	public String getCompanyRecordStatistics(@RequestParam(value = "isinCode", required = true) String isinCode,
			@RequestBody EquityResearchFilter filter, @RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "perPageMaxRecords", required = true) String perPageMaxRecords)
					throws WebApiException {
		try {
			String companyRecordStatistics = homePageSearchService.getCompanyRecordStatistics(isinCode, filter, perPageMaxRecords);
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
			String apiErrorMessage = ExceptionUtil.buildErrorMessage("Error has occurred in WebHomePageSearch -> getCompanyResearchReportData(...) method", e);
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
