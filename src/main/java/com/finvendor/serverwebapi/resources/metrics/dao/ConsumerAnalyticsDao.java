package com.finvendor.serverwebapi.resources.metrics.dao;

import com.finvendor.common.util.CommonCodeUtil;
import com.finvendor.common.util.JsonUtil;
import com.finvendor.common.util.Pair;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.serverwebapi.resources.metrics.dto.EquityResearchAnalyticsDto;
import org.apache.commons.io.FileUtils;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class ConsumerAnalyticsDao {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerAnalyticsDao.class.getName());
    /**
     * RF-Research Filter
     */
    private static final String RF_QUERY = "select a.user_name, b.registration_date, b.last_login, a.ip_address, sum(a.count) rf_count, if(sum(a.count)>15,'y','n') rf_breach  from eqty_research_report_metrics a, users b where a.user_name=b.username group by a.user_name CONDITION";

    /**
     * D-Report Download
     */
    private static final String D_QUERY = "select a.user_name,b.registration_date, b.last_login, a.ip_address,sum(a.count) d_count, if(sum(a.count)>8,'y','n') d_breach from download_eqty_research_report_metrics a, users b where a.user_name=b.username group by a.user_name CONDITION";
    public static final String NA = "NA";

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");

    @Resource(name = "finvendorProperties")
    private Properties finvendorProperties;

    @Autowired
    ICommonDao commonDao;

    public String getRecordStats(String type, String subType, String perPageMaxRecords, String breachFlag) throws RuntimeException {
        String statsJson;
        try {
            String mainQuery = applyFilter(subType, breachFlag);
            SQLQuery query1 = commonDao.getNativeQuery(mainQuery, null);

            List<Object[]> rows = query1.list();
            long actualTotalRecords = rows.size();

            if (actualTotalRecords != 0L) {
                // Calculate Last page number
                long lastPageNumber = CommonCodeUtil.calculatePaginationLastPage(perPageMaxRecords, actualTotalRecords);
                statsJson = CommonCodeUtil.getRecordStatsJson(actualTotalRecords, lastPageNumber);
            } else {
                statsJson = "0";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return statsJson;
    }

    public String getConsumerAnalytics(String type, String subType, String pageNumber, String perPageMaxRecords, String breachFlag) throws RuntimeException {
        String mainQuery = applyFilter(subType, breachFlag);
        String mainQueryWithPagintion = mainQuery + CommonCodeUtil.applyPagination(pageNumber, perPageMaxRecords);
        SQLQuery query1 = commonDao.getNativeQuery(mainQueryWithPagintion, null);
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
                String hitCount = row[4] != null ? row[4].toString().trim() : "";
                String breachFlagFromDB = row[5] != null ? row[5].toString().trim() : "";

                EquityResearchAnalyticsDto dto = new EquityResearchAnalyticsDto();
                dto.setUserName(userName);
                dto.setRegistrationDate(!StringUtils.isEmpty(registrationDate) ? String.valueOf(formatter.parse(registrationDate).getTime()) : "NA");
                dto.setLastLogin(!StringUtils.isEmpty(lastLogin) ? String.valueOf(formatter.parse(lastLogin).getTime()) : "NA");
                dto.setIpAddress(ipAddress);
                dto.setHitCount(String.valueOf((int) Float.parseFloat(hitCount)));
                dto.setBreachFlag(breachFlagFromDB);
                equityResearchAnalyticsDtoList.add(dto);
            }
            resultMap.put("ConsumerAnalytics", equityResearchAnalyticsDtoList);
            resultString = JsonUtil.createJsonFromParamsMap(resultMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultString;
    }

    private String applyFilter(String subType, String breachFlag) {
        String mainQuery;
        if ("rf".equals(subType)) {
            mainQuery = RF_QUERY;
            if ("y".equals(breachFlag)) {
                mainQuery = StringUtils.replace(mainQuery, "CONDITION", " having rf_count>15 ");
            }
            if ("n".equals(breachFlag)) {
                mainQuery = StringUtils.replace(mainQuery, "CONDITION", " having rf_count<=15 ");
            }
            if ("all".equals(breachFlag)) {
                mainQuery = StringUtils.replace(mainQuery, "CONDITION", "");
            }
        } else {
            mainQuery = D_QUERY;
            if ("y".equals(breachFlag)) {
                mainQuery = StringUtils.replace(mainQuery, "CONDITION", " having d_count>8 ");
            }
            if ("n".equals(breachFlag)) {
                mainQuery = StringUtils.replace(mainQuery, "CONDITION", " having d_count<=8 ");
            }
            if ("all".equals(breachFlag)) {
                mainQuery = StringUtils.replace(mainQuery, "CONDITION", "");
            }
        }
        return mainQuery;
    }

    public Pair<Long, InputStream> downloadConsumerAnalytics(String type, String subType) throws RuntimeException {
        String mainQuery = applyFilter(subType, "all");
        SQLQuery query1 = commonDao.getNativeQuery(mainQuery, null);
        List<Object[]> rows = query1.list();
        String fileName = finvendorProperties.getProperty("metrics_download_path");

        File csvFile = new File(fileName);
        FileWriter fw = null;
        try {
            fw = new FileWriter(csvFile);
            fw.append("UserID");
            fw.append(',');
            fw.append("RegistrationDate");
            fw.append(',');
            fw.append("LastLogin");
            fw.append(',');
            fw.append("IpAddress");
            fw.append(',');
            fw.append("HitCount");
            fw.append(',');
            fw.append("BreachFlag");

            fw.append('\n');

            for (Object[] row : rows) {
                String userName = row[0] != null ? row[0].toString().trim() : NA;
                String registrationDate = row[1] != null ? row[1].toString().trim() : NA;
                String lastLogin = row[2] != null ? row[2].toString().trim() : NA;
                String ipAddress = row[3] != null ? row[3].toString().trim() : NA;
                String hitCount = row[4] != null ? row[4].toString().trim() : NA;
                String breachFlag = row[5] != null ? row[5].toString().trim() : NA;
                fw.append(userName);
                fw.append(',');
                fw.append(!registrationDate.equals(NA) ? registrationDate.substring(0, registrationDate.indexOf(" ")) : NA);
                fw.append(',');
                fw.append(!lastLogin.equals(NA) ? lastLogin.substring(0, lastLogin.indexOf(" ")) : NA);
                fw.append(',');
                fw.append(ipAddress);
                fw.append(',');
                fw.append(String.valueOf((int) Float.parseFloat(hitCount)));
                fw.append(',');
                fw.append(breachFlag);
                fw.append('\n');
            }
        } catch (Exception e) {
            throw new RuntimeException("Error has occurred while downloading customer analytics data for equity research", e);
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    logger.error("Unable to close FileWriter while downloading customer analytics data for equity research");
                }
            } else {
                logger.error("FileWriter found null while downloading customer analytics data for equity research");
            }
        }
        try {
            long length = csvFile.length();
            InputStream inputStream = FileUtils.openInputStream(csvFile);
            return new Pair<>(length, inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Error has occurred while downloading customer analytics data for equity research", e);
        }
    }
}
