package com.finvendor.server.metrics.service;

import com.finvendor.server.metrics.FeatureTypeEnum;

public interface IMetricsService {
	void increaseCount(FeatureTypeEnum featureType) throws Exception;
}
