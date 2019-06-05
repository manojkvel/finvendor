package com.finvendor.api.homepage.dao;

import com.finvendor.common.util.JsonUtil;
import com.finvendor.api.homepage.dto.HomePageSearchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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

    @Autowired
    @Qualifier(value = "homePageSectorSearch")
    private AbstractHomePageSearch homePageSectorSearch;

    public String getHomePageSearchResult(String searchKey) throws RuntimeException {
        try {
            List<? extends HomePageSearchData> stockSearchResult = homePageStockSearch.getSearchResult(searchKey);
            List<? extends HomePageSearchData> mfSearchResult = homePageMFSearch.getSearchResult(searchKey);
            List<? extends HomePageSearchData> sectorSearchResult = homePageSectorSearch.getSearchResult(searchKey);
            Map<String, Object> resultMap = new HashMap<>();
            Map<String, Object> paramsMap = new LinkedHashMap<>();
            paramsMap.put("stock", stockSearchResult);
            paramsMap.put("mf", mfSearchResult);
            paramsMap.put("sector", sectorSearchResult);
            resultMap.put("data", paramsMap);
            String resultString = JsonUtil.createJsonFromParamsMap(paramsMap);
            return resultString;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}