//package com.finvendor.api.screener.stock.recommendation.service;
//
//import com.finvendor.common.commondao.ICommonDao;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Transactional
//public class StockReturnService {
//    private static final Logger logger = LoggerFactory.getLogger(StockReturnService.class.getName());
//    private final ICommonDao commonDao;
//
//    @Autowired
//    public StockReturnService(ICommonDao commonDao) {
//        this.commonDao = commonDao;
//    }
//
////    public Map<String, String> findStockReturns(StockReturnDto stockReturnDto) throws Exception {
////        String isinCode = stockReturnDto.getIsinCode();
////        float cmpAsFloat = commonDao.findCmp(stockReturnDto.getIsinCode()).getElement2();
////        logger.info("## SERVICE findStockReturns - START cmp:{}, ISIN CODE: {} ",cmpAsFloat, isinCode);
////        Map<String, String> stockHistoricalPrices = commonDao.findStockHistoricalPrices(cmpAsFloat, isinCode, true, true, true, true);
////        logger.info("## SERVICE findStockReturns - END stockHistoricalPrices: {} ", stockHistoricalPrices);
////        return stockHistoricalPrices;
////    }
//}
