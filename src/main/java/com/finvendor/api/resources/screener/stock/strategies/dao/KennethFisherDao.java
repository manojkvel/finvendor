package com.finvendor.api.resources.screener.stock.strategies.dao;

import com.finvendor.api.resources.screener.stock.strategies.dto.AbstractStrategyDto;
import com.finvendor.api.resources.screener.stock.strategies.dto.KennethFisherStrategyDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class KennethFisherDao extends AbstractCisDao {

    @Override
    public String findCisRecordStats(String query, String perPageMaxRecords) throws RuntimeException {
        return "{\"firstPageNumber\":1,\"lastPageNumber\":2,\"totalRecords\":2}";
    }

    @Override
    public List<? extends AbstractStrategyDto> findCis(String query, String pageNumber, String perPageMaxRecords) throws RuntimeException {
        List<KennethFisherStrategyDto> resultList=new ArrayList<>();
        KennethFisherStrategyDto dto1=new KennethFisherStrategyDto("1","ABB","7.7","6.6","5.5","4.4","3.3","2.2","1.1","23.2");
        KennethFisherStrategyDto dto2=new KennethFisherStrategyDto("2","ACC","7.7","6.6","5.5","4.4","3.3","2.2","1.1","23.2");
        KennethFisherStrategyDto dto3=new KennethFisherStrategyDto("3","ACC","7.7","6.6","5.5","4.4","3.3","2.2","1.1","23.2");
        KennethFisherStrategyDto dto4=new KennethFisherStrategyDto("4","ACC","7.7","6.6","5.5","4.4","3.3","2.2","1.1","23.2");
        KennethFisherStrategyDto dto5=new KennethFisherStrategyDto("5","ACC","7.7","6.6","5.5","4.4","3.3","2.2","1.1","23.2");
        resultList.add(dto1);
        resultList.add(dto2);
        resultList.add(dto3);
        resultList.add(dto4);
        resultList.add(dto5);
        return resultList;
    }
}
