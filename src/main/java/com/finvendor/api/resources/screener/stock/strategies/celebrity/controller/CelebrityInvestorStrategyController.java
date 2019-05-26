package com.finvendor.api.resources.screener.stock.strategies.celebrity.controller;

import com.finvendor.api.resources.screener.stock.strategies.celebrity.enums.CisEnum;
import com.finvendor.api.resources.screener.stock.strategies.celebrity.service.CisService;
import com.finvendor.common.util.ErrorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.finvendor.common.exception.ExceptionEnum.*;

/**
 * CelebrityInvestorStrategy - CIS
 */
@RestController
@RequestMapping(value = "/system/api")
public class CelebrityInvestorStrategyController {

    @Autowired
    private CisService service;

    @GetMapping(value = "/cis/recordstats")
    public ResponseEntity<?> findCisRecordStats(@RequestParam(value = "type") CisEnum type,
            @RequestParam(value = "perPageMaxRecords") String perPageMaxRecords) {
        try {
            return new ResponseEntity<>(service.findCisRecordStats(type, perPageMaxRecords), HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("CelebrityInvestorStrategyController -> findCisRecordStats(...), type: " + type.name(), e);
            return ErrorUtil.getError(CELEBRITY_INVESTOR_STRATEGY_RECORD_STATS.getCode(), CELEBRITY_INVESTOR_STRATEGY_RECORD_STATS.getUserMessage(), e);
        }
    }

    @GetMapping(value = "/cis/strategies")
    public ResponseEntity<?> findCisStrategy(@RequestParam(value = "type") CisEnum type,
            @RequestParam(value = "pageNumber") String pageNumber,
            @RequestParam(value = "perPageMaxRecords") String perPageMaxRecords,
            @RequestParam(value = "sortBy") String sortBy,
            @RequestParam(value = "orderBy") String orderBy) {
        try {
            return new ResponseEntity<>(service.findCis(type, pageNumber, perPageMaxRecords, sortBy, orderBy), HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("CelebrityInvestorStrategyController -> findCisStrategy(...), type: " + type.name(), e);
            return ErrorUtil.getError(CELEBRITY_INVESTOR_STRATEGY.getCode(), CELEBRITY_INVESTOR_STRATEGY.getUserMessage(), e);
        }
    }
}
