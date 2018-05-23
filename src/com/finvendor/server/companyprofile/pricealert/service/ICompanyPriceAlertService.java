package com.finvendor.server.companyprofile.pricealert.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyPriceAlertPojo;

/**
 * 
 * @author ayush on May 01, 2018
 */
@Service
public interface ICompanyPriceAlertService {

	boolean addCompanyPriceAlert(CompanyPriceAlertPojo pojo) throws Exception;

	boolean updateCompanyPriceAlert(CompanyPriceAlertPojo companyPriceAlertPojo) throws Exception;

	boolean deleteCompanyPriceAlerts(List<CompanyPriceAlertPojo> pojoList)	throws Exception;

	CompanyPriceAlertPojo findCompanyPriceAlert(String companyId, String userName) throws Exception;

	List<CompanyPriceAlertPojo> findAllCompanyPriceAlert(String userName) throws Exception;
	
}
