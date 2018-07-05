package com.finvendor.server.metrics.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finvendor.model.Metrics;
import com.finvendor.server.common.commondao.GenericDao;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.server.metrics.FeatureTypeEnum;
import com.finvendor.server.metrics.dao.IMetricsDao;

@Repository
public class MetricsDaoImpl extends GenericDao<Metrics> implements IMetricsDao {

	@Autowired
	private ICommonDao commonDao;

	@Override
	public void increaseCount(FeatureTypeEnum featureTypeEnum) throws RuntimeException {

	}

}
