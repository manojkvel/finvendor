package com.finvendor.server.metrics.dao;

import java.util.List;
import java.util.Map;

import com.finvendor.server.metrics.dto.MetricsDto;

public interface IMetricsDao {

    void increaseCount(String userName) throws RuntimeException;

    Map<String, Object> getAllMetrics() throws RuntimeException;

    Map<String, Object>  getYearMetrics() throws RuntimeException;

    Map<String, Object>  getYearMonthMetrics() throws RuntimeException;

    Map<String, Object>  getYearMonthDayMetrics() throws RuntimeException;
}
