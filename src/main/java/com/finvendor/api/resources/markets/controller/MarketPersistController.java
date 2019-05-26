package com.finvendor.api.resources.markets.controller;

import com.finvendor.common.util.DateUtils;
import com.finvendor.common.util.ErrorUtil;
import com.finvendor.common.infra.persist.IFilePersist;
import com.finvendor.common.infra.upload.IFileUpload;
import com.finvendor.api.exception.WebApiException;
import com.finvendor.util.EmailUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    /**
     * Persist Bhav Copy data from NSE site to findvendor db
     */
    @RequestMapping(value = "/persist/markets", method = RequestMethod.GET)
    public ResponseEntity<?> persistMarkets() throws WebApiException {
        try {
            String nseBhavCopyPriceUrl = getBhavCopyPriceUrl();
            logger.info("persistFileToDb-> nseBhavCopyPriceUrl:{}", nseBhavCopyPriceUrl);

            //home/finvendo/tmp
            String toPath = finvendorProperties.getProperty("finvendo_tmp_path");

            //Step-1 Upload to server
            String filePath = marketsFileUploadService.upload(nseBhavCopyPriceUrl, toPath);
            logger.info("filePath:{}", filePath);

            //Step-2 Persist to db
            long persistCount = (long) this.marketsFilePersist.persist(filePath);
            logger.info("Bhav copy persist count:{}", persistCount);
            return new ResponseEntity<>("Bhav Copy file persisted into finvendor database successfully with total prices:" + persistCount, HttpStatus.OK);
        } catch (Exception e) {
            sendMailWhenURLInvalid();
            ErrorUtil.logError("Error has occurred while persist Bhav Copy from finvendor tmp path, error - ", e);
            return ErrorUtil.getError(MARKETS_PERSIST.getCode(), MARKETS_PERSIST.getUserMessage(), e);
        }
    }

    /**
     * Persist Nifty Indices Data from Nifty Site to finvendor db
     */
    @RequestMapping(value = "/persist/nifty", method = RequestMethod.GET)
    public ResponseEntity<?> persistNiftyIndices() throws WebApiException {
        try {
            String niftyIndicesPricePath =  finvendorProperties.getProperty("nifty_indices_source_path");
            long persistCount = (long) this.niftyIndicesFilePersist.persist(niftyIndicesPricePath);
            logger.info("NiftyIndices - persistCount:{}", persistCount);
            return new ResponseEntity<>("Nifty Indices persisted into finvendor database successfully with total records:" + persistCount, HttpStatus.OK);
        } catch (Exception e) {
            sendMailWhenURLInvalid();
            ErrorUtil.logError("Error has occurred while persist Nifty Indices from finvendor tmp path, error - ", e);
            return ErrorUtil.getError(NIFTY_INDICES_PERSIST.getCode(), NIFTY_INDICES_PERSIST.getUserMessage(), e);
        }
    }

    private void sendMailWhenURLInvalid() {
        String subject = "URL Invalid";
        String content = "The resource  you are looking for has been removed, had its name changed, or is temporarily unavailable.";
        EmailUtil.sendMail("amitkgaurav@gmail.com", subject, content);
        EmailUtil.sendMail("wonderfulmani@gmail.com", subject, content);
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

    private String getNiftyIndicesPriceUrl() {
        String niftyIndicesSourceUrl = finvendorProperties.getProperty("nifty_indices_source_path");
        String currentDay = DateUtils.getDayNumber();
        String currentMonth = DateUtils.getCurrentMonthDigit();
        String currentYear = DateUtils.getCurrentYear();
        String dateForUrl=currentDay+currentMonth+currentYear;
        niftyIndicesSourceUrl = StringUtils.replace(niftyIndicesSourceUrl, "DATE", dateForUrl);
        return niftyIndicesSourceUrl;
    }
}
