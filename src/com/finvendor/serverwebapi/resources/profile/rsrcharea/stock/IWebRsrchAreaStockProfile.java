package com.finvendor.serverwebapi.resources.profile.rsrcharea.stock;

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
public interface IWebRsrchAreaStockProfile {

	/**
	 * 
	 * @param rrfilter
	 * @param isinCode
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = "/profile/researcharea", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<?> getRsrchAreaStockProfile(String id, String isinCode) throws WebApiException;

	/**
	 * 
	 * @param isinCode
	 * @param equityResearchFilter
	 * @param type
	 * @param perPageMaxRecords
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = "/recordstats/reaseacharea", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<?> getRsrchAreaStockRecordStats(String id, String isinCode, String perPageMaxRecords)
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
	@RequestMapping(value = "/researchreport/reaseacharea", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<?> getRsrchAreaStockRsrchReport(String id, String isinCode, String pageNumber,
			String perPageMaxRecords, String sortBy, String orderBy) throws WebApiException;

	// String getTechnicalDataTab() throws WebApiException;
	//
	// String getCalendarDataTab() throws WebApiException;
	//
	// String getShareHoldingDataTab() throws WebApiException;
}
