package com.finvendor.server.watchlist.companywatchlist.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyWatchListPojo;
import com.finvendor.server.watchlist.companywatchlist.dao.IWatchListDao;
import com.finvendor.server.watchlist.companywatchlist.service.ICompanyWatchListService;

/**
 * 
 * @author ayush on May 01, 2018
 */
@Service
public class ComapnyWatchListServiceImpl implements ICompanyWatchListService {

	@Autowired
	private IWatchListDao companyWatchListDao;

	@Override
	@Transactional(readOnly = false)
	public boolean addWatchList(CompanyWatchListPojo companyWatchListPojo) throws Exception {
		boolean addWatchList = false;
		try {
			addWatchList = companyWatchListDao.addWatchList(companyWatchListPojo);
			return addWatchList;
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<CompanyWatchListPojo> findAllWatchList(String userName) throws Exception {
		try {
			Map<Object, Object> paramMap = new LinkedHashMap<>();
			paramMap.put("username", userName);
			return companyWatchListDao.findAllWatchList(paramMap);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}
}
