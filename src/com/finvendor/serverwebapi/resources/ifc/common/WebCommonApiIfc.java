package com.finvendor.serverwebapi.resources.ifc.common;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finvendor.modelpojo.staticpojo.admindashboard.CompanyDetails;
import com.finvendor.serverwebapi.exception.WebApiException;

/**
 * @author ayush on Feb 17, 2018
 */
@RequestMapping(WebUriConstants.BASE_URI)
public interface WebCommonApiIfc {
	
	@RequestMapping(value = WebUriConstants.AdminDashBoard.COMPANY_DETAILS_URI, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	List<CompanyDetails> getCompanyDetails(@RequestParam("researchAreaId") String researchAreaId) throws WebApiException;
	
	/**
	 * 
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = WebUriConstants.FILTER_DATA_URI, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	String getResearchFilterData(@RequestParam("type") String  type) throws WebApiException;

}
