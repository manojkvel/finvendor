package com.finvendor.server.metrics.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.common.util.JsonUtil;
import com.finvendor.server.metrics.dao.IMetricsDao;

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
	
	@Transactional(readOnly = false)
	public void increaseCount(String userName, MetricsType metricsType) {
		
		switch (metricsType) {
		case EQTY_RESEARCH:
			equityResearchReportMetricsDao.increaseCount(userName, metricsType.getValue());
			break;
		case DOWNLOAD_EQTY_RESEARCH:
			downloadEquityResearchReportMetricsDao.increaseCount(userName,metricsType.getValue());
			break;
		}
	}

	@Transactional(readOnly = true)
	public String getRequestMetrics(String type) throws Exception {
		try {
			Map<String, Object> paramsMap = new LinkedHashMap<>();
			paramsMap.put("equityResearchReportHitData", equityResearchReportMetricsDao.getRequestMetrics());
			paramsMap.put("dowloadEquityResearchReportHitData", downloadEquityResearchReportMetricsDao.getRequestMetrics());
			String createJsonFromObject = JsonUtil.createJsonFromObject(paramsMap);
			return createJsonFromObject;
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}
}
