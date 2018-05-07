package com.finvendor.server.watchlist.rsrcharea.stock.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyWatchListPojo;
import com.finvendor.server.watchlist.rsrcharea.stock.dao.IResearchAreaStockWatchListDao;
import com.finvendor.server.watchlist.rsrcharea.stock.service.IRsrchAreaStockWatchListService;

/**
 * 
 * @author ayush on May 01, 2018
 */
@Service
public class RsrchAreaStockWatchListServiceImpl implements IRsrchAreaStockWatchListService {

	@Autowired
	private IResearchAreaStockWatchListDao researchAreaStockWatchListDao;

	@Override
	@Transactional(readOnly = false)
	public boolean addStockWatchList(CompanyWatchListPojo companyWatchListPojo, String id) throws Exception {
		boolean addWatchList = false;
		try {
			addWatchList = researchAreaStockWatchListDao.addWatchList(companyWatchListPojo);
			return addWatchList;
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<CompanyWatchListPojo> findAllStockWatchList(String userName) throws Exception {
		try {
			Map<Object, Object> paramMap = new LinkedHashMap<>();
			paramMap.put("username", userName);
			return researchAreaStockWatchListDao.findAllWatchList(paramMap);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}
}
