package com.finvendor.server.homepage.dao;

public interface IHomePageSearchDao {

	public final static String SEARCH_HINT_QUERY = "select a.company_id,a.company_name,a.isin_code,a.ticker,b.close_price from rsch_sub_area_company_dtls a,stock_current_prices b where a.company_name like SEARCHKEY or a.isin_code like SEARCHKEY or a.ticker like SEARCHKEY and a.company_id=b.stock_id group by a.company_name order by a.company_id;";

	/**
	 * @param hint
	 * @return
	 * @throws RuntimeException
	 */
	String getHomePageSearchHint(String searchKey) throws RuntimeException;
}
