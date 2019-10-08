package com.finvendor.api.common.service;

import com.finvendor.api.common.dao.CommonDao;
import com.finvendor.api.screener.stock.recommendation.dto.StockReturnDto;
import com.finvendor.common.commondao.ICommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class CommonService {

    private final ICommonDao commonDao;
    private final CommonDao dao;

    @Autowired
    public CommonService(ICommonDao commonDao, CommonDao dao) {
        this.commonDao = commonDao;
        this.dao = dao;
    }

    public Map<String, String> findStockReturns(StockReturnDto stockReturnDto) throws Exception {
        float cmpAsFloat = Float.parseFloat(stockReturnDto.getCmp());
        return commonDao.findStockHistoricalPrices(cmpAsFloat, stockReturnDto.getIsinCode(), true, true, true, true);
    }

    public void insertVo() throws Exception {
        try {
            dao.saveVo();
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }
}
