package com.finvendor.common.commondao;

import java.io.Serializable;

import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPriceDTO;

public interface IStockCurrentPriceDao {

	StockCurrentPriceDTO getStockCurrentPriceById(Serializable id) throws RuntimeException;
}
