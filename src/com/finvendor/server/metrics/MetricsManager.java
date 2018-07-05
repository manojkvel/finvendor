package com.finvendor.server.metrics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finvendor.server.metrics.service.IMetricsService;

@Service
public class MetricsManager {

	@Autowired
	private IMetricsService metricsService;

	private MetricsManager() {
	}

	public void increaseCount(FeatureTypeEnum featureTypeEnum) throws Exception {
		metricsService.increaseCount(featureTypeEnum);
	}
}
