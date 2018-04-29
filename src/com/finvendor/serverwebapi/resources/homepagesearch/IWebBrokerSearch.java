package com.finvendor.serverwebapi.resources.homepagesearch;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finvendor.server.researchreport.dto.filter.impl.EquityResearchFilter;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.WebUriConstants;

@RequestMapping(WebUriConstants.BASE_URI)
public interface IWebBrokerSearch {

	/**
	 * 
	 * @param companyName
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = WebUriConstants.HomePageSearch.COMPANY_HOME_PAGE_SEARCH_URI, method = RequestMethod.POST)
	@ResponseBody
	String getBrokerHomePageSearchData(EquityResearchFilter filter, String q) throws WebApiException;
}
