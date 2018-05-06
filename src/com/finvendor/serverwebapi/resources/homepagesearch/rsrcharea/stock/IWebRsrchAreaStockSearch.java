package com.finvendor.serverwebapi.resources.homepagesearch.rsrcharea.stock;

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
public interface IWebRsrchAreaStockSearch {

	/**
	 * @param companyName
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = "/search/researcharea", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<?> getResearchAreaStockSearchHint(String id, String geo, String key) throws WebApiException;

}
