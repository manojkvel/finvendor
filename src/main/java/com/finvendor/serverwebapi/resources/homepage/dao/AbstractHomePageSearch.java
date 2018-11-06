package com.finvendor.serverwebapi.resources.homepage.dao;


import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.serverwebapi.resources.homepage.controller.HomePageController;
import com.finvendor.serverwebapi.resources.homepage.dto.HomePageSearchData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractHomePageSearch {
    protected Logger logger = LoggerFactory.getLogger(HomePageController.class.getName());

    @Autowired
    protected ICommonDao commonDao;

    abstract List<? extends HomePageSearchData> getSearchResult(String hint) throws RuntimeException;

}
