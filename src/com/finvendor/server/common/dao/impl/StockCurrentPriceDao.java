package com.finvendor.server.common.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.finvendor.model.StockCurrentPrice;
import com.finvendor.server.common.dao.IStockCurrentPriceDao;
import com.finvendor.server.common.dao.infra.GenericDao;
import com.finvendor.server.common.dao.staticpojo.StockCurrentPricePojo;

@Repository
public class StockCurrentPriceDao extends GenericDao<StockCurrentPrice> implements IStockCurrentPriceDao {

	@Override
	public StockCurrentPricePojo getStockCurrentPriceById(Serializable id) throws RuntimeException {
		StockCurrentPrice stockCurrentPriceEntity = findById(id);
		StockCurrentPricePojo pojo=new StockCurrentPricePojo();
		pojo.setStock_id(stockCurrentPriceEntity.getStock_id());
		pojo.setClose_price(stockCurrentPriceEntity.getClose_price());
		return pojo;
	}

}
