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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.finvendor.common.exception.ExceptionEnum.SECTOR_RESEARCH_FILTER;

/**
 * @Author Ayush on 4-Oct-2018
 */
@Controller
@RequestMapping(WebUriConstants.BASE_URI)
public class MfPersistController {

    private static final Logger logger = LoggerFactory.getLogger(MfPersistController.class.getName());

    @Autowired
    @Qualifier(value = "mfMasterFilePersist")
    IFilePersist mfStaticDataFilePersist;

    /**
     * Persist Static Data on Weekly Basis
     */
    @RequestMapping(value = "/mutualfund/persist/master", method = RequestMethod.GET)
    public ResponseEntity<?> persistData() throws WebApiException {
        try {
            Long persistCount = (Long)mfStaticDataFilePersist.persist("d:\\tmp\\mf.csv");
            logger.info("persistCount:{}", persistCount);
            return new ResponseEntity<>("MF Master persisted into database successfully with total records:" + persistCount, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("Error has occurred while getting filter value, Error:{}", e);
            return ErrorUtil.getError(SECTOR_RESEARCH_FILTER.getCode(), SECTOR_RESEARCH_FILTER.getUserMessage(), e);
        }
    }

    /**
     * Persist NAV Historical Data - One Time
     */
    @RequestMapping(value = "/mutualfund/persist/historicalnav", method = RequestMethod.GET)
    public ResponseEntity<?> persistMutualFundHistoricalNav() throws WebApiException {
        try {

            return null;
        } catch (Exception e) {
            logger.error("Error has occurred while getting filter value, Error:{}", e);
            return ErrorUtil.getError(SECTOR_RESEARCH_FILTER.getCode(), SECTOR_RESEARCH_FILTER.getUserMessage(), e);
        }
    }

    /**
     * Persist NAV Data - Daily Basis
     */
    @RequestMapping(value = "/mutualfund/persist/dailynav", method = RequestMethod.GET)
    public ResponseEntity<?> persistDailyData() throws WebApiException {
        try {
            return null;
        } catch (Exception e) {
            logger.error("Error has occurred while getting filter value, Error:{}", e);
            return ErrorUtil.getError(SECTOR_RESEARCH_FILTER.getCode(), SECTOR_RESEARCH_FILTER.getUserMessage(), e);
        }
    }
}
