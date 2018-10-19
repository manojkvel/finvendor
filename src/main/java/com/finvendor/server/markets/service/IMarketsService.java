package com.finvendor.server.markets.service;

public interface IMarketsService {

    String getMarketsRecordStats(String indexFilter, String perPageMaxRecords) throws Exception;


    String getMarkets(String indexFilter, String type, String pageNumber,
                                String perPageMaxRecords) throws Exception;

}