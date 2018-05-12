package com.finvendor.server.homepagesearch.rsrcharea.stock.service;

public interface IRsrchAreaStockSearchService {

	/**
	 * @param searchKeyword
	 * @return
	 * @throws Exception
	 */
	String getResearchAreaStockSearchHint(String id, String key) throws Exception;
}
