package com.finvendor.server.bhavprice.dao.impl;

import com.finvendor.common.util.CommonUtil;
import com.finvendor.server.bhavprice.dao.IBhavPriceDao;
import com.finvendor.server.bhavprice.dto.BhavPriceDto;
import com.finvendor.server.bhavprice.dto.BhavPriceFilter;
import com.finvendor.server.common.commondao.ICommonDao;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BhavPriceDaoImpl implements IBhavPriceDao {
    private static final Logger logger = LoggerFactory.getLogger(BhavPriceDaoImpl.class.getName());

    private static final String BHAV_PRICE_QUERY="";

    @Autowired
    private ICommonDao commonDao;


    @Override
    public String getBhavPriceRecordStats(String type, String perPageMaxRecords, BhavPriceFilter bhavPriceFilter) throws Exception {
        String recordStatsJson;
        long totalRecords=0L;

        SQLQuery query1 = commonDao.getNativeQuery(BHAV_PRICE_QUERY, null);

        List<Object[]> rows = query1.list();
        totalRecords=rows.size();
        if (totalRecords != 0L) {
            long lastPageNumber = CommonUtil.calculatePaginationLastPage(perPageMaxRecords, totalRecords);
            recordStatsJson = CommonUtil.getRecordStatsJson(totalRecords, lastPageNumber);
        } else {
            recordStatsJson = "NA";
        }
        return recordStatsJson;
    }


    @Override
    public List<BhavPriceDto> getBhavPrices(String type, String order, String pageNumber, String perPageMaxRecords, BhavPriceFilter bhavPriceFilter) throws Exception {
        return null;
    }
}
