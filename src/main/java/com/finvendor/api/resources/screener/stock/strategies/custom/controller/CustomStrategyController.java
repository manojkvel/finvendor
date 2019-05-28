package com.finvendor.api.resources.screener.stock.strategies.custom.controller;

import com.finvendor.api.resources.screener.stock.strategies.custom.dto.Filters;
import com.finvendor.api.resources.screener.stock.strategies.custom.dto.Others;
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

    @GetMapping(value = "/customscreener/filters", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllFilters() {

        List<Others> others=new ArrayList<>();

        others.add(new Others("mcap","1","2"));
        others.add(new Others("pe","1","2"));
        others.add(new Others("pb","1","2"));
        others.add(new Others("debtToEquityRatio","1","2"));
        others.add(new Others("currentRatio","1","2"));
        others.add(new Others("netOperatingCashFlow","1","2"));
        others.add(new Others("roeInPercentage","1","2"));
        others.add(new Others("operatingProfitMargin","1","2"));
        others.add(new Others("patGrowthInPercentage","1","2"));
        others.add(new Others("epsGrowthInPercentage","1","2"));
        others.add(new Others("revenueGrowthInPercentage","1","2"));
        others.add(new Others("totalFreeCashFlow","1","2"));
        others.add(new Others("returnOnAssetInPercentage","1","2"));
        others.add(new Others("divYield","1","2"));
        others.add(new Others("rotcInPercentage","1","2"));
        List<String> industry=new ArrayList<>();
        industry.add("a");
        industry.add("b");

        Filters filters=new Filters(industry, others);
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
        return null;
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
