package com.finvendor.api.resources.screener.stock.strategies.custom.controller;

import com.finvendor.api.resources.screener.stock.strategies.custom.dto.Filters;
import com.finvendor.api.resources.screener.stock.strategies.custom.dto.ListData;
import com.finvendor.api.resources.screener.stock.strategies.custom.dto.SliderData;
import com.finvendor.api.resources.screener.stock.strategies.custom.service.CustomStrategyService;
import com.finvendor.common.exception.ExceptionEnum;
import com.finvendor.common.util.ErrorUtil;
import com.finvendor.common.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/system/api")
public class CustomStrategyController {

    @Autowired
    private CustomStrategyService service;

    @GetMapping(value = "/customscreener/filters", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllFilters() {

        List<SliderData> sliderDataList = new ArrayList<>();

        Pair mcapFilter = service.findMcapFilter();
        Pair peFilter = service.findPEFilter();
        Pair pbFilter = service.findPBFilter();
        Pair deFilter = service.findDEFilter();
        Pair currentRatioFilter = service.findCurrentRatioFilter();
        Pair operatingCashFlowFilter = service.findNetOperatingCashFlowFilter();
        Pair roeFilter = service.findROEFilter();
        Pair operatingProfitMarginFilter = service.findOperatingProfitMarginFilter();
        Pair patFilter = service.findPATFilter();
        Pair epsFilter = service.findEPSFilter();
        Pair revenueFilter = service.findRevenueFilter();
        Pair totalFreeCashFlowFilter = service.findTotalFreeCashFlowFilter();
        Pair revenueFilter1 = service.findReturnOnAssetFilter();
        Pair divYieldFilter = service.findDivYieldFilter();
        Pair rotcFilter = service.findROTCFilter();

        sliderDataList.add(new SliderData("mcap", "Market Capitalisation",  mcapFilter.getElement1().toString(),  mcapFilter.getElement2().toString()));
        sliderDataList.add(new SliderData("pe","P/E(Trailing)",  peFilter.getElement1().toString(),  peFilter.getElement2().toString()));
        sliderDataList.add(new SliderData("pb","Price to Book value",  pbFilter.getElement1().toString(),  pbFilter.getElement2().toString()));
        sliderDataList.add(new SliderData("debtToEquityRatio","Debt to Equity Ratio",  deFilter.getElement1().toString(),  deFilter.getElement2().toString()));
        sliderDataList
                .add(new SliderData("currentRatio","Current Ratio",  currentRatioFilter.getElement1().toString(),  currentRatioFilter.getElement2().toString()));
        sliderDataList.add(new SliderData("netOperatingCashFlow","Net Operating Cash Flow",  operatingCashFlowFilter.getElement1().toString(),
                 operatingCashFlowFilter.getElement2().toString()));
        sliderDataList.add(new SliderData("roeInPercentage","ROE (AVERAGE 3 YR)", roeFilter.getElement1().toString(), roeFilter.getElement2().toString()));
        sliderDataList.add(new SliderData("operatingProfitMargin","Operating profit margin",  operatingProfitMarginFilter.getElement1().toString(),
                 operatingProfitMarginFilter.getElement2().toString()));
        sliderDataList.add(new SliderData("patGrowthInPercentage","PAT Growth (avr 3 yrs)",  patFilter.getElement1().toString(),  patFilter.getElement2().toString()));
        sliderDataList.add(new SliderData("epsGrowthInPercentage","EPS growth (avr 3 yrs)",  epsFilter.getElement1().toString(),  epsFilter.getElement2().toString()));
        sliderDataList
                .add(new SliderData("revenueGrowthInPercentage","Revenue growth (avr 3 yrs)",  revenueFilter.getElement1().toString(),  revenueFilter.getElement2().toString()));
        sliderDataList.add(new SliderData("totalFreeCashFlow","Total Free Cash Flow",  totalFreeCashFlowFilter.getElement1().toString(),  totalFreeCashFlowFilter.getElement2().toString()));
        sliderDataList.add(new SliderData("returnOnAssetInPercentage","Return on assets",  revenueFilter1.getElement1().toString(),  revenueFilter1.getElement2().toString()));
        sliderDataList.add(new SliderData("divYield","Dividend Yield",  divYieldFilter.getElement1().toString(),  divYieldFilter.getElement2().toString()));
        sliderDataList.add(new SliderData("rotcInPercentage","Return on Total Capital",  rotcFilter.getElement1().toString(),  rotcFilter.getElement2().toString()));

        List<String> industry = service.findIndustry();
        List<ListData> listData = new ArrayList<>();
        listData.add(new ListData("industry","Industry", industry));

        Filters filters = new Filters(listData, sliderDataList);
        return new ResponseEntity<>(filters, HttpStatus.OK);
    }

    @GetMapping(value = "/customscreener/recordstats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findRecordStats(
            @RequestParam(value = "perPageMaxRecords") String perPageMaxRecords,
            @RequestParam(value = "mcap", required = false) String[] mcap,
            @RequestParam(value = "industry", required = false) String[] industry,
            @RequestParam(value = "pe", required = false) String[] pe,
            @RequestParam(value = "pb", required = false) String[] pb,
            @RequestParam(value = "debtToEquityRatio", required = false) String[] debtToEquityRatio,
            @RequestParam(value = "currentRatio", required = false) String[] currentRatio,
            @RequestParam(value = "netOperatingCashFlow", required = false) String[] netOperatingCashFlow,
            @RequestParam(value = "roeInPercentage", required = false) String[] roeInPercentage,
            @RequestParam(value = "operatingProfitMargin", required = false) String[] operatingProfitMargin,
            @RequestParam(value = "patGrowthInPercentage", required = false) String[] patGrowthInPercentage,
            @RequestParam(value = "epsGrowthInPercentage", required = false) String[] epsGrowthInPercentage,
            @RequestParam(value = "revenueGrowthInPercentage", required = false) String[] revenueGrowthInPercentage,
            @RequestParam(value = "totalFreeCashFlow", required = false) String[] totalFreeCashFlow,
            @RequestParam(value = "returnOnAssetInPercentage", required = false) String[] returnOnAssetInPercentage,
            @RequestParam(value = "divYield", required = false) String[] divYield,
            @RequestParam(value = "rotcInPercentage", required = false) String[] rotcInPercentage
    ) {
        try {
            String recorsStats = service.findRecorsStats(perPageMaxRecords, mcap, industry, pe, pb, debtToEquityRatio,
                    currentRatio, netOperatingCashFlow, roeInPercentage, operatingProfitMargin, patGrowthInPercentage,
                    epsGrowthInPercentage, revenueGrowthInPercentage,
                    totalFreeCashFlow, returnOnAssetInPercentage, divYield, rotcInPercentage);
            return new ResponseEntity<>(recorsStats, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("CustomStrategyController -> findRecordStats(...)", e);
            return ErrorUtil.getError(ExceptionEnum.CUSTOM_STRATEGY.getCode(), ExceptionEnum.CUSTOM_STRATEGY.getUserMessage(), e);
        }
    }

    @GetMapping(value = "/customscreener/companies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findCompanies(
            @RequestParam(value = "pageNumber") String pageNumber,
            @RequestParam(value = "perPageMaxRecords") String perPageMaxRecords,
            @RequestParam(value = "sortBy") String sortBy,
            @RequestParam(value = "orderBy") String orderBy,
            @RequestParam(value = "mcap", required = false) String[] mcap,
            @RequestParam(value = "industry", required = false) String[] industry,
            @RequestParam(value = "pe", required = false) String[] pe,
            @RequestParam(value = "pb", required = false) String[] pb,
            @RequestParam(value = "debtToEquityRatio", required = false) String[] debtToEquityRatio,
            @RequestParam(value = "currentRatio", required = false) String[] currentRatio,
            @RequestParam(value = "netOperatingCashFlow", required = false) String[] netOperatingCashFlow,
            @RequestParam(value = "roeInPercentage", required = false) String[] roeInPercentage,
            @RequestParam(value = "operatingProfitMargin", required = false) String[] operatingProfitMargin,
            @RequestParam(value = "patGrowthInPercentage", required = false) String[] patGrowthInPercentage,
            @RequestParam(value = "epsGrowthInPercentage", required = false) String[] epsGrowthInPercentage,
            @RequestParam(value = "revenueGrowthInPercentage", required = false) String[] revenueGrowthInPercentage,
            @RequestParam(value = "totalFreeCashFlow", required = false) String[] totalFreeCashFlow,
            @RequestParam(value = "returnOnAssetInPercentage", required = false) String[] returnOnAssetInPercentage,
            @RequestParam(value = "divYield", required = false) String[] divYield,
            @RequestParam(value = "rotcInPercentage", required = false) String[] rotcInPercentage
    ) {
        return null;
    }
}
