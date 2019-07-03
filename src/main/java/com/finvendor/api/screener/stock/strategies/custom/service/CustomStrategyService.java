package com.finvendor.api.screener.stock.strategies.custom.service;

import com.finvendor.api.screener.stock.strategies.custom.dao.CustomStrategyDao;
import com.finvendor.api.screener.stock.strategies.custom.dto.CustomStrategyDto;
import com.finvendor.api.screener.stock.strategies.custom.dto.IndustryData;
import com.finvendor.api.screener.stock.strategies.custom.dto.SliderData;
import com.finvendor.api.screener.stock.strategies.custom.dto.filter.CustomFilter;
import com.finvendor.api.screener.stock.strategies.custom.enums.FilterTypeEnum;
import com.finvendor.common.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomStrategyService {

    @Autowired
    private CustomStrategyDao dao;

    public String findRecordStats(String perPageMaxRecords,CustomFilter customFilter) throws Exception {
        try {
            return dao.findRecordStats(perPageMaxRecords,customFilter);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public List<CustomStrategyDto> findCustomScreeners(String pageNumber, String perPageMaxRecords, String sortBy, String orderBy, CustomFilter customFilter) throws Exception {
        try {
            return dao.findCustomScreeners(pageNumber, perPageMaxRecords, sortBy, orderBy, customFilter);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public void insertCustomScreenerData() throws Exception {
        try {
            dao.insertCustomScreenerData();
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public List<SliderData> findSliderFilterData() {
        List<SliderData> sliderDataList = new ArrayList<>();
        Pair mcapFilter = dao.findSliderFilterData(FilterTypeEnum.MCAP);
        Pair peFilter = dao.findSliderFilterData(FilterTypeEnum.PE);
        Pair pbFilter = dao.findSliderFilterData(FilterTypeEnum.PB);
        Pair deFilter = dao.findSliderFilterData(FilterTypeEnum.DE);
        Pair currentRatioFilter = dao.findSliderFilterData(FilterTypeEnum.CURRENT_RATIO);
        Pair operatingCashFlowFilter = dao.findSliderFilterData(FilterTypeEnum.NET_OPERATING_CASH_FLOW);
        Pair roeFilter = dao.findSliderFilterData(FilterTypeEnum.ROE);
        Pair operatingProfitMarginFilter = dao.findSliderFilterData(FilterTypeEnum.OPERATING_PROFIT_MARGIN);
        Pair patFilter = dao.findSliderFilterData(FilterTypeEnum.PAT);
        Pair epsFilter = dao.findSliderFilterData(FilterTypeEnum.EPS);
        Pair revenueFilter = dao.findSliderFilterData(FilterTypeEnum.REVENUE);
        Pair totalFreeCashFlowFilter = dao.findSliderFilterData(FilterTypeEnum.TOTAL_FREE_CASH_FLOW);
        Pair revenueFilter1 = dao.findSliderFilterData(FilterTypeEnum.REVENUE);
        Pair divYieldFilter = dao.findSliderFilterData(FilterTypeEnum.DIV_YIELD);
        Pair rotcFilter = dao.findSliderFilterData(FilterTypeEnum.ROTC);

        sliderDataList.add(new SliderData("mcap", "Market Capitalisation ('0000 Cr)", mcapFilter.getElement1().toString(),
                mcapFilter.getElement2().toString()));
        sliderDataList.add(new SliderData("pe", "P/E(ttm)", peFilter.getElement1().toString(), peFilter.getElement2().toString()));
        sliderDataList
                .add(new SliderData("pb", "Price To Book Value", pbFilter.getElement1().toString(), pbFilter.getElement2().toString()));
        sliderDataList.add(new SliderData("debtToEquityRatio", "Debt To Equity Ratio", deFilter.getElement1().toString(),
                deFilter.getElement2().toString()));
        sliderDataList
                .add(new SliderData("currentRatio", "Current Ratio", currentRatioFilter.getElement1().toString(),
                        currentRatioFilter.getElement2().toString()));
        sliderDataList
                .add(new SliderData("netOperatingCashFlow", "Net Operating CashFlow ('0000 Cr)", operatingCashFlowFilter.getElement1().toString(),
                        operatingCashFlowFilter.getElement2().toString()));
        sliderDataList.add(new SliderData("roeInPercentage", "ROE (avg 3Y; in%)", roeFilter.getElement1().toString(),
                roeFilter.getElement2().toString()));
        sliderDataList.add(new SliderData("operatingProfitMargin", "Operating Profit Margin(avg 3Y; in %)",
                operatingProfitMarginFilter.getElement1().toString(),
                operatingProfitMarginFilter.getElement2().toString()));
        sliderDataList.add(new SliderData("patGrowthInPercentage", "PAT Growth (avg 3Y; in %)", patFilter.getElement1().toString(),
                patFilter.getElement2().toString()));
        sliderDataList.add(new SliderData("epsGrowthInPercentage", "EPS Growth (avg 3Y; in %)", epsFilter.getElement1().toString(),
                epsFilter.getElement2().toString()));
        sliderDataList
                .add(new SliderData("revenueGrowthInPercentage", "Revenue Growth (avg 3Y; in %)", revenueFilter.getElement1().toString(),
                        revenueFilter.getElement2().toString()));
        sliderDataList.add(new SliderData("totalFreeCashFlow", "Total Free CashFlow ('0000 Cr)", totalFreeCashFlowFilter.getElement1().toString(),
                totalFreeCashFlowFilter.getElement2().toString()));
        sliderDataList.add(new SliderData("returnOnAssetInPercentage", "Return On Assets(%)", revenueFilter1.getElement1().toString(),
                revenueFilter1.getElement2().toString()));
        sliderDataList.add(new SliderData("divYield", "Dividend Yield(%)", divYieldFilter.getElement1().toString(),
                divYieldFilter.getElement2().toString()));
        sliderDataList.add(new SliderData("rotcInPercentage", "Return On Total Capital(%)", rotcFilter.getElement1().toString(),
                rotcFilter.getElement2().toString()));
        return sliderDataList;
    }

    public List<IndustryData> findIndustryFilterData() {
        List<String> industryNames = dao.findIndustry();
        List<IndustryData> industryData = new ArrayList<>();
        industryData.add(new IndustryData("industry", "Industry", industryNames));
        return industryData;
    }
}
