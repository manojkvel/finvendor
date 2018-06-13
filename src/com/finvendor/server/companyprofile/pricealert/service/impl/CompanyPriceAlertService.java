package com.finvendor.server.companyprofile.pricealert.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyPriceAlertPojo;
import com.finvendor.server.companyprofile.pricealert.dao.ICompanyPriceAlertDao;
import com.finvendor.server.companyprofile.pricealert.service.ICompanyPriceAlertService;

/**
 * 
 * @author ayush on May 01, 2018
 */
@Service
public class CompanyPriceAlertService implements ICompanyPriceAlertService {

	@Autowired
	private ICompanyPriceAlertDao dao;

	@Override
	@Transactional(readOnly = false)
	public boolean addCompanyPriceAlert(CompanyPriceAlertPojo pojo) throws Exception {
		try {
			boolean status = dao.addCompanyPriceAlert(pojo);
			return status;
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}
	
	@Override
	@Transactional(readOnly = false)
	public boolean updateCompanyPriceAlert(CompanyPriceAlertPojo companyPriceAlertPojo) throws Exception {
		try {
			return dao.updateCompanyPriceAlert(companyPriceAlertPojo);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteCompanyPriceAlerts(List<CompanyPriceAlertPojo> pojoList) throws Exception {
		try {
			return dao.deleteCompanyPriceAlerts(pojoList);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public CompanyPriceAlertPojo findCompanyPriceAlert(String companyId, String userName) throws Exception {
		try {
			return dao.findCompanyPriceAlert(companyId, userName);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<CompanyPriceAlertPojo> findAllCompanyPriceAlert(String userName) throws Exception {
		try {
			Map<Object, Object> paramMap = new LinkedHashMap<>();
			paramMap.put("username", userName);
			return dao.findAllCompanyPriceAlerts(paramMap);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isResearchPriceSet(String companyName) throws Exception {
		return dao.isResearchPriceSet(companyName);
	}
}
