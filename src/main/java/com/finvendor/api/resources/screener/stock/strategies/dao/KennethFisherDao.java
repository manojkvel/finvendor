package com.finvendor.api.resources.screener.stock.strategies.dao;

import com.finvendor.api.resources.screener.stock.strategies.dto.AbstractStrategyDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KennethFisherDao extends AbstractCisDao {

    @Override
    public String findCisRecordStats(String query, String perPageMaxRecords) throws RuntimeException {
        return null;
    }

    @Override
    public List<? extends AbstractStrategyDto> findCis(String query, String pageNumber, String perPageMaxRecords) throws RuntimeException {
        return null;
    }
}
