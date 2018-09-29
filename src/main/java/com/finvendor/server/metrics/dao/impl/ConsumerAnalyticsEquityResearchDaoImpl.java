package com.finvendor.server.metrics.dao.impl;

import com.finvendor.common.util.CommonUtil;
import com.finvendor.common.util.JsonUtil;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.server.metrics.dao.IConsumerAnalyticsDao;
import com.finvendor.server.metrics.dto.EquityResearchAnalyticsDto;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ConsumerAnalyticsEquityResearchDaoImpl implements IConsumerAnalyticsDao {
    private static final String COUNT_QUERY = "SELECT count(a.user_name) FROM eqty_research_report_metrics a where a.user_name != 'UNKNOWN' group by a.user_name";
    private static final String RF_QUERY = "select a.user_name,b.registration_date,b.last_login, a.ip_address,sum(a.count) rf_count, if(sum(a.count)>15,'Y','N') RF_Breach from eqty_research_report_metrics a, users b where a.user_name=b.username group by a.user_name";
    private static final String D_COUNT_QUERY = "SELECT a.user_name,sum(a.count) d_count,if(sum(a.count)>8,'Y','N') D_Breach FROM download_eqty_research_report_metrics a where a.user_name=? group by a.user_name";

    @Autowired
    ICommonDao commonDao;

    @Override
    public String getRecordStats(String perPageMaxRecords) throws RuntimeException {
        int totalRecords;
        String statsJson;
        try {
            SQLQuery query1 = commonDao.getNativeQuery(COUNT_QUERY, null);

            List<Object[]> rows = query1.list();
            if (rows != null) {
                totalRecords = rows.size();

                // Calculate Last page number
                long lastPageNumber = CommonUtil.calculatePaginationLastPage(perPageMaxRecords, totalRecords);

                // Prepare Json result
                Map<String, Object> paramsMap = new LinkedHashMap<>();
                paramsMap.put("firstPageNumber", 1);
                paramsMap.put("lastPageNumber", lastPageNumber);
                paramsMap.put("totalRecords", totalRecords);
                statsJson = JsonUtil.createJsonFromObject(paramsMap);
            } else {
                statsJson = "0";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return statsJson;
    }


    @Override
    public String getConsumerAnalytics(String type, String pageNumber, String perPageMaxRecords) throws RuntimeException {
        String mainQuery = RF_QUERY + CommonUtil.applyPagination(pageNumber, perPageMaxRecords);
        SQLQuery query1 = commonDao.getNativeQuery(mainQuery, null);
        List<Object[]> rows = query1.list();
        String resultString = "NA";
        Map<String, Object> resultMap = new HashMap<>();
        List<EquityResearchAnalyticsDto> equityResearchAnalyticsDtoList = new ArrayList<>();
        try {
            for (Object[] row : rows) {
                String userName = row[0] != null ? row[0].toString().trim() : "";
                String registrationDate = row[1] != null ? row[1].toString().trim() : "";
                String lastLogin = row[2] != null ? row[2].toString().trim() : "";
                String ipAddress = row[3] != null ? row[3].toString().trim() : "";
                String rfCount = row[4] != null ? row[4].toString().trim() : "";
                String rfBreach = row[5] != null ? row[5].toString().trim() : "";
                EquityResearchAnalyticsDto dto = new EquityResearchAnalyticsDto();
                dto.setUserName(userName);
                dto.setRegistrationDate(registrationDate);
                dto.setLastLogin(lastLogin);
                dto.setIpAddress(ipAddress);
                dto.setRfCount(rfCount);
                dto.setRfBreach(rfBreach);
                query1 = commonDao.getNativeQuery(D_COUNT_QUERY, new String[]{userName});
                rows = query1.list();
                for (Object[] row1 : rows) {
                    String dUserName = row1[0] != null ? row1[0].toString().trim() : "";
                    String dCount = row1[1] != null ? row1[1].toString().trim() : "";
                    String dBreach = row1[2] != null ? row1[2].toString().trim() : "";
                    if (userName.equals(dUserName)) {
                        dto.setdCount(dCount);
                        dto.setdBreach(dBreach);
                    }
                }
                equityResearchAnalyticsDtoList.add(dto);
            }
            resultMap.put("ConsumerAnalytics", equityResearchAnalyticsDtoList);
            resultString = JsonUtil.createJsonFromParamsMap(resultMap);
            ;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultString;
    }
}
