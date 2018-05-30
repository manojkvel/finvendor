package com.finvendor.server.companyprofile.pricealert.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPricePojo;
import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyPriceAlertPojo;
import com.finvendor.server.companyprofile.pricealert.dao.IPriceAlertMailDao;
import com.finvendor.server.companyprofile.pricealert.service.IPriceAlertMailService;

@Service
public class PriceAlertMailServiceImpl implements IPriceAlertMailService {

	@Autowired
	private IPriceAlertMailDao dao;

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Scheduler related
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Override
	@Transactional(readOnly = false)
	public boolean updatePrice(StockCurrentPricePojo stockCurrentPricePojo) throws Exception {
		try {
			return dao.updatePrice(stockCurrentPricePojo);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, String> findAllTickerFromDb() throws Exception {
		try {
			return dao.findAllTickerFromDb();
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, List<CompanyPriceAlertPojo>> fetchCompanyPriceAlert() throws Exception {
		try {
			return dao.fetchAllCompanyPriceAlert();
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, StockCurrentPricePojo> fetchAllStockPrice() throws Exception {
		try {
			return dao.fetchAllStockPrice();
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public StockCurrentPricePojo getLastWeekPrice(String stockId) throws Exception {
		try {
			return dao.getLastWeekPrice(stockId);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public StockCurrentPricePojo getLastMonthPrice(String stockId) throws Exception {
		try {
			return dao.getLastMonthPrice(stockId);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public StockCurrentPricePojo fetchStockPrice() throws Exception {
		try {
			return dao.fetchStockPrice();
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

}
