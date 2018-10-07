package com.finvendor.server.bhavprice.dto;

public interface IBhavPriceDetailsDao {
    String getRecordStats(String type, String subType, String perPageMaxRecords) throws RuntimeException;
    String getBhavCopyPriceDetails(String type, String pageNumber, String perPageMaxRecords) throws RuntimeException;
}
