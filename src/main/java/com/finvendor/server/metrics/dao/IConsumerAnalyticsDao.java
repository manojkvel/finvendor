package com.finvendor.server.metrics.dao;

public interface IConsumerAnalyticsDao {

    String getRecordStats(String perPageMaxRecords) throws RuntimeException;

    String getConsumerAnalytics(String type, String pageNumber, String perPageMaxRecords) throws RuntimeException;
}
