package com.finvendor.server.homepagesearch.stock.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.server.homepagesearch.stock.dao.IStockHomePageSearchDao;
import com.finvendor.server.homepagesearch.stock.service.IStockHomePageSearchService;
import com.finvendor.server.researchreport.dto.filter.impl.EquityResearchFilter;

@Service
public class StockHomePageSearchServiceImpl implements IStockHomePageSearchService {

	@Autowired
	IStockHomePageSearchDao stockHomePageSearchDao;

	@Override
	@Transactional(readOnly = true)
	public String getHomePageSearchHint(String searchKeyword, EquityResearchFilter filter) throws Exception {
		String stockSearchHintJson = "NA";
		String geo = ((EquityResearchFilter) filter).getGeo();
		try {
			Map<Object, Object> paramMap = new LinkedHashMap<>();
			searchKeyword = searchKeyword + "%";
			paramMap.put("cid", Integer.parseInt(geo));
			paramMap.put("cname", searchKeyword);
			paramMap.put("isincode", searchKeyword);
			paramMap.put("ticker", searchKeyword);
			stockSearchHintJson = stockHomePageSearchDao.getSearchHint(paramMap);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
		return stockSearchHintJson;
	}
}
