package com.finvendor.server.companyprofile.pricealert.dao;

import java.util.List;
import java.util.Map;

import com.finvendor.modelpojo.staticpojo.wathlist.company.ConsumerPriceAlertDTO;


/**
 * 
 * @author ayush on May 01, 2018
 */
public interface IConsumerPriceAlertDao {

	boolean addConsumerPriceAlert(ConsumerPriceAlertDTO priceAlertPojo) throws RuntimeException;

	boolean updateConsumerPriceAlert(ConsumerPriceAlertDTO companyPriceAlertPojo) throws RuntimeException;
	
	boolean deleteConsumerPriceAlerts(List<ConsumerPriceAlertDTO> pojoList)	throws RuntimeException;
	
	ConsumerPriceAlertDTO findConsumerPriceAlert(String companyId, String userName) throws RuntimeException;
	
	List<ConsumerPriceAlertDTO> findAllConsumerPriceAlerts(Map<Object, Object> paramMap)throws RuntimeException;
	
	boolean isResearchPriceSet(String companyName) throws RuntimeException;
}
