package com.finvendor.serverwebapi.resources.common;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.WebUriConstants;

/**
 * @author ayush on Feb 17, 2018
 */
@RequestMapping(WebUriConstants.BASE_URI)
public interface IWebApiCommon {
	
	@RequestMapping(value = WebUriConstants.AdminDashBoard.COMPANY_DETAILS_URI, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<?> getCompanyDetails(@RequestParam("researchAreaId") String researchAreaId) throws WebApiException;
	
	/**
	 * 
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = WebUriConstants.FILTER_DATA_URI, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<?> getResearchFilterData(@RequestParam("type") String  type) throws WebApiException;

}
