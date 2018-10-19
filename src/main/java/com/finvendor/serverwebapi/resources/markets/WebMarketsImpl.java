package com.finvendor.serverwebapi.resources.markets;

import com.finvendor.common.util.DateUtil;
import com.finvendor.common.util.ErrorUtil;
import com.finvendor.server.common.infra.persist.IFilePersist;
import com.finvendor.server.common.infra.upload.IFileUpload;
import com.finvendor.server.markets.service.IMarketsService;
import com.finvendor.serverwebapi.exception.WebApiException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Properties;

import static com.finvendor.common.exception.ExceptionEnum.MARKETS_PERSIST;

@Controller
public class WebMarketsImpl implements IWebMarkets {
    private static final Logger logger = LoggerFactory.getLogger(WebMarketsImpl.class.getName());

    @Resource(name = "finvendorProperties")
    private Properties finvendorProperties;

    @Autowired
    @Qualifier(value = "marketsFileUploadService")
    IFileUpload marketsFileUploadService;

    @Autowired
    @Qualifier(value = "marketsFilePersist")
    IFilePersist marketsFilePersist;

    @Autowired
    private IMarketsService marketsServiceImpl;


    @Override
    public ResponseEntity<?> getMarketsRecordStats(@RequestParam("indexFilter") String indexFilter,
                                                   @RequestParam("perPageMaxRecords") String perPageMaxRecords) throws WebApiException {
        try {
            String marketsRecordStatsJson = marketsServiceImpl.getMarketsRecordStats(indexFilter, perPageMaxRecords);
            return new ResponseEntity<>(marketsRecordStatsJson, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while get record stats, error - ", e);
            return ErrorUtil.getError(MARKETS_PERSIST.getCode(), MARKETS_PERSIST.getUserMessage(), e);
        }
    }

    @Override
    public ResponseEntity<?> getMarkets(@RequestParam("indexFilter") String indexFilter,
                                        @RequestParam("type") String type,
                                        @RequestParam("pageNumber") String pageNumber,
                                        @RequestParam("perPageMaxRecords") String perPageMaxRecords) throws WebApiException {
        try {
            String markets = marketsServiceImpl.getMarkets(indexFilter, type, pageNumber,
                    perPageMaxRecords);
            return new ResponseEntity<>(markets, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while get record stats, error - ", e);
            return ErrorUtil.getError(MARKETS_PERSIST.getCode(), MARKETS_PERSIST.getUserMessage(), e);
        }
    }

    @Override
    public ResponseEntity<?> persistMarkets() throws WebApiException {
        try {
            String nseBhavCopyPriceUrl = getBhavCopyPriceUrl();
            logger.info("persistFileToDb-> nseBhavCopyPriceUrl:{}", nseBhavCopyPriceUrl);
            String toPath = "d:\\ayush\\bhav";//AppConstant.FINVENDO_TMP_PATH;
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


}
