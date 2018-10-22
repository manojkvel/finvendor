package com.finvendor.server.markets.service;

public interface IMarketsService {

    String getIndexNames() throws Exception;

    String getIndexSummary(String indexFilter) throws Exception;

    String getMarketsAnalytics(String indexFilter, String type) throws Exception;

    String getMarketsRecordStats(String indexFilter, String perPageMaxRecords) throws Exception;


    String getMarkets(String indexFilter, String type, String pageNumber,
                      String perPageMaxRecords) throws Exception;

}