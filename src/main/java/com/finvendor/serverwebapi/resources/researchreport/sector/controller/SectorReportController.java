package com.finvendor.serverwebapi.resources.researchreport.sector.controller;

import com.finvendor.common.util.ErrorUtil;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.WebUriConstants;
import com.finvendor.serverwebapi.resources.researchreport.sector.dto.SectorReportFilter;
import com.finvendor.serverwebapi.resources.researchreport.sector.service.SectorReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.finvendor.common.exception.ExceptionEnum.*;

@Controller
@RequestMapping(WebUriConstants.BASE_URI)
public class SectorReportController {
    private static final Logger logger = LoggerFactory.getLogger(SectorReportController.class.getName());

    @Autowired
    private SectorReportService service;

    @RequestMapping(value = "/sectorresearch/filters", method = RequestMethod.GET)
    public ResponseEntity<?> getFilterValue(@RequestParam("type") String type) throws WebApiException {
        try {
            logger.info("type:{}", type);
            return new ResponseEntity<>(service.getFilterValue(type), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while getting filter value, Error:", e);
            return ErrorUtil.getError(SECTOR_RESEARCH_FILTER.getCode(), SECTOR_RESEARCH_FILTER.getUserMessage(), e);
        }
    }

    @RequestMapping(value = "/sectorresearch/recordstats", method = RequestMethod.POST)
    public ResponseEntity<?> getRecordStats(@RequestParam(value = "perPageMaxRecords") String perPageMaxRecords,
                                            @RequestBody SectorReportFilter sectorFilter) throws WebApiException {
        try {
            logger.info("perPageMaxRecords:{}", perPageMaxRecords);
            logger.info("sectorFilter:{}", sectorFilter.toString());

            return new ResponseEntity<>(service.getRecordStats(sectorFilter, perPageMaxRecords), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while getting record stats, Error:", e);
            return ErrorUtil.getError(SECTOR_RECORD_STATS.getCode(), SECTOR_RECORD_STATS.getUserMessage(), e);
        }
    }

    @RequestMapping(value = "/researchreports", method = RequestMethod.POST)
    public ResponseEntity<?> getSectorResearchReports(@RequestParam(value = "type") String type,
            @RequestParam(value = "pageNumber") String pageNumber,
            @RequestParam(value = "perPageMaxRecords") String perPageMaxRecords,
            @RequestParam(value = "sortBy") String sortBy,
            @RequestParam(value = "orderBy") String orderBy,
            @RequestBody SectorReportFilter sectorFilter) {
        try {
            logger.info("type:{}", type);
            logger.info("pageNumber:{}", pageNumber);
            logger.info("perPageMaxRecords:{}", perPageMaxRecords);
            logger.info("sortBy:{}", sortBy);
            logger.info("orderBy:{}", orderBy);
            logger.info("sectorFilter:{}", sectorFilter.toString());

            String sectorReportResult = service.getSectorReports(sectorFilter, pageNumber, perPageMaxRecords, sortBy,
                    orderBy);
            return new ResponseEntity<>(sectorReportResult, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("WebEquityResearchReport -> getResearchResultTableData(...) method", e);
            return ErrorUtil.getError(SECTOR_RESEARCH_REPORT.getCode(), SECTOR_RESEARCH_REPORT.getUserMessage(), e);
        }
    }
}
