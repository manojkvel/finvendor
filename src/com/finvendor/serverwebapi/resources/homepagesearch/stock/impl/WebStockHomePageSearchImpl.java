package com.finvendor.serverwebapi.resources.homepagesearch.stock.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.finvendor.common.util.ExceptionUtil;
import com.finvendor.server.homepagesearch.stock.service.IStockHomePageSearchService;
import com.finvendor.server.researchreport.dto.filter.impl.EquityResearchFilter;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.homepagesearch.stock.IWebStockHomePageSearch;

/**
 * 
 * @author ayush on April 30, 2018
 */
@Controller
public class WebStockHomePageSearchImpl implements IWebStockHomePageSearch {
	private static Logger logger = LoggerFactory.getLogger(WebStockHomePageSearchImpl.class);

	@Autowired
	IStockHomePageSearchService stockHomePageSearchService;

	@Override
	public String getCompanySearchHint(@RequestBody EquityResearchFilter filter, String q) throws WebApiException {
		try {
			return stockHomePageSearchService.getHomePageSearchHint(q, filter);
		} catch (Exception e) {
			String apiErrorMessage = ExceptionUtil
					.buildErrorMessage("Error has occurred in WebHomePageSearch -> getSearchHint(...) method", e);
			logger.error("Web API Error: " + apiErrorMessage);
			throw new WebApiException(apiErrorMessage);
		}
	}
}
