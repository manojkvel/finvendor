package com.finvendor.serverwebapi.resources.companyprofile.companyprofile;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.WebUriConstants;

/**
 * 
 * @author ayush on April 30, 2018
 */
@RequestMapping(WebUriConstants.BASE_URI)
public interface IWebCompanyProfile {

	/**
	 * 
	 * @param rrfilter
	 * @param isinCode
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = "/companyprofile", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<?> getCompanyProfile(String isinCode) throws WebApiException;

	/**
	 * 
	 * @param isinCode
	 * @param equityResearchFilter
	 * @param type
	 * @param perPageMaxRecords
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = "/companyprofile/recordstat", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<?> getCompanyProfileRecordStats(String isinCode, String perPageMaxRecords)
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
	@RequestMapping(value = "/companyprofile/researchreport", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<?> getCompanyProfileResearchReport(String isinCode, String pageNumber, String perPageMaxRecords, String sortBy, String orderBy) throws WebApiException;

	// String getTechnicalDataTab() throws WebApiException;
	//
	// String getCalendarDataTab() throws WebApiException;
	//
	// String getShareHoldingDataTab() throws WebApiException;
}
