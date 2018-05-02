package com.finvendor.serverwebapi.resources.homepagesearch.stock;

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
public interface IWebStockHomePageSearch {

	/**
	 * @param companyName
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = "/search/company", method = RequestMethod.POST)
	@ResponseBody
	ResponseEntity<?> getCompanySearchHint(EquityResearchFilter filter, String q) throws WebApiException;

}
