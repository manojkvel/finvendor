package com.finvendor.server.scheduler.dao;

import java.util.List;
import java.util.Map;

import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPricePojo;
import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyPriceAlertPojo;

public interface ISchedulerDao {
	boolean updatePrice(StockCurrentPricePojo stockCurrentPricePojo) throws RuntimeException;
	Map<String,String> findAllTickerFromDb() throws Exception;
	Map<String,List<CompanyPriceAlertPojo>> fetchAllCompanyPriceAlert()throws RuntimeException;
	Map<String,StockCurrentPricePojo> fetchAllStockPrice()throws RuntimeException;
	StockCurrentPricePojo fetchStockPrice()throws RuntimeException;
	
	StockCurrentPricePojo getLastWeekPrice(String stockId) throws RuntimeException;
	StockCurrentPricePojo getLastMonthPrice(String stockId) throws RuntimeException;
}
