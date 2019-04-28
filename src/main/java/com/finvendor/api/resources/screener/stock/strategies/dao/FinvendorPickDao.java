package com.finvendor.api.resources.screener.stock.strategies.dao;

import com.finvendor.api.resources.screener.stock.strategies.dto.AbstractStrategyDto;
import com.finvendor.api.resources.screener.stock.strategies.dto.FinvendorPickStrategyDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FinvendorPickDao extends AbstractCisDao {

    @Override
    public String findCisRecordStats(String perPageMaxRecords) throws RuntimeException {
        return "{\"firstPageNumber\":1,\"lastPageNumber\":2,\"totalRecords\":5}";
    }

    @Override
    public List<? extends AbstractStrategyDto> findCis(String pageNumber, String perPageMaxRecords) throws RuntimeException {
        List<FinvendorPickStrategyDto> resultList=new ArrayList<>();

//        resultList.add(dto1);
//        resultList.add(dto2);
//        resultList.add(dto3);
//        resultList.add(dto4);
//        resultList.add(dto5);
        return resultList;
    }
}
