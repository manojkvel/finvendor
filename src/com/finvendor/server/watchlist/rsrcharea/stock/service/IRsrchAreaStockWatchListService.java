package com.finvendor.server.watchlist.rsrcharea.stock.service;

import java.util.List;

import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyWatchListPojo;

/**
 * 
 * @author ayush on May 01, 2018
 */
public interface IRsrchAreaStockWatchListService {
	boolean addStockWatchList(CompanyWatchListPojo companyWatchListPojo,String id) throws Exception;

	List<CompanyWatchListPojo> findAllStockWatchList(String userName) throws Exception;
}
