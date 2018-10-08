package com.finvendor.server.bhavprice.service.impl;

import com.finvendor.server.bhavprice.dao.IBhavPriceDao;
import com.finvendor.server.bhavprice.dto.BhavPriceDto;
import com.finvendor.server.bhavprice.dto.BhavPriceFilter;
import com.finvendor.server.bhavprice.service.IBhavPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BhavPriceServiceImpl implements IBhavPriceService {

    @Autowired
    private IBhavPriceDao dao;

    @Override
    @Transactional(readOnly = true)
    public String getBhavPriceRecordStats(String type, String perPageMaxRecords, BhavPriceFilter bhavPriceFilter)
            throws Exception {
        try {
            String bhavPriceRecordStats = dao.getBhavPriceRecordStats(type, perPageMaxRecords, bhavPriceFilter);
            return bhavPriceRecordStats;
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<BhavPriceDto> getBhavPrices(String type, String order, String pageNumber, String perPageMaxRecords,
                                            BhavPriceFilter bhavPriceFilter) throws Exception {
        try {
            List<BhavPriceDto> bhavPrices = dao.getBhavPrices(type, order, pageNumber, perPageMaxRecords, bhavPriceFilter);
            return bhavPrices;
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }
}
