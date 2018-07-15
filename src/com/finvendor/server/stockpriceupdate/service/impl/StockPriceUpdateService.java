package com.finvendor.server.stockpriceupdate.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPriceDTO;
import com.finvendor.modelpojo.staticpojo.wathlist.company.ConsumerPriceAlertDTO;
import com.finvendor.server.stockpriceupdate.dao.IStockPriceUpdateDao;
import com.finvendor.server.stockpriceupdate.service.IStockPriceUpdateService;

@Service
public class StockPriceUpdateService implements IStockPriceUpdateService {

	@Autowired
	private IStockPriceUpdateDao dao;

	@Override
	@Transactional(readOnly = false)
	public boolean updatePrice(StockCurrentPriceDTO stockCurrentPricePojo) throws Exception {
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
	public Map<String, List<ConsumerPriceAlertDTO>> fetchConsumerPriceAlert() throws Exception {
		try {
			return dao.fetchAllConsumerPriceAlert();
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, StockCurrentPriceDTO> fetchAllStockCurrentPrice() throws Exception {
		try {
			return dao.fetchAllStockCurrentPrice();
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public StockCurrentPriceDTO getLastWeekPrice(String stockId) throws Exception {
		try {
			return dao.getLastWeekPrice(stockId);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public StockCurrentPriceDTO getLastMonthPrice(String stockId) throws Exception {
		try {
			return dao.getLastMonthPrice(stockId);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public StockCurrentPriceDTO fetchStockPrice() throws Exception {
		try {
			return dao.fetchStockPrice();
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public String findIsinFromDb(String companyId) throws Exception {
		try {
			return dao.findIsinFromDb(companyId);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

}
