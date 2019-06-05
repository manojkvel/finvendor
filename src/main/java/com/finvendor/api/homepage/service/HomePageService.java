package com.finvendor.api.homepage.service;

import com.finvendor.api.homepage.dao.HomePageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HomePageService {

	@Autowired
	HomePageDao homePageDao;

	public String getHomePageSearchHint(String searchKey) throws Exception {
		String stockSearchHintJson;
		try {
			stockSearchHintJson = homePageDao.getHomePageSearchResult(searchKey);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
		return stockSearchHintJson;
	}
}
