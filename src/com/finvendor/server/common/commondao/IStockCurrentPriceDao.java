package com.finvendor.server.common.commondao;

import java.io.Serializable;

import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPricePojo;

public interface IStockCurrentPriceDao {

	StockCurrentPricePojo getStockCurrentPriceById(Serializable id) throws RuntimeException;
}
