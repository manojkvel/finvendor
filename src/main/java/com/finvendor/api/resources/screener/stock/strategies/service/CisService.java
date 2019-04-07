package com.finvendor.api.resources.screener.stock.strategies.service;

import com.finvendor.api.resources.screener.stock.strategies.dao.AbstractCisDao;
import com.finvendor.api.resources.screener.stock.strategies.dto.AbstractStrategyDto;
import com.finvendor.api.resources.screener.stock.strategies.enums.CisEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CisService {

    @Autowired
    @Qualifier(value = "kennethFisherDao")
    private AbstractCisDao kennethFisherDao;

    /**
     * Find Celebrity Investor Records Stats for given type
     */
    public String findCisRecordStats(CisEnum type, String perPageMaxRecords) {
        String recordStats;
        switch (type) {
        case KENNITH_FISHER:
            recordStats = kennethFisherDao.findCisRecordStats(AbstractCisDao.KENNIT_FISHER_RECORD_STATS_QUERY, perPageMaxRecords);
            break;
        case BENJAMIN_GRAHAM:
            recordStats = kennethFisherDao.findCisRecordStats(AbstractCisDao.BENJAMIN_GRAHAM_RECORD_STATS_QUERY, perPageMaxRecords);
            break;
        default:
            recordStats = "";
        }
        return recordStats;
    }

    /**
     * Find Celebrity Investor Records for given type
     */
    public List<? extends AbstractStrategyDto> findCis(CisEnum type, String pageNumber, String perPageMaxRecords) {
        List<? extends AbstractStrategyDto> strategyResultList = new ArrayList<>();
        switch (type) {
        case KENNITH_FISHER:
            strategyResultList = kennethFisherDao.findCis(AbstractCisDao.KENNIT_FISHER_STRATEGY_QUERY, pageNumber, perPageMaxRecords);
            break;
        case BENJAMIN_GRAHAM:
            break;
        default:
            strategyResultList = null;
        }
        return strategyResultList;
    }
}
