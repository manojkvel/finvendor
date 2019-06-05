package com.finvendor.api.markets.controller;

import com.finvendor.api.markets.service.MarketsService;
import com.finvendor.common.util.ErrorUtil;
import com.finvendor.api.exception.WebApiException;
import com.finvendor.api.markets.dto.indexstock.IndexFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.finvendor.common.exception.ExceptionEnum.*;

@Controller
@RequestMapping(value = "/system/api")
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
            ErrorUtil.logError("Error in MarketsController - getIndexNames() ", e);
            return ErrorUtil.getError(INDEX_NAMES.getCode(), INDEX_NAMES.getUserMessage(), e);
        }
    }

    @RequestMapping(value = "/markets/index/summary", method = RequestMethod.GET)
    public ResponseEntity<?> getIndexSummary(@RequestParam("indexFilter") String indexFilter) throws WebApiException {
        try {
            logger.info("MarketsController-> getIndexSummary()::");
            logger.info("indexFilter: {}", indexFilter);
            String marketsRecordStatsJson = marketsService.getIndexSummary(indexFilter);
            return new ResponseEntity<>(marketsRecordStatsJson, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("Error in MarketsController - getIndexSummary() ", e);
            return ErrorUtil.getError(INDEX_SUMMARY.getCode(), INDEX_SUMMARY.getUserMessage(), e);
        }
    }

    @RequestMapping(value = "/markets/analytics", method = RequestMethod.GET)
    public ResponseEntity<?> getMarketsAnalytics(@RequestParam("indexFilter") String indexFilter, @RequestParam("type") String type) throws WebApiException {
        try {
            logger.info("MarketsController-> getMarketsAnalytics()::");
            logger.info("indexFilter: {}", indexFilter);
            logger.info("type: {}", type);
            String marketsRecordStatsJson = marketsService.getMarketsAnalytics(indexFilter, type);
            return new ResponseEntity<>(marketsRecordStatsJson, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("Error in MarketsController - getMarketsAnalytics() ", e);
            return ErrorUtil.getError(MARKET_ANALYTICS.getCode(), MARKET_ANALYTICS.getUserMessage(), e);
        }
    }

    @RequestMapping(value = "/markets/recordstats", method = RequestMethod.GET)
    public ResponseEntity<?> getMarketsRecordStats(@RequestParam("indexFilter") String indexFilter,
                                                   @RequestParam("type") String type,
                                                   @RequestParam("perPageMaxRecords") String perPageMaxRecords) throws WebApiException {
        try {
            logger.info("MarketsController-> getMarketsRecordStats()::");
            logger.info("indexFilter: {}", indexFilter);
            logger.info("type: {}", type);
            logger.info("perPageMaxRecords:{}", perPageMaxRecords);
            String marketsRecordStatsJson = marketsService.getMarketsRecordStats(indexFilter, type, perPageMaxRecords);
            return new ResponseEntity<>(marketsRecordStatsJson, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("Error in MarketsController - getMarketsRecordStats() ", e);
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

            String markets = marketsService.getMarkets(indexFilter, type, pageNumber, perPageMaxRecords, sortBy, orderBy);
            return new ResponseEntity<>(markets, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("Error in MarketsController - getMarkets ", e);
            return ErrorUtil.getError(MARKETS.getCode(), MARKETS.getUserMessage(), e);
        }
    }

    @PostMapping(value = "/markets/marquee/index")
    public ResponseEntity<?> getIndexMarqueeData(@RequestBody(required = false) IndexFilter indexFilter)
            throws WebApiException {
        try {
            if (indexFilter == null) {
                return new ResponseEntity<>(marketsService.getIndexMarqueeData(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(marketsService.getStockMarqueeDataForIndex(indexFilter.getIndexNames()), HttpStatus.OK);
            }
        } catch (Exception e) {
            ErrorUtil.logError("Error in MarketsController - Get Marquee Data ", e);
            return ErrorUtil.getError(MARQUEE_ERROR.getCode(), MARQUEE_ERROR.getUserMessage(), e);
        }
    }
}
