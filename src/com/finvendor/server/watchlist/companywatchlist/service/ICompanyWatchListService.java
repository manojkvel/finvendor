package com.finvendor.server.watchlist.companywatchlist.service;

import java.util.List;

import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyWatchListPojo;

/**
 * 
 * @author ayush on May 01, 2018
 */
public interface ICompanyWatchListService {
	boolean addWatchList(CompanyWatchListPojo companyWatchListPojo) throws Exception;

	List<CompanyWatchListPojo> findAllWatchList(String userName) throws Exception;
}
