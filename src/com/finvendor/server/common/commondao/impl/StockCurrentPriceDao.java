package com.finvendor.server.common.commondao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.finvendor.model.StockCurrentPrice;
import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPricePojo;
import com.finvendor.server.common.commondao.GenericDao;
import com.finvendor.server.common.commondao.IStockCurrentPriceDao;

@Repository
public class StockCurrentPriceDao extends GenericDao<StockCurrentPrice> implements IStockCurrentPriceDao {

	@Override
	public StockCurrentPricePojo getStockCurrentPriceById(Serializable id) throws RuntimeException {
		try {
			StockCurrentPrice stockCurrentPriceEntity = findById(id);
			StockCurrentPricePojo pojo = new StockCurrentPricePojo();
			pojo.setStock_id(stockCurrentPriceEntity.getStock_id());
			pojo.setClose_price(stockCurrentPriceEntity.getClose_price());
			pojo.setLast_traded_price(stockCurrentPriceEntity.getLast_trade_price());
			return pojo;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
