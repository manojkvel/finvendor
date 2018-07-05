package com.finvendor.server.metrics.dao;

import com.finvendor.server.metrics.FeatureTypeEnum;

public interface IMetricsDao {

	void increaseCount(FeatureTypeEnum featureTypeEnum) throws RuntimeException;
}
