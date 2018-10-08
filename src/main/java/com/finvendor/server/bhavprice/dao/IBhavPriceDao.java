package com.finvendor.server.bhavprice.dao;

import com.finvendor.server.bhavprice.dto.BhavPriceDto;
import com.finvendor.server.bhavprice.dto.BhavPriceFilter;

import java.util.List;

public interface IBhavPriceDao {

    String getBhavPriceRecordStats(String type,
                                   String perPageMaxRecords,
                                   BhavPriceFilter bhavPriceFilter) throws Exception;


    List<BhavPriceDto> getBhavPrices(String type, String order, String pageNumber,
                                     String perPageMaxRecords, BhavPriceFilter bhavPriceFilter) throws Exception;

}
