package com.finvendor.server.markets.dao;

public interface IMarketsDao {

    String getMarketsRecordStats(String indexFilter,String perPageMaxRecords) throws Exception;


    String getMarkets(String indexFilter, String type, String pageNumber,
                                String perPageMaxRecords) throws Exception;

}
