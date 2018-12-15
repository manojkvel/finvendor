package com.finvendor.api.resources.markets.service;

import com.finvendor.api.resources.markets.dao.MarketsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MarketsService {

    @Autowired
    private MarketsDao dao;


    public String getIndexNames() throws Exception {
        try {
            return dao.getIndexNames();
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public String getIndexSummary(String indexFilter) throws Exception {
        try {
            return dao.getIndexSummary(indexFilter);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public String getMarketsAnalytics(String indexFilter, String type) throws Exception {
        try {
            return dao.getMarketsAnalytics(indexFilter, type);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public String getMarketsRecordStats(String indexFilter,String type,  String perPageMaxRecords) throws Exception {
        try {
            return dao.getMarketsRecordStats(indexFilter,type, perPageMaxRecords);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public String getMarkets(String indexFilter, String type, String pageNumber,
                             String perPageMaxRecords,String sortBy,String orderBy) throws Exception {
        try {
            return dao.getMarkets(indexFilter, type, pageNumber, perPageMaxRecords,sortBy,orderBy);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public String getStockMarqueeDataForIndex(List<String> indexName) throws Exception {
        try {
            return dao.getStockMarqueeDataForIndex(indexName);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public String getIndexMarqueeData() throws Exception {
        try {
            return dao.getIndexMarqueeData();
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }
}
