package com.finvendor.server.bhavprice.service;

import com.finvendor.server.bhavprice.dto.BhavPriceDto;
import com.finvendor.server.bhavprice.dto.BhavPriceFilter;

import java.util.List;

public interface IBhavPriceService {

    String getBhavPriceRecordStats(String type,
                                               String perPageMaxRecords,
                                               BhavPriceFilter bhavPriceFilter) throws Exception;


    List<BhavPriceDto> getBhavPrices(String type, String order, String pageNumber,
                                     String perPageMaxRecords, BhavPriceFilter bhavPriceFilter) throws Exception;

}