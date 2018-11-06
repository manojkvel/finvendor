package com.finvendor.serverwebapi.resources.markets.controller;

import com.finvendor.common.util.DateUtil;
import com.finvendor.common.util.ErrorUtil;
import com.finvendor.server.common.infra.persist.IFilePersist;
import com.finvendor.server.common.infra.upload.IFileUpload;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.WebUriConstants;
import com.finvendor.serverwebapi.resources.markets.service.MarketsService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Properties;

import static com.finvendor.common.exception.ExceptionEnum.*;

@Controller
@RequestMapping(WebUriConstants.BASE_URI)
public class MarketsController {
    private static final Logger logger = LoggerFactory.getLogger(MarketsController.class.getName());

    @Resource(name = "finvendorProperties")
    private Properties finvendorProperties;

    @Autowired
    @Qualifier(value = "marketsFileUploadService")
    IFileUpload marketsFileUploadService;

    @Autowired
    @Qualifier(value = "niftyIndicesFileUploadService")
    IFileUpload niftyIndicesFileUploadService;

    @Autowired
    @Qualifier(value = "marketsFilePersist")
    IFilePersist marketsFilePersist;

    @Autowired
    @Qualifier(value = "niftyIndicesFilePersist")
    IFilePersist niftyIndicesFilePersist;

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
    public ResponseEntity<?> getMarketsAnalytics(String indexFilter, String type) throws WebApiException {
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
                                                   @RequestParam("perPageMaxRecords") String perPageMaxRecords) throws WebApiException {
        try {
            String marketsRecordStatsJson = marketsService.getMarketsRecordStats(indexFilter, perPageMaxRecords);
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
                                        @RequestParam("perPageMaxRecords") String perPageMaxRecords) throws WebApiException {
        try {
            String markets = marketsService.getMarkets(indexFilter, type, pageNumber, perPageMaxRecords);
            return new ResponseEntity<>(markets, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while get record stats, error - ", e);
            return ErrorUtil.getError(MARKETS.getCode(), MARKETS.getUserMessage(), e);
        }
    }

    @RequestMapping(value = "/markets/persist", method = RequestMethod.GET)
    public ResponseEntity<?> persistMarkets() throws WebApiException {
        try {
            String nseBhavCopyPriceUrl = getBhavCopyPriceUrl();
            logger.info("persistFileToDb-> nseBhavCopyPriceUrl:{}", nseBhavCopyPriceUrl);
            String toPath = finvendorProperties.getProperty("finvendo_tmp_path");
            String filePath = marketsFileUploadService.upload(nseBhavCopyPriceUrl, toPath);
            logger.info("filePath:{}", filePath);

            long persistCount = (long) this.marketsFilePersist.persist(filePath);
            logger.info("persistCount:{}", persistCount);
            return new ResponseEntity<>("Bhav Copy file persisted into finvendor database successfully with total prices:" + persistCount, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while persist Bhav Copy from finvendor tmp path, error - ", e);
            return ErrorUtil.getError(MARKETS_PERSIST.getCode(), MARKETS_PERSIST.getUserMessage(), e);
        }
    }

    @RequestMapping(value = "/niftyindices/persist", method = RequestMethod.GET)
    public ResponseEntity<?> persistNiftyIndices() throws WebApiException {
        try {
            String niftyIndicesPriceUrl = getNiftyIndicesPriceUrl();
            logger.info("persistNiftyIndices-> niftyIndicesPriceUrl:{}", niftyIndicesPriceUrl);

            String toPath = finvendorProperties.getProperty("finvendo_tmp_path");

            //Upload to server
            String filePath = niftyIndicesFileUploadService.upload(niftyIndicesPriceUrl, toPath);
            logger.info("filePath:{}", filePath);

            //Persist To Db
            long persistCount = (long) this.niftyIndicesFilePersist.persist(filePath);
            logger.info("persistCount:{}", persistCount);
            return new ResponseEntity<>("Nifty Indices persisted into finvendor database successfully with total records:" + persistCount, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while persist Nifty Indices from finvendor tmp path, error - ", e);
            return ErrorUtil.getError(NIFTY_INDICES_PERSIST.getCode(), NIFTY_INDICES_PERSIST.getUserMessage(), e);
        }
    }


    private String getBhavCopyPriceUrl() {
        String bhavCopyPriceUrl = finvendorProperties.getProperty("nse_bhav_copy_price_url");
        String dayNumber = DateUtil.getDayNumber();
        String threeLetterMonthName = DateUtil.getThreeLetterMonthName();
        String year = DateUtil.getYear();
        bhavCopyPriceUrl = StringUtils.replace(bhavCopyPriceUrl, "$DAY", dayNumber);
        bhavCopyPriceUrl = StringUtils.replace(bhavCopyPriceUrl, "$MON", threeLetterMonthName);
        bhavCopyPriceUrl = StringUtils.replace(bhavCopyPriceUrl, "$YEAR", year);
        return bhavCopyPriceUrl;
    }

    private String getNiftyIndicesPriceUrl() {
        String niftyIndicesSourceUrl = finvendorProperties.getProperty("nifty_indices_source_path");
        String currentDay = DateUtil.getCurrentDay();
        String currentMonth = DateUtil.getCurrentMonthDigit();
        String currentYear = DateUtil.getCurrentYear();
        String dateForUrl=currentDay+currentMonth+currentYear;
        System.out.println(dateForUrl);
        niftyIndicesSourceUrl = StringUtils.replace(niftyIndicesSourceUrl, "DATE", dateForUrl);
        return niftyIndicesSourceUrl;
    }
}
