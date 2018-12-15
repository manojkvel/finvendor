package com.finvendor.api.resources.homepage.dao;

import com.finvendor.common.util.JsonUtil;
import com.finvendor.api.resources.homepage.dto.HomePageSearchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Ayush on 05-Apr-2018
 */
@Repository
public class HomePageDao {

    @Autowired
    @Qualifier(value = "homePageStockSearch")
    private AbstractHomePageSearch homePageStockSearch;

    @Autowired
    @Qualifier(value = "homePageMFSearch")
    private AbstractHomePageSearch homePageMFSearch;

    public String getHomePageSearchResult(String searchKey) throws RuntimeException {
        try {
            List<? extends HomePageSearchData> stockSearchResult = homePageStockSearch.getSearchResult(searchKey);
            List<? extends HomePageSearchData> mfSearchResult = homePageMFSearch.getSearchResult(searchKey);

            Map<String, Object> paramsMap = new LinkedHashMap<>();
            paramsMap.put("stock", stockSearchResult);
            paramsMap.put("mutualFund", mfSearchResult);

            return JsonUtil.createJsonFromParamsMap(paramsMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}