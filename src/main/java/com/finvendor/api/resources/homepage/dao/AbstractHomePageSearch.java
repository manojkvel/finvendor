package com.finvendor.api.resources.homepage.dao;


import com.finvendor.common.commondao.ICommonDao;
import com.finvendor.api.resources.homepage.controller.HomePageController;
import com.finvendor.api.resources.homepage.dto.HomePageSearchData;
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
