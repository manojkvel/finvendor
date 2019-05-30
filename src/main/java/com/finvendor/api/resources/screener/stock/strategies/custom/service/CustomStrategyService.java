package com.finvendor.api.resources.screener.stock.strategies.custom.service;

import com.finvendor.api.resources.screener.stock.strategies.custom.dao.CustomStrategyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomStrategyService {

    @Autowired
    private CustomStrategyDao dao;
    public String findRecorsStats(String perPageMaxRecords, String[] mcap, String[] industry, String[] pe, String[] pb,
            String[] debtToEquityRatio, String[] currentRatio, String[] netOperatingCashFlow, String[] roeInPercentage,
            String[] operatingProfitMargin, String[] patGrowthInPercentage, String[] epsGrowthInPercentage,
            String[] revenueGrowthInPercentage, String[] totalFreeCashFlow, String[] returnOnAssetInPercentage, String[] divYield,
            String[] rotcInPercentage) throws Exception {
        try{
            return  dao.findRecordStats(perPageMaxRecords,mcap,industry,pe,pb,debtToEquityRatio,
                    currentRatio,netOperatingCashFlow,roeInPercentage,operatingProfitMargin,patGrowthInPercentage,epsGrowthInPercentage,revenueGrowthInPercentage,
                    totalFreeCashFlow,returnOnAssetInPercentage,divYield,rotcInPercentage );
        }catch (RuntimeException e){
            throw new Exception(e);
        }
    }
}
