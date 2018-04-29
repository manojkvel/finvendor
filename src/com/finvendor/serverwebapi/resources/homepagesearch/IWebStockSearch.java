package com.finvendor.serverwebapi.resources.homepagesearch;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finvendor.server.homepagesearch.dto.staticpojo.CompanyWatchListPojo;
import com.finvendor.server.researchreport.dto.filter.impl.EquityResearchFilter;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.WebUriConstants;

@RequestMapping(WebUriConstants.BASE_URI)
public interface IWebStockSearch {

	/**
	 * 
	 * @param companyName
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = WebUriConstants.HomePageSearch.COMPANY_HOME_PAGE_SEARCH_URI, method = RequestMethod.POST)
	@ResponseBody
	String getCompanyHomePageSearchData(EquityResearchFilter filter, String q) throws WebApiException;
	
	/**
	 * 
	 * @param rrfilter
	 * @param isinCode
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = WebUriConstants.HomePageSearch.COMPANY_PROFILE_DATA_URI, method = RequestMethod.POST)
	@ResponseBody
	String getCompanyProfileData(EquityResearchFilter filter, String isinCode) throws WebApiException;
	
	@RequestMapping(value = WebUriConstants.HomePageSearch.COMPANY_WATCHLIST_URI, 
			method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	String addCompanyWatchList(CompanyWatchListPojo companyWatchList) throws WebApiException;
	
	@RequestMapping(value = "/companywatchlists", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	List<CompanyWatchListPojo> findAllcompanywatchlist(String userName) throws WebApiException;
	
	/**
	 * 
	 * @param isinCode
	 * @param equityResearchFilter
	 * @param type
	 * @param perPageMaxRecords
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = WebUriConstants.HomePageSearch.COMPANY_RECORD_STATS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	String getCompanyRecordStatistics(String isinCode, EquityResearchFilter filter, String type, String perPageMaxRecords) throws WebApiException;

	/**
	 * 
	 * @param isinCode
	 * @param equityResearchFilter
	 * @param type
	 * @param pageNumber
	 * @param perPageMaxRecords
	 * @param sortBy
	 * @param orderBy
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = WebUriConstants.HomePageSearch.COMPANY_RESEARCH_REPORT, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	String getCompanyResearchReportData(String isinCode, EquityResearchFilter filter,
			String type, String pageNumber, String perPageMaxRecords, String sortBy, String orderBy) throws WebApiException;

//	String getTechnicalDataTab() throws WebApiException;
//
//	String getCalendarDataTab() throws WebApiException;
//
//	String getShareHoldingDataTab() throws WebApiException;
}
