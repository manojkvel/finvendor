package com.finvendor.server.metrics.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.server.metrics.dao.IMetricsDao;
import com.finvendor.server.metrics.dto.MetricsDto;

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
	public Collection<MetricsDto> getRequestMetrics(String type) throws Exception {
		try {
			List<MetricsDto> eqtyMetrics=equityResearchReportMetricsDao.getRequestMetrics();
			eqtyMetrics.addAll(downloadEquityResearchReportMetricsDao.getRequestMetrics());
			return eqtyMetrics;
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}
}
