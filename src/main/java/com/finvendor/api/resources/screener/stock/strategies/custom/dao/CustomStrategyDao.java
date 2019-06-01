package com.finvendor.api.resources.screener.stock.strategies.custom.dao;

import com.finvendor.common.commondao.ICommonDao;
import com.finvendor.common.util.CommonCodeUtils;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomStrategyDao {

    public static final String QUERY = "select * from strategy_custom";
    @Autowired
    protected ICommonDao commonDao;

    public String findRecordStats(String perPageMaxRecords, String[] mcap, String[] industry, String[] pe, String[] pb,
            String[] debtToEquityRatio, String[] currentRatio, String[] netOperatingCashFlow, String[] roeInPercentage,
            String[] operatingProfitMargin, String[] patGrowthInPercentage, String[] epsGrowthInPercentage,
            String[] revenueGrowthInPercentage, String[] totalFreeCashFlow, String[] returnOnAssetInPercentage, String[] divYield,
            String[] rotcInPercentage){
        SQLQuery query = commonDao.getNativeQuery(QUERY, null);
        return CommonCodeUtils.getRecordStats(perPageMaxRecords, ((List<Object[]>) query.list()).size());
    }
}
