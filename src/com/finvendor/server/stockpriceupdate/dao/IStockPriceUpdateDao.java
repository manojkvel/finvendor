package com.finvendor.server.stockpriceupdate.dao;

import java.util.List;
import java.util.Map;

import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPriceDTO;
import com.finvendor.modelpojo.staticpojo.wathlist.company.ConsumerPriceAlertDTO;

public interface IStockPriceUpdateDao {
	boolean updatePrice(StockCurrentPriceDTO stockCurrentPricePojo) throws RuntimeException;

	Map<String, String> findAllTickerFromDb() throws Exception;
	String findIsinFromDb(String companyId) throws Exception;

	Map<String, List<ConsumerPriceAlertDTO>> fetchAllConsumerPriceAlert() throws RuntimeException;

	Map<String, StockCurrentPriceDTO> fetchAllStockCurrentPrice() throws RuntimeException;

	StockCurrentPriceDTO fetchStockPrice() throws RuntimeException;

	StockCurrentPriceDTO getLastWeekPrice(String stockId) throws RuntimeException;

	StockCurrentPriceDTO getLastMonthPrice(String stockId) throws RuntimeException;
}
