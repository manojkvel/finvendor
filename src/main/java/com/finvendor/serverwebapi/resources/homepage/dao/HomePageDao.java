package com.finvendor.serverwebapi.resources.homepage.dao;

import com.finvendor.common.util.JsonUtil;
import com.finvendor.model.CompanyWatchList;
import com.finvendor.server.common.commondao.GenericDao;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.serverwebapi.resources.homepage.dto.CompnyData;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class HomePageDao extends GenericDao<CompanyWatchList> {//implements IHomePageSearchDao {

    @Autowired
    private ICommonDao commonDao;

    private static final String SEARCH_HINT_QUERY = "select a.company_id,a.company_name,a.isin_code,a.ticker,b.close_price from rsch_sub_area_company_dtls a,stock_current_prices b where a.company_name like SEARCHKEY or a.isin_code like SEARCHKEY or a.ticker like SEARCHKEY and a.company_id=b.stock_id group by a.company_name order by a.company_id;";

    @SuppressWarnings({"unchecked"})
    public String getHomePageSearchHint(String searchKey) throws RuntimeException {
        try {
            String query = SEARCH_HINT_QUERY;

            query = StringUtils.replace(query, "SEARCHKEY", "'%" + searchKey + "%'");
            SQLQuery sqlQuery = commonDao.getNativeQuery(query, null);
            List<Object[]> rows = sqlQuery.list();
            List<CompnyData> companyDataList = new ArrayList<>();
            for (Object[] row : rows) {
                String companyId = row[0] != null ? row[0].toString().trim() : "";
                String companyName = row[1] != null ? row[1].toString().trim() : "";
                String isin = row[2] != null ? row[2].toString().trim() : "";
                String ticker = row[3] != null ? row[3].toString().trim() : "";
                String cmp = row[4] != null ? row[4].toString().trim() : "";
                companyDataList.add(new CompnyData(companyId, companyName, isin, ticker, cmp));
            }
            return convertObjectToJson("searchOutput", companyDataList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertObjectToJson(String key, Object companyDataList) throws IOException {
        Map<String, Object> paramsMap = new LinkedHashMap<>();
        paramsMap.put(key, companyDataList);
        return JsonUtil.createJsonFromParamsMap(paramsMap);
    }
}