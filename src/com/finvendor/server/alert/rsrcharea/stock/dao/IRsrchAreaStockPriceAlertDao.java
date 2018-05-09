package com.finvendor.server.alert.rsrcharea.stock.dao;

import java.util.List;
import java.util.Map;

import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyPriceAlertPojo;


/**
 * 
 * @author ayush on May 01, 2018
 */
public interface IRsrchAreaStockPriceAlertDao {

	boolean addPriceAlert(CompanyPriceAlertPojo priceAlertPojo) throws RuntimeException;
	
	List<CompanyPriceAlertPojo> findAllRsrchAreaStockPriceAlerts(Map<Object, Object> paramMap)throws RuntimeException;
}
