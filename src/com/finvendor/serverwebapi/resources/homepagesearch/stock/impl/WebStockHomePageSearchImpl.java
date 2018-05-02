package com.finvendor.serverwebapi.resources.homepagesearch.stock.impl;

import static com.finvendor.common.exception.ExceptionEnum.COMPANY_HOMEPAGE_EARCH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.finvendor.common.util.ErrorUtil;
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
	@Autowired
	IStockHomePageSearchService stockHomePageSearchService;

	@Override
	public ResponseEntity<?> getCompanySearchHint(@RequestBody EquityResearchFilter filter, String q) throws WebApiException {
		try {
			String homePageSearchHint = stockHomePageSearchService.getHomePageSearchHint(q, filter);
			return new ResponseEntity<String>(homePageSearchHint, HttpStatus.OK);
		} catch (Exception e) {
			ErrorUtil.logError("StockHomePageSearch -> getCompanySearchHint(...) method", e);
			return ErrorUtil.getError(COMPANY_HOMEPAGE_EARCH.getCode(), COMPANY_HOMEPAGE_EARCH.getUserMessage(), e);
		}
	}
}
