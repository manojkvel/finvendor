package com.finvendor.server.profile.rsrcharea.stock.service;

/**
 * 
 * @author ayush on May 01, 2018
 */
public interface IRsrchAreaStockProfileService {

	/**
	 * 
	 * @param isinCode
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	String getResearchAreaStockProfile(String id, String geo, String isinCode) throws Exception;

	/**
	 * 
	 * @param isinCode
	 * @param rrfilter
	 * @param perPageMaxRecords
	 * @return
	 */
	String getResearchAreaStockRecordStats(String id, String geo, String isinCode,String perPageMaxRecords)
			throws Exception;

	/**
	 * 
	 * @param isinCode
	 * @param filter
	 * @param pageNumber
	 * @param perPageMaxRecords
	 * @param sortBy
	 * @param orderBy
	 * @return
	 * @throws Exception
	 */
	String getResearchReport(String id, String geo, String isinCode,String pageNumber, String perPageMaxRecords,
			String sortBy, String orderBy) throws Exception;
}
