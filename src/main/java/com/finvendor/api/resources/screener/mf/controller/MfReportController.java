package com.finvendor.api.resources.screener.mf.controller;

import com.finvendor.api.resources.screener.mf.dto.MfFilter;
import com.finvendor.common.util.ErrorUtil;
import com.finvendor.api.exception.WebApiException;
import com.finvendor.api.resources.WebUriConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.finvendor.common.exception.ExceptionEnum.SECTOR_RESEARCH_FILTER;


/**
 * @Author Ayush on 4-Oct-2018
 */
@Controller
@RequestMapping(WebUriConstants.BASE_URI)
public class MfReportController {

    private static final Logger logger = LoggerFactory.getLogger(MfReportController.class.getName());


    /**
     * Get MF (Mutual Fund) Filter Value
     */
    @RequestMapping(value = "/mfreports/filters", method = RequestMethod.GET)
    public ResponseEntity<?> getFilterValue(@RequestParam("type") String type) throws WebApiException {
        try {

            return null;
        } catch (Exception e) {
            logger.error("Error has occurred while getting filter value, Error:{}", e);
            return ErrorUtil.getError(SECTOR_RESEARCH_FILTER.getCode(), SECTOR_RESEARCH_FILTER.getUserMessage(), e);
        }
    }

    /**
     * Get MF (Mutual Fund) Record Statistics
     */
    @RequestMapping(value = "/mfreports/recordstats", method = RequestMethod.POST)
    public ResponseEntity<?> getRecordStats(@RequestParam("perPageMaxRecords") String perPageMaxRecords,
                                            @RequestBody MfFilter mfFilter) throws WebApiException {
        try {
            return null;
        } catch (Exception e) {
            logger.error("Error has occurred while getting filter value, Error:{}", e);
            return ErrorUtil.getError(SECTOR_RESEARCH_FILTER.getCode(), SECTOR_RESEARCH_FILTER.getUserMessage(), e);
        }
    }

    /**
     * Get MF (Mutual Fund) Reports
     */
    @RequestMapping(value = "/mfreports", method = RequestMethod.POST)
    public ResponseEntity<?> getMfReports(@RequestParam("pageNumber") String pageNumber,
                                          @RequestParam("perPageMaxRecords") String perPageMaxRecords,
                                          @RequestParam("sortBy") String sortBy,
                                          @RequestParam("orderBy") String orderBy,
                                          @RequestBody MfFilter mfFilter) throws WebApiException {
        try {
            return null;
        } catch (Exception e) {
            logger.error("Error has occurred while getting filter value, Error:{}", e);
            return ErrorUtil.getError(SECTOR_RESEARCH_FILTER.getCode(), SECTOR_RESEARCH_FILTER.getUserMessage(), e);
        }
    }

    /**
     * Get MF (Mutual Fund) Reports
     */
    @RequestMapping(value = "/mfreports/dashboard", method = RequestMethod.POST)
    public ResponseEntity<?> getMfReportDashBoard(@RequestParam("pageNumber") String pageNumber,
                                                  @RequestParam("perPageMaxRecords") String perPageMaxRecords,
                                                  @RequestParam("sortBy") String sortBy,
                                                  @RequestParam("orderBy") String orderBy,
                                                  @RequestBody MfFilter mfFilter) throws WebApiException {
        try {
            return null;
        } catch (Exception e) {
            logger.error("Error has occurred while getting filter value, Error:{}", e);
            return ErrorUtil.getError(SECTOR_RESEARCH_FILTER.getCode(), SECTOR_RESEARCH_FILTER.getUserMessage(), e);
        }
    }

    /**
     * Get MF (Mutual Fund) Reports
     */
    @RequestMapping(value = "/mfreports/download", method = RequestMethod.POST)
    public ResponseEntity<?> getMfReportDownload(@RequestParam("productId") String productId,
                                                 @RequestParam("reportName") String reportName) throws WebApiException {
        try {
            return null;
        } catch (Exception e) {
            logger.error("Error has occurred while getting filter value, Error:{}", e);
            return ErrorUtil.getError(SECTOR_RESEARCH_FILTER.getCode(), SECTOR_RESEARCH_FILTER.getUserMessage(), e);
        }
    }
}
