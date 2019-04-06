package com.finvendor.api.resources.screener.stock.recommendation.controller;

import com.finvendor.api.resources.screener.stock.recommendation.dto.filter.impl.EquityResearchFilter;
import com.finvendor.api.resources.screener.stock.recommendation.dto.result.AbsResearchReportResult;
import com.finvendor.api.resources.screener.stock.recommendation.dto.result.impl.EquityResearchResult;
import com.finvendor.api.resources.screener.stock.recommendation.service.EquityReportService;
import com.finvendor.common.util.ErrorUtil;
import com.finvendor.common.util.Pair;
import com.finvendor.modelpojo.staticpojo.StatusPojo;
import com.finvendor.api.exception.WebApiException;
import com.finvendor.api.webutil.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.*;

import static com.finvendor.common.exception.ExceptionEnum.*;

/**
 * @author ayush on Feb 21, 2018
 */
@Controller
@RequestMapping(value = "/system/api")
public class EquityReportController {
    private static final Logger logger = LoggerFactory.getLogger(EquityReportController.class.getName());

    @Autowired
    private EquityReportService equityReportService;

    @SuppressWarnings("unchecked")
    @PostMapping(value = "/researchReports")
    public ResponseEntity<?> getResearchResultTableData(@RequestBody EquityResearchFilter equityResearchFilter,
            @RequestParam(value = "type") String type,
            @RequestParam(value = "pageNumber") String pageNumber,
            @RequestParam(value = "perPageMaxRecords") String perPageMaxRecords,
            @RequestParam(value = "sortBy") String sortBy,
            @RequestParam(value = "orderBy") String orderBy) throws WebApiException {
        try {
            if (equityResearchFilter.getGeo() == null) {
                throw new Exception("Equity Research filter Error - Geo must not be null !!");
            }

            final Map<String, EquityResearchResult> researchReport = (Map<String, EquityResearchResult>) equityReportService
                    .getResearchReportTableData(equityResearchFilter, pageNumber, perPageMaxRecords, sortBy, orderBy);
            final Map<String, Collection<EquityResearchResult>> searchResult = new HashMap<>();
            searchResult.put(type, researchReport.values());
            return new ResponseEntity<>(searchResult, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("WebEquityResearchReport -> getResearchResultTableData(...) method", e);
            return ErrorUtil.getError(EQUITY_RESEARCH_REPORT.getCode(), EQUITY_RESEARCH_REPORT.getUserMessage(), e);
        }
    }

    @PostMapping(value = "/dashboardResearchReports")
    public ResponseEntity<?> getResearchResultDashboardData(@RequestBody EquityResearchFilter equityResearchFilter,
            @RequestParam(value = "type") String type,
            @RequestParam(value = "pageNumber") String pageNumber,
            @RequestParam(value = "perPageMaxRecords") String perPageMaxRecords,
            @RequestParam(value = "sortBy") String sortBy,
            @RequestParam(value = "orderBy") String orderBy,
            @RequestParam(value = "productId") String productId) throws WebApiException {
        try {
            if (equityResearchFilter == null) {
                equityResearchFilter = new EquityResearchFilter();
                equityResearchFilter.setGeo("1");
            }
            //This product Id will be used in filter when user click pdf report from CompanyProfile->ResearchReport tab in UI
            List<String> productList = new ArrayList<>();
            productList.add(productId);
            equityResearchFilter.setProductId(productList);

            final Map<String, ? extends AbsResearchReportResult> researchReportTableData = equityReportService
                    .getResearchReportTableData(equityResearchFilter, pageNumber, perPageMaxRecords, sortBy, orderBy);
            final EquityResearchResult absResearchReportResult = (EquityResearchResult) researchReportTableData
                    .get(productId);
            final Map<String, EquityResearchResult> dashboardResult = new HashMap<>();

            dashboardResult.put(type, absResearchReportResult);
            return new ResponseEntity<Map<String, EquityResearchResult>>(dashboardResult, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("WebEquityResearchReport -> getResearchResultDashboardData(...) method", e);
            return ErrorUtil.getError(EQUITY_RESEARCH_RESULT_DASHBOARD.getCode(),
                    EQUITY_RESEARCH_RESULT_DASHBOARD.getUserMessage(), e);
        }
    }

    @GetMapping(value = "/downloadResearchReports")
    public ResponseEntity<?> downloadResearchReport(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("productId") String productId,
            @RequestParam("reportName") String reportName) throws WebApiException {
        try {
            final Pair<Long, InputStream> download = equityReportService.download(productId);
            WebUtil.processDownload(response, productId, reportName, download);
            return new ResponseEntity<>(new StatusPojo("true",
                    "Equity research report downloaded successfully."), HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("WebEquityResearchReport -> downloadResearchReport(...) method", e);
            return ErrorUtil.getError(EQUITY_RESEARCH_REPORT_DOWNLOAD.getCode(),
                    EQUITY_RESEARCH_REPORT_DOWNLOAD.getUserMessage(), e);
        }
    }

    @PostMapping(value = "/researchReports/recordStats")
    public ResponseEntity<?> getRecordStatistics(@RequestBody EquityResearchFilter equityResearchFilter,
            @RequestParam(value = "type") String type,
            @RequestParam(value = "perPageMaxRecords") String perPageMaxRecords)
            throws WebApiException {
        try {
            if (!"equity".equals(type)) {
                throw new Exception("Research Report type must be equity !!");
            }
            final String recordStatistics = equityReportService.getRecordStatistics(equityResearchFilter,
                    perPageMaxRecords);
            return new ResponseEntity<String>(recordStatistics, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("WebEquityResearchReport -> getRecordStatistics(...) method", e);
            return ErrorUtil.getError(EQUITY_RESEARCH_RECORD_STAT.getCode(),
                    EQUITY_RESEARCH_RECORD_STAT.getUserMessage(), e);
        }
    }
}
