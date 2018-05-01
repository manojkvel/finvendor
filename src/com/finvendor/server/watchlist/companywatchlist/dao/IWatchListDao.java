package com.finvendor.server.watchlist.companywatchlist.dao;

import java.util.List;
import java.util.Map;

import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyWatchListPojo;

/**
 * 
 * @author ayush on May 01, 2018
 */
public interface IWatchListDao {
	boolean addWatchList(CompanyWatchListPojo companyWatchList) throws RuntimeException;

	List<CompanyWatchListPojo> findAllWatchList(Map<Object, Object> paramMap) throws RuntimeException;
}
