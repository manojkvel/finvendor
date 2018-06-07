package com.finvendor.server.homepage.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.common.util.LocaleUtil;
import com.finvendor.server.homepage.dao.IHomePageSearchDao;
import com.finvendor.server.homepage.service.IHomePageService;

@Service
public class HomePageServiceImpl implements IHomePageService {

	@Autowired
	IHomePageSearchDao dao;

	@Override
	@Transactional(readOnly = true)
	public String getHomePageSearchHint(String searchKey) throws Exception {
		String stockSearchHintJson = "NA";
		final String geo = LocaleUtil.getCurrentGeo();
		try {
			Map<Object, Object> paramMap = new LinkedHashMap<>();
			searchKey = searchKey + "%";
			paramMap.put("cid", Integer.parseInt(geo));
			paramMap.put("cname", searchKey);
			paramMap.put("isincode", searchKey);
			paramMap.put("ticker", searchKey);
			stockSearchHintJson = dao.getHomePageSearchHint(paramMap);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
		return stockSearchHintJson;
	}
}
