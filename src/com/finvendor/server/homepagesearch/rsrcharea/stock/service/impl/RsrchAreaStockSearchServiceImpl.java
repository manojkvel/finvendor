package com.finvendor.server.homepagesearch.rsrcharea.stock.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.common.util.LocaleUtil;
import com.finvendor.server.homepagesearch.rsrcharea.stock.dao.IStockHomePageSearchDao;
import com.finvendor.server.homepagesearch.rsrcharea.stock.service.IRsrchAreaStockSearchService;

@Service
public class RsrchAreaStockSearchServiceImpl implements IRsrchAreaStockSearchService {

	@Autowired
	IStockHomePageSearchDao stockHomePageSearchDao;

	@Override
	@Transactional(readOnly = true)
	public String getResearchAreaStockSearchHint(String id, String key) throws Exception {
		String stockSearchHintJson = "NA";
		final String geo = LocaleUtil.getCurrentGeo();
		try {
			Map<Object, Object> paramMap = new LinkedHashMap<>();
			key = key + "%";
			paramMap.put("cid", Integer.parseInt(geo));
			paramMap.put("cname", key);
			paramMap.put("isincode", key);
			paramMap.put("ticker", key);
			stockSearchHintJson = stockHomePageSearchDao.getSearchHint(paramMap);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
		return stockSearchHintJson;
	}
}
