package com.finvendor.server.homepagesearch.rsrcharea.stock.service;

public interface IRsrchAreaStockSearchService {

	/**
	 * @param searchKeyword
	 * @return
	 * @throws Exception
	 */
	String getResearchAreaStockSearchHint(String id,String geo, String key) throws Exception;
}
