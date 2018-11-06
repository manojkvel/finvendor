//package com.finvendor.server.markets.service.impl;
//
//import com.finvendor.server.markets.dao.IMarketsDao;
//import com.finvendor.server.markets.service.IMarketsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//public class MarketsServiceImpl implements IMarketsService {
//
//    @Autowired
//    private IMarketsDao dao;
//
//    @Override
//    @Transactional(readOnly = true)
//    public String getIndexNames() throws Exception {
//        try {
//            return dao.getIndexNames();
//        } catch (RuntimeException e) {
//            throw new Exception(e);
//        }
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public String getIndexSummary(String indexFilter) throws Exception {
//        try {
//            return dao.getIndexSummary(indexFilter);
//        } catch (RuntimeException e) {
//            throw new Exception(e);
//        }
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public String getMarketsAnalytics(String indexFilter, String type) throws Exception {
//        try {
//            return dao.getMarketsAnalytics(indexFilter,type);
//        } catch (RuntimeException e) {
//            throw new Exception(e);
//        }
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public String getMarketsRecordStats(String indexFilter, String perPageMaxRecords) throws Exception {
//        try {
//            return dao.getMarketsRecordStats(indexFilter, perPageMaxRecords);
//        } catch (RuntimeException e) {
//            throw new Exception(e);
//        }
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public String getMarkets(String indexFilter, String type, String pageNumber,
//                                       String perPageMaxRecords) throws Exception {
//        try {
//            return dao.getMarkets(indexFilter, type, pageNumber, perPageMaxRecords);
//        } catch (RuntimeException e) {
//            throw new Exception(e);
//        }
//    }
//}
