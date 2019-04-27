package com.finvendor.api.resources.screener.stock.strategies.dao;

import com.finvendor.api.resources.screener.stock.strategies.dto.AbstractStrategyDto;
import com.finvendor.common.commondao.ICommonDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractCisDao {

    @Autowired
    protected ICommonDao commonDao;

    public static final String KENNIT_FISHER_RECORD_STATS_QUERY = "";
    public static final String KENNIT_FISHER_STRATEGY_QUERY = "";

    public static final String BENJAMIN_GRAHAM_RECORD_STATS_QUERY = "";
    public static final String BENJAMIN_GRAHAM_STRATEGY_QUERY = "";

    public static final String JAMES_SHAUGHNESSY_RECORD_STATS_QUERY = "";
    public static final String JAMES_SHAUGHNESSY_STRATEGY_QUERY = "";

    public static final String JOEL_GREENBLATT_RECORD_STATS_QUERY = "";
    public static final String JOEL_GREENBLATT_STRATEGY_QUERY = "";

    public abstract String findCisRecordStats(String query, String perPageMaxRecords) throws RuntimeException;

    public abstract List<? extends AbstractStrategyDto> findCis(String query, String pageNumber, String perPageMaxRecords) throws RuntimeException;
}
