package com.finvendor.server.homepage.dao.impl;

import com.finvendor.common.util.JsonUtil;
import com.finvendor.model.CompanyWatchList;
import com.finvendor.server.common.commondao.GenericDao;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.server.homepage.dao.IHomePageSearchDao;
import com.finvendor.server.homepage.dto.CompnyData;
import com.finvendor.server.researchreport.dao.IResearchReportDao;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class HomePageSearchDaoImpl extends GenericDao<CompanyWatchList> implements IHomePageSearchDao {
    private static final Logger logger = LoggerFactory.getLogger(HomePageSearchDaoImpl.class.getName());

    @Autowired
    private ICommonDao commonDao;

    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired
    @Qualifier(value = "equityResearchDaoImpl")
    private IResearchReportDao equityDao;

    @SuppressWarnings({"unchecked"})
    @Override
    public String getHomePageSearchHint(String searchKey) throws RuntimeException {
        try {
            String query = IHomePageSearchDao.SEARCH_HINT_QUERY;

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
