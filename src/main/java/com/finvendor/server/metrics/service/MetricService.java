package com.finvendor.server.metrics.service;

import com.finvendor.common.util.JsonUtil;
import com.finvendor.server.metrics.dao.IMetricsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class MetricService {

    public MetricService() {
    }

    @Autowired
    @Qualifier(value = "equityResearchReportMetricsDaoImpl")
    private IMetricsDao equityResearchReportMetricsDao;

    @Autowired
    @Qualifier(value = "downloadEquityResearchReportMetricsDaoImpl")
    private IMetricsDao downloadEquityResearchReportMetricsDao;

    @Autowired
    @Qualifier(value = "homePageMetricsDaoImpl")
    private IMetricsDao homePageMetricsDao;

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
}
