package com.finvendor.server.markets.service.impl;

import com.finvendor.server.markets.dao.IMarketsDao;
import com.finvendor.server.markets.service.IMarketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MarketsServiceImpl implements IMarketsService {

    @Autowired
    private IMarketsDao dao;

    @Override
    @Transactional(readOnly = true)
    public String getMarketsRecordStats(String indexFilter, String perPageMaxRecords)
            throws Exception {
        try {
            String bhavPriceRecordStats = dao.getMarketsRecordStats(indexFilter, perPageMaxRecords);
            return bhavPriceRecordStats;
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public String getMarkets(String indexFilter, String type, String pageNumber,
                                       String perPageMaxRecords) throws Exception {
        try {
            return dao.getMarkets(indexFilter, type, pageNumber, perPageMaxRecords);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }
}
