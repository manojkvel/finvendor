package com.finvendor.server.common.dao;

import java.io.Serializable;

import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPricePojo;

public interface IStockCurrentPriceDao {

	StockCurrentPricePojo getStockCurrentPriceById(Serializable id) throws RuntimeException;
}
