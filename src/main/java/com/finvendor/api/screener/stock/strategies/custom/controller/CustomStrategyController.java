package com.finvendor.api.screener.stock.strategies.custom.controller;

import com.finvendor.api.screener.stock.strategies.custom.dto.CustomStrategyDto;
import com.finvendor.api.screener.stock.strategies.custom.dto.Filters;
import com.finvendor.api.screener.stock.strategies.custom.service.CustomStrategyService;
import com.finvendor.common.exception.ExceptionEnum;
import com.finvendor.common.util.ErrorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/system/api")
public class CustomStrategyController {

    @Autowired
    private CustomStrategyService service;

    @PostMapping(value = "/customscreeners/feeddata", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> feedCustomScreenerData() {
        try {
            service.insertCustomScreenerData();
            return new ResponseEntity<>("Custom Screener data feed completed successfully", HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("CustomStrategyController -> feedCustomScreenerData(...)", e);
            return ErrorUtil.getError(ExceptionEnum.CUSTOM_STRATEGY_DATA_FEED.getCode(), ExceptionEnum.CUSTOM_STRATEGY_DATA_FEED.getUserMessage(), e);
        }
    }

    @GetMapping(value = "/customscreeners/filters", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllFilters() {
        return new ResponseEntity<>(new Filters(service.findIndustryFilterData(), service.findSliderFilterData()), HttpStatus.OK);
    }

    @GetMapping(value = "/customscreeners/recordstats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findRecordStats(
            @RequestParam(value = "perPageMaxRecords") String perPageMaxRecords,
            @RequestParam(value = "mcap", required = false) String[] mcap,
            @RequestParam(value = "industry", required = false) String industry,
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
            String recordStats = service.findRecordStats(perPageMaxRecords, mcap, industry, pe, pb, debtToEquityRatio,
                    currentRatio, netOperatingCashFlow, roeInPercentage, operatingProfitMargin, patGrowthInPercentage,
                    epsGrowthInPercentage, revenueGrowthInPercentage,
                    totalFreeCashFlow, returnOnAssetInPercentage, divYield, rotcInPercentage);
            return new ResponseEntity<>(recordStats, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("CustomStrategyController -> findRecordStats(...)", e);
            return ErrorUtil.getError(ExceptionEnum.CUSTOM_STRATEGY.getCode(), ExceptionEnum.CUSTOM_STRATEGY.getUserMessage(), e);
        }
    }

    @GetMapping(value = "/customscreeners", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findCustomScreeners(
            @RequestParam(value = "pageNumber") String pageNumber,
            @RequestParam(value = "perPageMaxRecords") String perPageMaxRecords,
            @RequestParam(value = "sortBy") String sortBy,
            @RequestParam(value = "orderBy") String orderBy,
            @RequestParam(value = "mcap", required = false) String[] mcap,
            @RequestParam(value = "industry", required = false) String industry,
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
            List<CustomStrategyDto> customScreeners = service
                    .findCustomScreeners(pageNumber, perPageMaxRecords, sortBy, orderBy, mcap, industry, pe, pb, debtToEquityRatio,
                            currentRatio, netOperatingCashFlow, roeInPercentage, operatingProfitMargin, patGrowthInPercentage,
                            epsGrowthInPercentage, revenueGrowthInPercentage,
                            totalFreeCashFlow, returnOnAssetInPercentage, divYield, rotcInPercentage);

            return new ResponseEntity<>(customScreeners, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("CustomStrategyController -> findCustomScreeners(...)", e);
            return ErrorUtil.getError(ExceptionEnum.CUSTOM_STRATEGY_DATA.getCode(), ExceptionEnum.CUSTOM_STRATEGY_DATA.getUserMessage(), e);
        }
    }
}
