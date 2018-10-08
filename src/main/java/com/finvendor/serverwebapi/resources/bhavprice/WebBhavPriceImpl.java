package com.finvendor.serverwebapi.resources.bhavprice;

import com.finvendor.common.util.DateUtil;
import com.finvendor.common.util.ErrorUtil;
import com.finvendor.server.bhavprice.dto.BhavPriceDto;
import com.finvendor.server.bhavprice.dto.BhavPriceFilter;
import com.finvendor.server.bhavprice.service.IBhavPriceService;
import com.finvendor.server.common.infra.persist.IFilePersist;
import com.finvendor.server.common.infra.upload.IFileUpload;
import com.finvendor.serverwebapi.exception.WebApiException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Properties;

import static com.finvendor.common.exception.ExceptionEnum.BHAV_COPY_PERSIST;

@Controller
public class WebBhavPriceImpl implements IWebBhavPrice {
    private static final Logger logger = LoggerFactory.getLogger(WebBhavPriceImpl.class.getName());

    @Resource(name = "finvendorProperties")
    private Properties finvendorProperties;

    @Autowired
    @Qualifier(value = "bhavCopyCsvFileUploadService")
    IFileUpload bhavCopyCsvFileUploadService;

    @Autowired
    @Qualifier(value = "bhavCopyCsvFilePersist")
    IFilePersist bhavCopyCsvFilePersist;

    @Autowired
    private IBhavPriceService bhavPriceService;


    @Override
    public ResponseEntity<?> getBhavPriceRecordStats(@RequestParam("type") String type,
                                                     @RequestParam("type") String perPageMaxRecords,
                                                     @RequestBody BhavPriceFilter bhavPriceFilter) throws WebApiException {
        try {
            String bhavPriceRecordStatsJson = bhavPriceService.getBhavPriceRecordStats(type, perPageMaxRecords, bhavPriceFilter);
            return new ResponseEntity<>(bhavPriceRecordStatsJson, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while get record stats, error - ", e);
            return ErrorUtil.getError(BHAV_COPY_PERSIST.getCode(), BHAV_COPY_PERSIST.getUserMessage(), e);
        }
    }

    @Override
    public ResponseEntity<?> getBhavPrices(@RequestParam("type") String type,
                                           @RequestParam("order") String order,
                                           @RequestParam("pageNumber") String pageNumber,
                                           @RequestParam("perPageMaxRecords") String perPageMaxRecords,
                                           @RequestBody BhavPriceFilter bhavPriceFilter) throws WebApiException {
        try {
            List<BhavPriceDto> bhavPrices = bhavPriceService.getBhavPrices(type, order, pageNumber,
                    perPageMaxRecords, bhavPriceFilter);
            return new ResponseEntity<>(bhavPrices, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while get record stats, error - ", e);
            return ErrorUtil.getError(BHAV_COPY_PERSIST.getCode(), BHAV_COPY_PERSIST.getUserMessage(), e);
        }
    }

    @Override
    public ResponseEntity<?> persistFileToDb() throws WebApiException {
        try {
            String nseBhavCopyPriceUrl = getBhavCopyPriceUrl();
            logger.info("persistFileToDb-> nseBhavCopyPriceUrl:{}", nseBhavCopyPriceUrl);
            String toPath = "d:\\ayush\\bhav";//AppConstant.FINVENDO_TMP_PATH;
            String filePath = bhavCopyCsvFileUploadService.upload(nseBhavCopyPriceUrl, toPath);
            logger.info("filePath:{}", filePath);

            long persistCount = (long) this.bhavCopyCsvFilePersist.persist(filePath);
            logger.info("persistCount:{}", persistCount);
            return new ResponseEntity<>("Bhav Copy file persisted into finvendor database successfully with total prices:" + persistCount, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while persist Bhav Copy from finvendor tmp path, error - ", e);
            return ErrorUtil.getError(BHAV_COPY_PERSIST.getCode(), BHAV_COPY_PERSIST.getUserMessage(), e);
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
