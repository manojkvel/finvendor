package com.finvendor.server.alert.rsrcharea.stock.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyPriceAlertPojo;
import com.finvendor.server.alert.rsrcharea.stock.dao.IRsrchAreaStockPriceAlertDao;
import com.finvendor.server.alert.rsrcharea.stock.service.IRsrchAreaStockPriceAlertService;

/**
 * 
 * @author ayush on May 01, 2018
 */
@Service
public class RsrchAreaStockPriceAlertService implements IRsrchAreaStockPriceAlertService {

	@Autowired
	private IRsrchAreaStockPriceAlertDao dao;

	@Override
	@Transactional(readOnly = false)
	public boolean addPriceAlert(CompanyPriceAlertPojo pojo, String id) throws Exception {
		try {
			boolean status = dao.addPriceAlert(pojo);
			return status;
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<CompanyPriceAlertPojo> findAllPriceAlerts(String userName) throws Exception {
		try {
			Map<Object, Object> paramMap = new LinkedHashMap<>();
			paramMap.put("username", userName);
			return dao.findAllRsrchAreaStockPriceAlerts(paramMap);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}
}
