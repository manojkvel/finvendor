package com.finvendor.server.metrics.dao;

import com.finvendor.common.util.Pair;

import java.io.InputStream;

public interface IConsumerAnalyticsDao {

    String getRecordStats(String type, String subType, String perPageMaxRecords) throws RuntimeException;

    String getConsumerAnalytics(String type, String subType, String pageNumber, String perPageMaxRecords, String breachFlag) throws RuntimeException;

    Pair<Long,InputStream> downloadConsumerAnalytics(String type, String subType) throws RuntimeException;
}
