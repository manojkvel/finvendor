package com.finvendor.server.alert.rsrcharea.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyPriceAlertPojo;

/**
 * 
 * @author ayush on May 01, 2018
 */
@Service
public interface IRsrchAreaStockPriceAlertService {

	boolean addPriceAlert(CompanyPriceAlertPojo pojo, String id) throws Exception;

	List<CompanyPriceAlertPojo> findAllPriceAlerts(String userName) throws Exception;
}
