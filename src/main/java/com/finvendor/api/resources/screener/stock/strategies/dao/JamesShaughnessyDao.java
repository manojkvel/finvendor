package com.finvendor.api.resources.screener.stock.strategies.dao;

import com.finvendor.api.resources.screener.stock.strategies.dto.AbstractStrategyDto;
import com.finvendor.api.resources.screener.stock.strategies.dto.JamesShaughnessyStrategyDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JamesShaughnessyDao extends AbstractCisDao {

    @Override
    public String findCisRecordStats(String query, String perPageMaxRecords) throws RuntimeException {
        return "{\"firstPageNumber\":1,\"lastPageNumber\":2,\"totalRecords\":5}";
    }

    @Override
    public List<? extends AbstractStrategyDto> findCis(String query, String pageNumber, String perPageMaxRecords) throws RuntimeException {
        List<JamesShaughnessyStrategyDto> resultList=new ArrayList<>();
        JamesShaughnessyStrategyDto dto1=new JamesShaughnessyStrategyDto("1","Company1","1.1","2.2","3.3","4.4","5.5","6.6");
        JamesShaughnessyStrategyDto dto2=new JamesShaughnessyStrategyDto("2","Company2","1.1","2.2","3.3","4.4","5.5","6.6");
        JamesShaughnessyStrategyDto dto3=new JamesShaughnessyStrategyDto("3","Company3","1.1","2.2","3.3","4.4","5.5","6.6");
        JamesShaughnessyStrategyDto dto4=new JamesShaughnessyStrategyDto("4","Company4","1.1","2.2","3.3","4.4","5.5","6.6");
        JamesShaughnessyStrategyDto dto5=new JamesShaughnessyStrategyDto("5","Company5","1.1","2.2","3.3","4.4","5.5","6.6");
        resultList.add(dto1);
        resultList.add(dto2);
        resultList.add(dto3);
        resultList.add(dto4);
        resultList.add(dto5);
        return resultList;
    }
}
