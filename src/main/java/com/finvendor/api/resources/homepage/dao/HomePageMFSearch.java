package com.finvendor.api.resources.homepage.dao;

import com.finvendor.api.resources.homepage.dto.MutualFundData;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HomePageMFSearch extends AbstractHomePageSearch {
    private static final String SEARCH_HINT_QUERY = "";

    /**
     * search from mf-master table of scheme_nav_name
     * we have to return schem_nav_name from mf_master table
     *
     * we will use like keyword to find the scheme nav name
     */
    @Override
    List<MutualFundData> getSearchResult(String hint) throws RuntimeException {
        List<MutualFundData> companyDataList = new ArrayList<>();
        try {
//            String query = SEARCH_HINT_QUERY;
//            query = StringUtils.replace(query, "SEARCHKEY", "'%" + hint + "%'");
//            SQLQuery sqlQuery = commonDao.getNativeQuery(query, null);
//            List<Object[]> rows = sqlQuery.list();
//            for (Object[] row : rows) {
//                String companyId = row[0] != null ? row[0].toString().trim() : "";
//
//                companyDataList.add(new MutualFundData());
//            }
            companyDataList.add(new MutualFundData("dummyCode", "dummyName"));
            return companyDataList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
