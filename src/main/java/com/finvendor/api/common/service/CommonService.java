package com.finvendor.api.common.service;

import com.finvendor.api.common.dao.CommonDao;
import com.finvendor.api.common.dto.StockReturnDto;
import com.finvendor.common.commondao.ICommonDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class CommonService {
    private static final Logger logger = LoggerFactory.getLogger(CommonService.class.getName());
    private final ICommonDao commonDao;
    private final CommonDao dao;

    @Autowired
    public CommonService(ICommonDao commonDao, CommonDao dao) {
        this.commonDao = commonDao;
        this.dao = dao;
    }

    public void insertVo() throws Exception {
        try {
            dao.saveVo();
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public Map<String, String> findStockReturns(StockReturnDto stockReturnDto) throws Exception {
        String isinCode = stockReturnDto.getIsinCode();
        float cmpAsFloat = commonDao.findCmp(stockReturnDto.getIsinCode()).getElement2();
        logger.info("## SERVICE findStockReturns - START cmp:{}, ISIN CODE: {} ",cmpAsFloat, isinCode);
        Map<String, String> stockHistoricalPrices = commonDao.findStockHistoricalPrices(cmpAsFloat, isinCode, true, true, true, true);
        logger.info("## SERVICE findStockReturns - END stockHistoricalPrices: {} ", stockHistoricalPrices);
        return stockHistoricalPrices;
    }
}
