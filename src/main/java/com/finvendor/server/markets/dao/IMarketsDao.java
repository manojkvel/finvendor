package com.finvendor.server.markets.dao;

public interface IMarketsDao {

    String getIndexNames() throws Exception;

    String getIndexSummary(String indexName) throws Exception;

    String getMarketsAnalytics(String indexFilter, String type) throws Exception;

    String getMarketsRecordStats(String indexFilter,String perPageMaxRecords) throws Exception;


    String getMarkets(String indexFilter, String type, String pageNumber,
                                String perPageMaxRecords) throws Exception;

}
