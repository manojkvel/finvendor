package com.finvendor.api.resources.screener.stock.strategies.dao;

import com.finvendor.api.resources.screener.stock.strategies.dto.AbstractStrategyDto;
import com.finvendor.api.resources.screener.stock.strategies.dto.BenjaminGrahamStrategyDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BenjaminGrahamDao extends AbstractCisDao {

    @Override
    public String findCisRecordStats(String query, String perPageMaxRecords) throws RuntimeException {
        return "{\"firstPageNumber\":1,\"lastPageNumber\":2,\"totalRecords\":2}";
    }

    @Override
    public List<? extends AbstractStrategyDto> findCis(String query, String pageNumber, String perPageMaxRecords) throws RuntimeException {
        List<BenjaminGrahamStrategyDto> resultList=new ArrayList<>();
        BenjaminGrahamStrategyDto dto1=new BenjaminGrahamStrategyDto("1","ABB","1.1","2.2","3.3",true,"5.5","6.6","7.7");
        BenjaminGrahamStrategyDto dto2=new BenjaminGrahamStrategyDto("2","ACC","1.1","2.2","3.3",true,"5.5","6.6","7.7");
        resultList.add(dto1);
        resultList.add(dto2);
        return resultList;
    }
}
