package com.finvendor.api.resources.screener.stock.strategies.custom.controller;

import com.finvendor.api.resources.screener.stock.strategies.custom.dto.Filters;
import com.finvendor.api.resources.screener.stock.strategies.custom.dto.ListData;
import com.finvendor.api.resources.screener.stock.strategies.custom.dto.SliderData;
import com.finvendor.api.resources.screener.stock.strategies.custom.service.CustomStrategyService;
import com.finvendor.common.exception.ExceptionEnum;
import com.finvendor.common.util.ErrorUtil;
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

        sliderDataList.add(new SliderData("Market Capitalisation", "1", "2"));
        sliderDataList.add(new SliderData("P/E(Trailing)", "1", "2"));
        sliderDataList.add(new SliderData("Price to Book value", "1", "2"));
        sliderDataList.add(new SliderData("Debt to Equity Ratio", "1", "2"));
        sliderDataList.add(new SliderData("Current Ratio", "1", "2"));
        sliderDataList.add(new SliderData("Net Operating Cash Flow", "1", "2"));
        sliderDataList.add(new SliderData("ROE (AVERAGE 3 YR)", "1", "2"));
        sliderDataList.add(new SliderData("Operating profit margin", "1", "2"));
        sliderDataList.add(new SliderData("PAT Growth (avr 3 yrs)", "1", "2"));
        sliderDataList.add(new SliderData("EPS growth (avr 3 yrs)", "1", "2"));
        sliderDataList.add(new SliderData("Revenue growth (avr 3 yrs)", "1", "2"));
        sliderDataList.add(new SliderData("Total Free Cash Flow", "1", "2"));
        sliderDataList.add(new SliderData("Return on assets", "1", "2"));
        sliderDataList.add(new SliderData("Dividend Yield", "1", "2"));
        sliderDataList.add(new SliderData("Return on Total Capital", "1", "2"));

        List<String> value = new ArrayList<>();
        value.add("a");
        value.add("b");
        List<ListData> listData = new ArrayList<>();
        listData.add(new ListData("Industry", value));

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
