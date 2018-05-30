package com.finvendor.server.companyprofile.watchlist.dao;

import java.util.List;
import java.util.Map;

import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyWatchListPojo;

/**
 * 
 * @author ayush on May 01, 2018
 */
public interface ICompanyWatchListDao1 {
	/**
	 * 
	 * @param companyWatchList
	 * @return
	 * @throws RuntimeException
	 */
	boolean addCompanyWatchList(CompanyWatchListPojo companyWatchList) throws RuntimeException;

	/**
	 * 
	 * @param paramMap
	 * @return
	 * @throws RuntimeException
	 */
	List<CompanyWatchListPojo> findAllCompanyWatchList(Map<Object, Object> paramMap) throws RuntimeException;
	
	boolean deleteCompanyWatchList(List<CompanyWatchListPojo> pojoList) throws RuntimeException;
}
