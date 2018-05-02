package com.finvendor.serverwebapi.resources.profile.companyprofile;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finvendor.server.researchreport.dto.filter.impl.EquityResearchFilter;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.WebUriConstants;

/**
 * 
 * @author ayush on April 30, 2018
 */
@RequestMapping(WebUriConstants.BASE_URI)
public interface IWebCompnayProfile {

	/**
	 * 
	 * @param rrfilter
	 * @param isinCode
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = "/companyprofile", method = RequestMethod.POST)
	@ResponseBody
	ResponseEntity<?> getCompanyProfile(EquityResearchFilter filter, String q) throws WebApiException;

	/**
	 * 
	 * @param isinCode
	 * @param equityResearchFilter
	 * @param type
	 * @param perPageMaxRecords
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = "/companyrecordstats", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<?> getCompanyResearchReportRecordStatistics(EquityResearchFilter filter, String isinCode, String perPageMaxRecords)
			throws WebApiException;

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
	@RequestMapping(value = "/companyresearchreport", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<?> getCompanyResearchReport(EquityResearchFilter filter, String isinCode, String pageNumber, String perPageMaxRecords,
			String sortBy, String orderBy) throws WebApiException;

	// String getTechnicalDataTab() throws WebApiException;
	//
	// String getCalendarDataTab() throws WebApiException;
	//
	// String getShareHoldingDataTab() throws WebApiException;
}
