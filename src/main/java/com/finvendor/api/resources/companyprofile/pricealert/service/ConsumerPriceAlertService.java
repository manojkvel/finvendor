package com.finvendor.api.resources.companyprofile.pricealert.service;

import com.finvendor.modelpojo.staticpojo.wathlist.company.ConsumerPriceAlertDTO;
import com.finvendor.api.resources.companyprofile.pricealert.dao.CompanyPriceAlertDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author ayush on May 01, 2018
 */
@Service
@Transactional
public class ConsumerPriceAlertService {

	@Autowired
	private CompanyPriceAlertDao dao;

	public boolean addConsumerPriceAlert(ConsumerPriceAlertDTO pojo) throws Exception {
		try {
			boolean status = dao.addConsumerPriceAlert(pojo);
			return status;
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	public boolean updateConsumerPriceAlert(ConsumerPriceAlertDTO companyPriceAlertPojo) throws Exception {
		try {
			return dao.updateConsumerPriceAlert(companyPriceAlertPojo);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	public boolean deleteConsumerPriceAlerts(List<ConsumerPriceAlertDTO> pojoList) throws Exception {
		try {
			return dao.deleteConsumerPriceAlerts(pojoList);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	public ConsumerPriceAlertDTO findConsumerPriceAlert(String companyId, String userName) throws Exception {
		try {
			return dao.findConsumerPriceAlert(companyId, userName);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	public List<ConsumerPriceAlertDTO> findAllConsumerPriceAlert(String userName) throws Exception {
		try {
			Map<Object, Object> paramMap = new LinkedHashMap<>();
			paramMap.put("username", userName);
			return dao.findAllConsumerPriceAlerts(paramMap);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	public boolean isResearchPriceSet(String companyName) throws Exception {
		return dao.isResearchPriceSet(companyName);
	}
}
