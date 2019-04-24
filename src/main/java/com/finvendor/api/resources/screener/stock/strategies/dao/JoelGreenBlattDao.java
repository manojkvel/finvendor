package com.finvendor.api.resources.screener.stock.strategies.dao;

import com.finvendor.api.resources.screener.stock.strategies.dto.AbstractStrategyDto;
import com.finvendor.api.resources.screener.stock.strategies.dto.JeolGreenblattStrategyDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JoelGreenBlattDao extends AbstractCisDao {

    @Override
    public String findCisRecordStats(String query, String perPageMaxRecords) throws RuntimeException {
        return "{\"firstPageNumber\":1,\"lastPageNumber\":2,\"totalRecords\":5}";
    }

    @Override
    public List<? extends AbstractStrategyDto> findCis(String query, String pageNumber, String perPageMaxRecords) throws RuntimeException {
        List<JeolGreenblattStrategyDto> resultList=new ArrayList<>();
        JeolGreenblattStrategyDto dto1=new JeolGreenblattStrategyDto("1","Company1","","1.1","2.2","3.3","4.4","5.5","6.6");
        JeolGreenblattStrategyDto dto2=new JeolGreenblattStrategyDto("1","Company1","","1.1","2.2","3.3","4.4","5.5","6.6");
        JeolGreenblattStrategyDto dto3=new JeolGreenblattStrategyDto("1","Company1","","1.1","2.2","3.3","4.4","5.5","6.6");
        JeolGreenblattStrategyDto dto4=new JeolGreenblattStrategyDto("1","Company1","","1.1","2.2","3.3","4.4","5.5","6.6");
        JeolGreenblattStrategyDto dto5=new JeolGreenblattStrategyDto("1","Company1","","1.1","2.2","3.3","4.4","5.5","6.6");
        resultList.add(dto1);
        resultList.add(dto2);
        resultList.add(dto3);
        resultList.add(dto4);
        resultList.add(dto5);
        return resultList;
    }
}
