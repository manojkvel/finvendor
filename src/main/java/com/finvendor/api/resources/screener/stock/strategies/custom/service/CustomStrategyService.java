package com.finvendor.api.resources.screener.stock.strategies.custom.service;

import com.finvendor.api.resources.screener.stock.strategies.custom.dao.CustomStrategyDao;
import com.finvendor.common.util.Pair;
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

    //MIN MAX
    public Pair findMcapFilter() {
        return dao.findMcapFilter();
    }

    //MIN MAX
    public Pair findPEFilter() {
        return dao.findPEFilter();
    }

    //MIN MAX
    public Pair findPBFilter() {
        return dao.findPBFilter();
    }

    /**
     * Debt to Equity Ratio
     */
    //MIN MAX
    public Pair findDEFilter() {
        return dao.findDEFilter();
    }

    //MIN MAX
    public Pair findCurrentRatioFilter() {
        return dao.findCurrentRatioFilter();
    }

    public Pair findNetOperatingCashFlowFilter() {
        return dao.findNetOperatingCashFlowFilter();
    }

    public Pair findOperatingProfitMarginFilter() {
        return dao.findOperatingProfitMarginFilter();
    }

    /**
     * ROE
     */
    public Pair findROEFilter() {
        return dao.findROEFilter();
    }

    /**
     * PAT
     */
    public Pair findPATFilter() {
        return dao.findPATFilter();
    }

    /**
     * EPS GROWTH
     */
    public Pair findEPSFilter() {
        return dao.findEPSFilter();
    }

    /**
     * REVENUE
     */
    public Pair findRevenueFilter() {
        return dao.findRevenueFilter();
    }

    /**
     * Total Free Cash Flow
     */
    //MIN MAX
    public Pair findTotalFreeCashFlowFilter() {
        return dao.findTotalFreeCashFlowFilter();
    }

    /**
     * Return On Asset
     */
    public Pair findReturnOnAssetFilter() {
        return dao.findReturnOnAssetFilter();
    }

    /**
     * Dividend Yield
     */
    //MIN MAX
    public Pair findDivYieldFilter() {
        return dao.findDivYieldFilter();
    }

    /**
     * Return on Total Capital
     */
    public Pair findROTCFilter() {
        return dao.findROTCFilter();
    }
}
