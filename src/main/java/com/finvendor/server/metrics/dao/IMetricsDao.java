package com.finvendor.server.metrics.dao;

import java.util.List;

import com.finvendor.server.metrics.dto.MetricsDto;

public interface IMetricsDao {

	void increaseCount(String userName,String request) throws RuntimeException;

	List<MetricsDto> getRequestMetrics() throws RuntimeException;
}
