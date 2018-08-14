package com.finvendor.server.stockpriceupdate.dao;

import java.util.List;
import java.util.Map;

import com.finvendor.common.util.Pair;
import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPriceDTO;
import com.finvendor.modelpojo.staticpojo.wathlist.company.ConsumerPriceAlertDTO;
import com.finvendor.server.common.infra.parser.StockPrice;

public interface IStockPriceUpdateDao {
	boolean updatePrice(StockCurrentPriceDTO stockCurrentPricePojo) throws RuntimeException;

	Map<String, String> findAllTickerFromDb() throws RuntimeException;
	List<Pair<String,String>> findAllIsin() throws Exception;
	String findIsin(String companyId) throws Exception;

	Map<String, List<ConsumerPriceAlertDTO>> fetchAllConsumerPriceAlert() throws RuntimeException;

	Map<String, StockCurrentPriceDTO> fetchAllStockCurrentPrice() throws RuntimeException;

	StockCurrentPriceDTO fetchStockPrice() throws RuntimeException;

	StockCurrentPriceDTO getLastWeekPrice(String stockId) throws RuntimeException;

	StockCurrentPriceDTO getLastMonthPrice(String stockId) throws RuntimeException;
	int updateStockPrice(String stockId, StockPrice stockPrice1,String priceDate)  throws RuntimeException;
	void updateCompanyDesc(String isin,String desc)  throws RuntimeException;
}
