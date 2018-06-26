package com.finvendor.server.stockpriceupdate.service;

import java.util.List;
import java.util.Map;

import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPriceDTO;
import com.finvendor.modelpojo.staticpojo.wathlist.company.ConsumerPriceAlertDTO;

public interface IStockPriceUpdateService {
	/**
	 * 
	 * @param stockCurrentPricePojo
	 * @return
	 * @throws Exception
	 */
	boolean updatePrice(StockCurrentPriceDTO stockCurrentPricePojo) throws Exception;

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
	Map<String, List<ConsumerPriceAlertDTO>> fetchConsumerPriceAlert() throws Exception;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	Map<String, StockCurrentPriceDTO> fetchAllStockCurrentPrice() throws Exception;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	StockCurrentPriceDTO fetchStockPrice() throws Exception;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	StockCurrentPriceDTO getLastWeekPrice(String stockId) throws Exception;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	StockCurrentPriceDTO getLastMonthPrice(String stockId) throws Exception;
}
