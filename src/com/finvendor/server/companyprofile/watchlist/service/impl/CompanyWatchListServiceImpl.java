package com.finvendor.server.companyprofile.watchlist.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyWatchListPojo;
import com.finvendor.server.companyprofile.watchlist.dao.ICompanyWatchListDao1;
import com.finvendor.server.companyprofile.watchlist.service.ICompanyWatchListService;

/**
 * 
 * @author ayush on May 01, 2018
 */
@Service
public class CompanyWatchListServiceImpl implements ICompanyWatchListService {

	@Autowired
	private ICompanyWatchListDao1 dao;

	@Override
	@Transactional(readOnly = false)
	public boolean addCompanyWatchList(CompanyWatchListPojo companyWatchListPojo) throws Exception {
		boolean addWatchList = false;
		try {
			addWatchList = dao.addCompanyWatchList(companyWatchListPojo);
			return addWatchList;
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<CompanyWatchListPojo> findAllCompanyWatchList(String userName) throws Exception {
		try {
			Map<Object, Object> paramMap = new LinkedHashMap<>();
			paramMap.put("username", userName);
			return dao.findAllCompanyWatchList(paramMap);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteCompnayWatchList(List<CompanyWatchListPojo> pojoList) throws Exception {
		try {
			return dao.deleteCompanyWatchList(pojoList);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}
}
