package com.finvendor.common.commondao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.finvendor.model.StockCurrentPrice;
import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPriceDTO;
import com.finvendor.common.commondao.GenericDao;
import com.finvendor.common.commondao.IStockCurrentPriceDao;

@Repository
public class StockCurrentPriceDao extends GenericDao<StockCurrentPrice> implements IStockCurrentPriceDao {

	@Override
	public StockCurrentPriceDTO getStockCurrentPriceById(Serializable id) throws RuntimeException {
		try {
			StockCurrentPrice stockCurrentPriceEntity = findById(id);
			StockCurrentPriceDTO pojo = new StockCurrentPriceDTO();
			pojo.setStock_id(stockCurrentPriceEntity.getStock_id());
			pojo.setClose_price(stockCurrentPriceEntity.getClose_price());
			pojo.setLast_traded_price(stockCurrentPriceEntity.getLast_trade_price());
			return pojo;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
