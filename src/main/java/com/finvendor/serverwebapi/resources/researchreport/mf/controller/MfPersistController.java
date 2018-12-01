package com.finvendor.serverwebapi.resources.researchreport.mf.controller;

import com.finvendor.common.util.ErrorUtil;
import com.finvendor.server.common.infra.persist.IFilePersist;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.WebUriConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

import static com.finvendor.common.exception.ExceptionEnum.MF_PERSIST;
import static com.finvendor.common.exception.ExceptionEnum.SECTOR_RESEARCH_FILTER;

/**
 * @Author Ayush on 4-Oct-2018
 */
@Controller
@RequestMapping(WebUriConstants.BASE_URI)
public class MfPersistController {

    private static final Logger logger = LoggerFactory.getLogger(MfPersistController.class.getName());
    public static final String MF_MASTER_FILE = "D:\\tmp\\master\\mfv2.0.csv";
    public static final String MF_HISTORICAL_FOLDER_PATH = "D:\\tmp\\master\\historical";

    @Autowired
    @Qualifier(value = "mfMasterDataPersist")
    IFilePersist mfMasterDataPersist;

    @Autowired
    @Qualifier(value = "mfHistoricalDataPersist")
    IFilePersist mfHistoricalDataPersist;

    /**
     * Persist Master Data -  Weekly
     */
    @GetMapping(value = "/mf/persist/master")
    public ResponseEntity<?> persistData() throws WebApiException {
        try {
            Long persistCount = (Long) mfMasterDataPersist.persist(MF_MASTER_FILE);
            logger.info("persistCount:{}", persistCount);
            return new ResponseEntity<>("MF Master record inserted count: " + persistCount, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("Error has occurred while getting filter value, Error:{}", e);
            return ErrorUtil.getError(MF_PERSIST.getCode(), MF_PERSIST.getUserMessage(), e);
        }
    }

    /**
     * Persist Historical Data - One Time
     */
    @GetMapping(value = "/mf/persist/historical")
    public ResponseEntity<?> persistMutualFundHistoricalNav() throws WebApiException {
        try {
            long totalHistoricalRecordCount = 0L;
            File folder = new File(MF_HISTORICAL_FOLDER_PATH);
            for (File filePath : folder.listFiles()) {
                Long persistCount = (Long) mfHistoricalDataPersist.persist(filePath.getPath());
                totalHistoricalRecordCount += persistCount;
                logger.info("persistCount:{} for file: {} ", persistCount, filePath);
            }
            return new ResponseEntity<>("MF Histrocial record inserted count: " + totalHistoricalRecordCount, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while getting filter value, Error:{}", e);
            return ErrorUtil.getError(MF_PERSIST.getCode(), MF_PERSIST.getUserMessage(), e);
        }
    }

    /**
     * Persist NAV Data - Daily Basis
     */
    @GetMapping(value = "/mf/persist/daily")
    public ResponseEntity<?> persistDailyData() throws WebApiException {
        try {
            return null;
        } catch (Exception e) {
            logger.error("Error has occurred while getting filter value, Error:{}", e);
            return ErrorUtil.getError(SECTOR_RESEARCH_FILTER.getCode(), SECTOR_RESEARCH_FILTER.getUserMessage(), e);
        }
    }

    /**
     * Persist NAV Data - Benchmark Return
     */
    @GetMapping(value = "/mf/persist/benchmark")
    public ResponseEntity<?> persistBenchMarkReturn() throws WebApiException {
        try {
            return null;
        } catch (Exception e) {
            logger.error("Error has occurred while getting filter value, Error:{}", e);
            return ErrorUtil.getError(SECTOR_RESEARCH_FILTER.getCode(), SECTOR_RESEARCH_FILTER.getUserMessage(), e);
        }
    }


    /**
     * Persist NAV Data - Risk Free
     */
    @GetMapping(value = "/mf/persist/riskfree")
    public ResponseEntity<?> persistRiskFree() throws WebApiException {
        try {
            return null;
        } catch (Exception e) {
            logger.error("Error has occurred while getting filter value, Error:{}", e);
            return ErrorUtil.getError(SECTOR_RESEARCH_FILTER.getCode(), SECTOR_RESEARCH_FILTER.getUserMessage(), e);
        }
    }
}
