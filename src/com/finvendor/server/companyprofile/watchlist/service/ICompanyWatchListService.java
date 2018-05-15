package com.finvendor.server.companyprofile.watchlist.service;

import java.util.List;

import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyWatchListPojo;

/**
 * 
 * @author ayush on May 01, 2018
 */
public interface ICompanyWatchListService {
	/**
	 * 
	 * @param researchArea
	 * @param companyWatchListPojo
	 * @return
	 * @throws Exception
	 */
	boolean addCompanyWatchList(CompanyWatchListPojo companyWatchListPojo) throws Exception;
	
	/**
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	List<CompanyWatchListPojo> findAllCompanyWatchList(String userName) throws Exception;
	
	boolean deleteCompnayWatchList(List<CompanyWatchListPojo> pojoList) throws Exception;
}
