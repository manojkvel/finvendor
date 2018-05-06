package com.finvendor.serverwebapi.resources.homepagesearch.rsrcharea.stock.impl;

import static com.finvendor.common.exception.ExceptionEnum.COMPANY_HOMEPAGE_EARCH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.finvendor.common.util.ErrorUtil;
import com.finvendor.server.homepagesearch.rsrcharea.stock.service.IRsrchAreaStockSearchService;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.homepagesearch.rsrcharea.stock.IWebRsrchAreaStockSearch;

/**
 * 
 * @author ayush on April 30, 2018
 */
@Controller
public class WebRsrchAreaStockSearchImpl implements IWebRsrchAreaStockSearch {
	@Autowired
	IRsrchAreaStockSearchService researchAreaStockSearchService;

	@Override
	public ResponseEntity<?> getResearchAreaStockSearchHint(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "geo", required = true) String geo,
			@RequestParam(value = "key", required = true) String key) throws WebApiException {
		try {
			String homePageSearchHint = researchAreaStockSearchService.getResearchAreaStockSearchHint(id,geo,key);
			return new ResponseEntity<String>(homePageSearchHint, HttpStatus.OK);
		} catch (Exception e) {
			ErrorUtil.logError("StockHomePageSearch -> getCompanySearchHint(...) method", e);
			return ErrorUtil.getError(COMPANY_HOMEPAGE_EARCH.getCode(), COMPANY_HOMEPAGE_EARCH.getUserMessage(), e);
		}
	}
}
