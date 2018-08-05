package com.finvendor.server.homepage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		try {
			stockSearchHintJson = dao.getHomePageSearchHint(searchKey);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
		return stockSearchHintJson;
	}
}
