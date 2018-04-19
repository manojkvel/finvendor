package com.finvendor.serverwebapi.resources.homepagesearch;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finvendor.server.researchreport.dto.filter.EquityResearchFilter;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.WebUriConstants;

@RequestMapping(WebUriConstants.BASE_URI)
public interface IWebHomePageSearch {

	@RequestMapping(value = WebUriConstants.HomePageSearch.COMPANY_DATA_URI, method = RequestMethod.GET)
	@ResponseBody
	String getCompanyData(String companyName) throws WebApiException;
	

	@RequestMapping(value = WebUriConstants.HomePageSearch.COMPANY_PROFILE_DATA_URI, method = RequestMethod.POST)
	@ResponseBody
	String getCompanyProfileData(EquityResearchFilter rrfilter, String isinCode) throws WebApiException;
	
	@RequestMapping(value = WebUriConstants.HomePageSearch.COMPANY_RECORD_STATS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	String getCompanyRecordStatistics(String isinCode, EquityResearchFilter equityResearchFilter, String type, String perPageMaxRecords) throws WebApiException;

	@RequestMapping(value = WebUriConstants.HomePageSearch.COMPANY_RESEARCH_REPORT, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	String getCompanyResearchReportData(String isinCode, EquityResearchFilter equityResearchFilter,
			String type, String pageNumber, String perPageMaxRecords, String sortBy, String orderBy) throws WebApiException;

//	String getTechnicalDataTab() throws WebApiException;
//
//	String getCalendarDataTab() throws WebApiException;
//
//	String getShareHoldingDataTab() throws WebApiException;
}
