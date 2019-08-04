package com.finvendor.api.markets.controller;

import com.finvendor.api.exception.WebApiException;
import com.finvendor.api.markets.dao._52wLowHigh;
import com.finvendor.common.infra.persist.IFilePersist;
import com.finvendor.common.infra.upload.IFileUpload;
import com.finvendor.common.infra.upload.exception.FileUploadException;
import com.finvendor.common.util.DateUtils;
import com.finvendor.common.util.ErrorUtil;
import com.finvendor.util.EmailUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Properties;

import static com.finvendor.common.exception.ExceptionEnum.MARKETS_PERSIST;
import static com.finvendor.common.exception.ExceptionEnum.NIFTY_INDICES_PERSIST;

@RestController
@RequestMapping(value = "/system/api")
public class MarketPersistController {
    private static final Logger logger = LoggerFactory.getLogger(MarketPersistController.class.getName());

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
    private _52wLowHigh wLowHigh;

    @GetMapping(value = "/findstockpricedates")
    public ResponseEntity<?> findStockPriceDates() {
        wLowHigh.findStocksWithPriceDates();
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /**
     * Persist Bhav Copy data from NSE site to findvendor db
     */
    @GetMapping(value = "/persist/markets")
    public ResponseEntity<?> persistMarkets() throws WebApiException {
        String nseBhavCopyPriceUrl = getBhavCopyPriceUrl();
        try {
            logger.info("persistFileToDb-> nseBhavCopyPriceUrl:{}", nseBhavCopyPriceUrl);

            //home/finvendo/tmp
            String toPath = finvendorProperties.getProperty("finvendo_tmp_path");

            //Step-1 Upload to server
            String filePath = marketsFileUploadService.upload(nseBhavCopyPriceUrl, toPath);
            logger.info("filePath:{}", filePath);

            //Step-2 Persist to db
            long persistCount = (long) this.marketsFilePersist.persist(filePath);
            logger.info("Bhav copy persist count:{}", persistCount);
            return new ResponseEntity<>("Bhav Copy file persisted into finvendor database successfully with total prices:" + persistCount,
                    HttpStatus.OK);
        } catch (FileUploadException e) {
            logger.error(">>>>>>>>> Bhav Copy Price Url: {} is not accessible", nseBhavCopyPriceUrl);
            sendMailWhenURLInvalid(nseBhavCopyPriceUrl);
            return ErrorUtil.getError(MARKETS_PERSIST.getCode(), MARKETS_PERSIST.getUserMessage(), e);
        } catch (Exception e) {
            sendMailWhenURLInvalid(nseBhavCopyPriceUrl);
            ErrorUtil.logError("Error has occurred while persist Bhav Copy from finvendor tmp path, error - ", e);
            return ErrorUtil.getError(MARKETS_PERSIST.getCode(), MARKETS_PERSIST.getUserMessage(), e);
        }
    }

    /**
     * Persist Nifty Indices Data from Nifty Site to finvendor db
     * We download file using curl command in prod in path /home/finvendo/tmp/index_price.csv
     */
    @GetMapping(value = "/persist/nifty")
    public ResponseEntity<?> persistNiftyIndices() {
        try {
            //Persist To db
            long persistCount = (long) this.niftyIndicesFilePersist.persist("/home/finvendo/tmp/index_price.csv");
            String indexPersistedMsg = "Total " + persistCount + " NIFTY Indices persisted into finvendor database successfully ";
            logger.info(indexPersistedMsg);
            return new ResponseEntity<>(indexPersistedMsg, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("Error has occurred while persist Nifty Indices from finvendor tmp path, error - ", e);
            return ErrorUtil.getError(NIFTY_INDICES_PERSIST.getCode(), NIFTY_INDICES_PERSIST.getUserMessage(), e);
        }
    }

    /**
     * This method is to insert data from indice file,
     * E.g. usage: curl https://finvendor.com/system/api/persist/nifty-file?path=/home/finvendo/tmp/ind_close_all_26072019.csv
     * <p>
     * Just download ind_close_all_26072019.csv file from url: http://www.niftyindices.com/Daily_Snapshot/ind_close_all_26072019.csv
     * and put in /home/finvendo/tmp path and hit this api using above curl
     *
     * @param filePath indice file path
     */
    @GetMapping(value = "/persist/nifty-file")
    public ResponseEntity<?> persistNiftyIndicesPersistPart(@RequestParam(value = "filePath") String filePath) {
        try {
            //Step-2 Persist To db
            long persistCount = (long) this.niftyIndicesFilePersist.persist(filePath);
            String indexPersistedMsg = "Total " + persistCount + " NIFTY Indices persisted into finvendor database successfully ";
            logger.info(indexPersistedMsg);
            return new ResponseEntity<>(indexPersistedMsg, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("Error has occurred while persist Nifty Indices from finvendor tmp path, error - ", e);
            return ErrorUtil.getError(NIFTY_INDICES_PERSIST.getCode(), NIFTY_INDICES_PERSIST.getUserMessage(), e);
        }
    }

    private void sendMailWhenURLInvalid(String thirdPartyUrl) {
        String subject = "Third Party url Invalid";
        String content = "The resource:" + thirdPartyUrl
                + "  has been removed, may be its name changed, or is temporarily unavailable. Please contact finvendor admin!";
        EmailUtil.sendMail("amitkgaurav@gmail.com", subject, content);
        EmailUtil.sendMail("jbytrain@gmail.com", subject, content);
    }

    private String getBhavCopyPriceUrl() {
        String bhavCopyPriceUrl = finvendorProperties.getProperty("nse_bhav_copy_price_url");
        String dayNumber = DateUtils.getDayNumber();
        String threeLetterMonthName = DateUtils.getThreeLetterMonthName();
        String year = DateUtils.getYear();
        bhavCopyPriceUrl = StringUtils.replace(bhavCopyPriceUrl, "$DAY", dayNumber);
        bhavCopyPriceUrl = StringUtils.replace(bhavCopyPriceUrl, "$MON", threeLetterMonthName);
        bhavCopyPriceUrl = StringUtils.replace(bhavCopyPriceUrl, "$YEAR", year);
        return bhavCopyPriceUrl;
    }
}
