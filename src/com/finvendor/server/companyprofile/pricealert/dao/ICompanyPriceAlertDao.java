package com.finvendor.server.companyprofile.pricealert.dao;

import java.util.List;
import java.util.Map;

import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyPriceAlertPojo;


/**
 * 
 * @author ayush on May 01, 2018
 */
public interface ICompanyPriceAlertDao {

	boolean addCompanyPriceAlert(CompanyPriceAlertPojo priceAlertPojo) throws RuntimeException;

	boolean updateCompanyPriceAlert(CompanyPriceAlertPojo companyPriceAlertPojo) throws RuntimeException;
	
	boolean deleteCompanyPriceAlerts(List<CompanyPriceAlertPojo> pojoList)	throws RuntimeException;
	
	CompanyPriceAlertPojo findCompanyPriceAlert(String companyId, String userName) throws RuntimeException;
	
	List<CompanyPriceAlertPojo> findAllCompanyPriceAlerts(Map<Object, Object> paramMap)throws RuntimeException;
}
