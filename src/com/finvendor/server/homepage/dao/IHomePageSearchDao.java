package com.finvendor.server.homepage.dao;

import java.util.Map;

public interface IHomePageSearchDao {

	public final static String SEARCH_HINT_QUERY = "SELECT distinct(x.company_id), x.company_name, x.isin_code, x.ticker from rsch_sub_area_company_dtls x, country y where x.country_id=y.country_id  and x.country_id = COUNTRYID and x.company_name like COMPANYNAME or x.isin_code like ISINCODE or x.ticker like TICKER order by x.company_name";

	/**
	 * @param hint
	 * @return
	 * @throws RuntimeException
	 */
	String getHomePageSearchHint(Map<Object, Object> paramMap) throws RuntimeException;
}
