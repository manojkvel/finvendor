package com.finvendor.api.screener.sector.controller;

import com.finvendor.api.exception.WebApiException;
import com.finvendor.api.screener.sector.dto.SectorReportFilter;
import com.finvendor.api.screener.sector.service.SectorReportService;
import com.finvendor.api.webutil.WebUtils;
import com.finvendor.common.util.ErrorUtil;
import com.finvendor.common.util.Pair;
import com.finvendor.modelpojo.staticpojo.StatusPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.finvendor.common.exception.ExceptionEnum.*;

@RestController
@RequestMapping(value = "/api")
public class SectorReportController {
    private static final Logger logger = LoggerFactory.getLogger(SectorReportController.class.getName());

    private final SectorReportService service;

    @Autowired
    public SectorReportController(SectorReportService service) {
        this.service = service;
    }

    @RequestMapping(value = "/industrysubtypes", method = RequestMethod.GET)
    public ResponseEntity<?> getIndustrySubTypes(@RequestParam("researchArea") String researchArea) throws WebApiException {
        try {
            return new ResponseEntity<>(service.getIndustrySubTypeName(researchArea), HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("Error in Sector Report , Error:{}", e);
            return ErrorUtil.getError(SECTOR_RESEARCH_FILTER.getCode(), SECTOR_RESEARCH_FILTER.getUserMessage(), e);
        }
    }

    @RequestMapping(value = "/sectorreports/filtervalue", method = RequestMethod.GET)
    public ResponseEntity<?> getSectorFilterValue(@RequestParam("type") String type) throws WebApiException {
        try {
            logger.info("type:{}", type);
            return new ResponseEntity<>(service.getFilterValue(type), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while getting filter value, Error:{}", e);
            return ErrorUtil.getError(SECTOR_RESEARCH_FILTER.getCode(), SECTOR_RESEARCH_FILTER.getUserMessage(), e);
        }
    }

    @RequestMapping(value = "/sectorreports/recordstats", method = RequestMethod.POST)
    public ResponseEntity<?> getSectorRecordStats(@RequestParam("perPageMaxRecords") String perPageMaxRecords,
                                            @RequestBody SectorReportFilter sectorFilter) throws WebApiException {
        try {
            logger.info("perPageMaxRecords:{}", perPageMaxRecords);
            logger.info("sectorFilter:{}", sectorFilter.toString());

            return new ResponseEntity<>(service.getRecordStats(sectorFilter, perPageMaxRecords), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while getting record stats, Error:{}", e);
            return ErrorUtil.getError(SECTOR_RECORD_STATS.getCode(), SECTOR_RECORD_STATS.getUserMessage(), e);
        }
    }

    @RequestMapping(value = "/sectorreports", method = RequestMethod.POST)
    public ResponseEntity<?> getSectorResearchReports(@RequestParam("pageNumber") String pageNumber,
                                                      @RequestParam("perPageMaxRecords") String perPageMaxRecords,
                                                      @RequestParam("sortBy") String sortBy,
                                                      @RequestParam("orderBy") String orderBy,
                                                      @RequestBody SectorReportFilter sectorFilter) throws WebApiException {
        try {
            logger.info("pageNumber:{}", pageNumber);
            logger.info("perPageMaxRecords:{}", perPageMaxRecords);
            logger.info("sortBy:{}", sortBy);
            logger.info("orderBy:{}", orderBy);
            logger.info("sectorFilter:{}", sectorFilter.toString());

            String sectorReportResult = service.getSectorReports(sectorFilter, pageNumber, perPageMaxRecords, sortBy,
                    orderBy);
            return new ResponseEntity<>(sectorReportResult, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            ErrorUtil.logError("Error in SectorReportController - getSectorResearchReports()", e);
            return ErrorUtil.getError(SECTOR_RESEARCH_REPORT.getCode(), SECTOR_RESEARCH_REPORT.getUserMessage(), e);
        }
    }

    @RequestMapping(value = "/sectorreports/dashboard", method = RequestMethod.POST)
    public ResponseEntity<?> getResearchReportDashboardData(@RequestParam("pageNumber") String pageNumber,
                                                @RequestParam("perPageMaxRecords") String perPageMaxRecords,
                                                @RequestParam("sortBy") String sortBy,
                                                @RequestParam("orderBy") String orderBy,
                                                @RequestBody SectorReportFilter sectorFilter,
                                                @RequestParam("productId") String productId) throws WebApiException {
        try {
            List<String> productIdList=new ArrayList<>();
            productIdList.add(productId);

            sectorFilter.setProductIds(productIdList);


            String sectorReportDashBoardData = service.getReportDashboard(sectorFilter, pageNumber, perPageMaxRecords, sortBy,
                    orderBy);
            return new ResponseEntity<>(sectorReportDashBoardData, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while getting sector reports, Error:{}", e);
            return ErrorUtil.getError(SECTOR_RESEARCH_REPORT.getCode(), SECTOR_RESEARCH_REPORT.getUserMessage(), e);
        }
    }

    @RequestMapping(value = "/sectorreports/download", method = RequestMethod.GET)
    public ResponseEntity<?> downloadResearchReport(HttpServletRequest request, HttpServletResponse response,
                                            @RequestParam("productId") String productId,
                                            @RequestParam("reportName") String reportName) throws WebApiException {
        try {
            Pair<Long, InputStream> download = service.download(productId);
            WebUtils.processDownload(response, productId, reportName, download);
            return new ResponseEntity<>(
                    new StatusPojo("true",
                            "Sector research report downloaded successfully."), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error has occurred while downloadin sector reports, Error:{}", e);
            return ErrorUtil.getError(SECTOR_RESEARCH_REPORT.getCode(), SECTOR_RESEARCH_REPORT.getUserMessage(), e);
        }
    }
}
