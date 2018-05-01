package com.finvendor.server.homepagesearch.stock.service;

import com.finvendor.server.researchreport.dto.filter.impl.EquityResearchFilter;

public interface IStockHomePageSearchService {

	/**
	 * @param searchKeyword
	 * @return
	 * @throws Exception
	 */
	String getHomePageSearchHint(String searchKeyword, final EquityResearchFilter filter) throws Exception;
}
