package com.finvendor.api.resources.screener.stock.strategies.controller;

import com.finvendor.api.resources.screener.stock.strategies.service.CisService;
import com.finvendor.common.util.ErrorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.finvendor.common.exception.ExceptionEnum.CELEBRITY_INVESTOR_STRATEGY;

/**
 * CelebrityInvestorStrategy - CIS
 */
@RestController
@RequestMapping(value = "/system/api")
public class CisController {

    @Autowired
    private CisService service;

    @GetMapping(value = "/cis/recordstats")
    public ResponseEntity<?> findStrategyRecordStats(@RequestParam(value = "type") String type,
                                                     @RequestParam(value = "perPageMaxRecords") String perPageMaxRecords) {
        return new ResponseEntity<>("{\"firstPageNumber\":1,\"lastPageNumber\":1,\"totalRecords\":4}", HttpStatus.OK);
    }

    @GetMapping(value = "/cis/strategies")
    public ResponseEntity<?> findStrategy(@RequestParam(value = "type") String type,
                                          @RequestParam(value = "pageNumber") String pageNumber,
                                          @RequestParam(value = "perPageMaxRecords") String perPageMaxRecords) {
        try {
            return new ResponseEntity<>(service.findStrategy(type), HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("CelebrityInvestorStrategyController -> findStrategy(...), type: " + type, e);
            return ErrorUtil.getError(CELEBRITY_INVESTOR_STRATEGY.getCode(), CELEBRITY_INVESTOR_STRATEGY.getUserMessage(), e);
        }
    }
}
