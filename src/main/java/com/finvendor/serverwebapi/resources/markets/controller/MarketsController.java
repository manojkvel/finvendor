package com.finvendor.serverwebapi.resources.markets.controller;

import com.finvendor.common.util.ErrorUtil;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.WebUriConstants;
import com.finvendor.serverwebapi.resources.markets.service.MarketsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.finvendor.common.exception.ExceptionEnum.*;

@Controller
@RequestMapping(WebUriConstants.BASE_URI)
public class MarketsController {
    private static final Logger logger = LoggerFactory.getLogger(MarketsController.class.getName());

    @Autowired
    private MarketsService marketsService;

    @RequestMapping(value = "/markets/index/names", method = RequestMethod.GET)
    public ResponseEntity<?> getIndexNames() throws WebApiException {
        try {
            String marketsRecordStatsJson = marketsService.getIndexNames();
            return new ResponseEntity<>(marketsRecordStatsJson, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while get index names, error - ", e);
            return ErrorUtil.getError(INDEX_NAMES.getCode(), INDEX_NAMES.getUserMessage(), e);
        }
    }

    @RequestMapping(value = "/markets/index/summary", method = RequestMethod.GET)
    public ResponseEntity<?> getIndexSummary(@RequestParam("indexFilter") String indexFilter) throws WebApiException {
        try {
            String marketsRecordStatsJson = marketsService.getIndexSummary(indexFilter);
            return new ResponseEntity<>(marketsRecordStatsJson, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while get index summary, error - ", e);
            return ErrorUtil.getError(INDEX_SUMMARY.getCode(), INDEX_SUMMARY.getUserMessage(), e);
        }
    }

    @RequestMapping(value = "/markets/analytics", method = RequestMethod.GET)
    public ResponseEntity<?> getMarketsAnalytics(@RequestParam("indexFilter") String indexFilter, @RequestParam("type")  String type) throws WebApiException {
        try {
            String marketsRecordStatsJson = marketsService.getMarketsAnalytics(indexFilter,type);
            return new ResponseEntity<>(marketsRecordStatsJson, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while get index summary, error - ", e);
            return ErrorUtil.getError(MARKET_ANALYTICS.getCode(), MARKET_ANALYTICS.getUserMessage(), e);
        }
    }

    @RequestMapping(value = "/markets/recordstats", method = RequestMethod.GET)
    public ResponseEntity<?> getMarketsRecordStats(@RequestParam("indexFilter") String indexFilter,
                                                   @RequestParam("type")  String type,
                                                   @RequestParam("perPageMaxRecords") String perPageMaxRecords) throws WebApiException {
        try {
            String marketsRecordStatsJson = marketsService.getMarketsRecordStats(indexFilter,type, perPageMaxRecords);
            return new ResponseEntity<>(marketsRecordStatsJson, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while get record stats, error - ", e);
            return ErrorUtil.getError(MARKETS_RECORD_STATS.getCode(), MARKETS_RECORD_STATS.getUserMessage(), e);
        }
    }

    @RequestMapping(value = "/markets", method = RequestMethod.GET)
    public ResponseEntity<?> getMarkets(@RequestParam("indexFilter") String indexFilter,
                                        @RequestParam("type") String type,
                                        @RequestParam("pageNumber") String pageNumber,
                                        @RequestParam("perPageMaxRecords") String perPageMaxRecords,
                                        @RequestParam("sortBy") String sortBy,
                                        @RequestParam("orderBy") String orderBy) throws WebApiException {
        try {
            String markets = marketsService.getMarkets(indexFilter, type, pageNumber, perPageMaxRecords,sortBy,orderBy);
            return new ResponseEntity<>(markets, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while get record stats, error - ", e);
            return ErrorUtil.getError(MARKETS.getCode(), MARKETS.getUserMessage(), e);
        }
    }
}
