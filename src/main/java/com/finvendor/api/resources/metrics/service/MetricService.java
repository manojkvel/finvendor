package com.finvendor.api.resources.metrics.service;

import com.finvendor.common.util.JsonUtil;
import com.finvendor.common.util.Pair;
import com.finvendor.api.resources.metrics.dao.ConsumerAnalyticsDao;
import com.finvendor.api.resources.metrics.dao.DownloadEquityResearchReportMetricsDao;
import com.finvendor.api.resources.metrics.dao.EquityResearchReportMetricsDao;
import com.finvendor.api.resources.metrics.dao.HomePageMetricsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class MetricService {

    @Autowired
    private EquityResearchReportMetricsDao equityResearchReportMetricsDao;

    @Autowired
    private DownloadEquityResearchReportMetricsDao downloadEquityResearchReportMetricsDao;

    @Autowired
    private HomePageMetricsDao homePageMetricsDao;

    @Autowired
    ConsumerAnalyticsDao iConsumerAnalyticsDao;

    @Transactional(readOnly = false)
    public void increaseCount(String userName, MetricsType metricsType, String clientIp) {

        switch (metricsType) {
            case EQTY_RESEARCH:
                equityResearchReportMetricsDao.increaseCount(userName, clientIp);
                break;
            case DOWNLOAD_EQTY_RESEARCH:
                downloadEquityResearchReportMetricsDao.increaseCount(userName, clientIp);
                break;
            case HOME_PAGE:
                homePageMetricsDao.increaseCount(userName, clientIp);
                break;

        }
    }

    @Transactional(readOnly = true)
    public String getRequestMetrics(String type) throws Exception {
        try {
            Map<String, Object> paramsMap = new LinkedHashMap<>();
            if (type.equals("equityResearch")) {
                paramsMap.put("equityResearchReportMetrics", equityResearchReportMetricsDao.getAllMetrics());
            } else if (type.equals("downloadEquityResearch")) {
                paramsMap.put("downloadEquityResearchReportMetrics", downloadEquityResearchReportMetricsDao.getAllMetrics());
            } else if (type.equals("homePage")) {
                paramsMap.put("homePageMetrics", homePageMetricsDao.getAllMetrics());
            } else {
                Map<String, Object> allMap = new LinkedHashMap<>();
                allMap.put("homePage", homePageMetricsDao.getAllMetrics());
                allMap.put("equityResearchReport", equityResearchReportMetricsDao.getAllMetrics());
                allMap.put("downloadEquityResearchReport", downloadEquityResearchReportMetricsDao.getAllMetrics());
                paramsMap.put("metrics", allMap);
            }
            String createJsonFromObject = JsonUtil.createJsonFromObject(paramsMap);
            return createJsonFromObject;
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    @Transactional(readOnly = true)
    public String getConsumerAnalyticsRecordStats(String type, String subType, String perPageMaxRecords,String breachFlag) throws Exception {
        String consumerAnalyticsRecordStats = "";
        try {
            if ("equity".equals(type)) {
                consumerAnalyticsRecordStats = iConsumerAnalyticsDao.getRecordStats(type, subType, perPageMaxRecords,breachFlag);
            }
            return consumerAnalyticsRecordStats;
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    @Transactional(readOnly = true)
    public String getConsumerAnalytics(String type, String subType, String pageNumber, String perPageMaxRecords,String breachFlag) throws Exception {
        String consumerAnalytics = "";
        try {
            if ("equity".equals(type)) {
                consumerAnalytics = iConsumerAnalyticsDao.getConsumerAnalytics(type, subType, pageNumber, perPageMaxRecords, breachFlag);
            }
            return consumerAnalytics;
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    @Transactional(readOnly = true)
    public Pair<Long, InputStream> downloadConsumerAnalytics(String type, String subType) throws Exception {
        Pair<Long, InputStream> customerAnalyticsDownloadData = null;
        try {
            if ("equity".equals(type)) {
                customerAnalyticsDownloadData = iConsumerAnalyticsDao.downloadConsumerAnalytics(type, subType);
            }
            return customerAnalyticsDownloadData;
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }
}
