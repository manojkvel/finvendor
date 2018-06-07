package com.finvendor.server.companyprofile.pricealert.service;

import java.util.List;
import java.util.Map;

import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPricePojo;
import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyPriceAlertPojo;

public interface IPriceAlertMailService {

	/**
	 * 
	 * @param stockCurrentPricePojo
	 * @return
	 * @throws Exception
	 */
	boolean updatePrice(StockCurrentPricePojo stockCurrentPricePojo) throws Exception;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	Map<String, String> findAllTickerFromDb() throws Exception;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	Map<String, List<CompanyPriceAlertPojo>> fetchCompanyPriceAlert() throws Exception;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	Map<String, StockCurrentPricePojo> fetchAllStockPrice() throws Exception;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	StockCurrentPricePojo fetchStockPrice() throws Exception;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	StockCurrentPricePojo getLastWeekPrice(String stockId) throws Exception;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	StockCurrentPricePojo getLastMonthPrice(String stockId) throws Exception;
}
