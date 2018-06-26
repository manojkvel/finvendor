package com.finvendor.serverwebapi.resources.researchreport.impl;

import static com.finvendor.common.exception.ExceptionEnum.EQUITY_RESEARCH_RECORD_STAT;
import static com.finvendor.common.exception.ExceptionEnum.EQUITY_RESEARCH_REPORT;
import static com.finvendor.common.exception.ExceptionEnum.EQUITY_RESEARCH_REPORT_DOWNLOAD;
import static com.finvendor.common.exception.ExceptionEnum.EQUITY_RESEARCH_RESULT_DASHBOARD;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.finvendor.common.util.ErrorUtil;
import com.finvendor.modelpojo.staticpojo.StatusPojo;
import com.finvendor.server.researchreport.dto.filter.impl.EquityResearchFilter;
import com.finvendor.server.researchreport.dto.result.AbsResearchReportResult;
import com.finvendor.server.researchreport.dto.result.impl.EquityResearchResult;
import com.finvendor.server.researchreport.service.IResearchReportService;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.researchreport.IWebResearchReport;

/**
 * 
 * @author ayush on Feb 21, 2018
 */
@Controller
// TBD: Later will replace with RestController to avoid @Responsebody annotation
public class WebEquityResearchReportImpl implements IWebResearchReport {

	@Autowired
	@Qualifier(value = "equityResearchReportService")
	private IResearchReportService equityResearchService;

	@Autowired
	@Qualifier(value = "sectorResearchReportService")
	private IResearchReportService sectorResearchService;

	@Autowired
	@Qualifier(value = "macroResearchReportService")
	private IResearchReportService macroResearchService;

	@Resource(name = "finvendorProperties")
	private Properties finvendorProperties;

	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<?> getResearchResultTableData(@RequestBody EquityResearchFilter equityResearchFilter,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "pageNumber", required = true) String pageNumber,
			@RequestParam(value = "perPageMaxRecords", required = true) String perPageMaxRecords,
			@RequestParam(value = "sortBy", required = true) String sortBy,
			@RequestParam(value = "orderBy", required = true) String orderBy) throws WebApiException {

		try {
			if (equityResearchFilter.getGeo() == null) {
				throw new Exception("Equity Research filter Error - Geo must not be null !!");
			}

			Map<String, EquityResearchResult> researchReport = (Map<String, EquityResearchResult>) equityResearchService
					.getResearchReportTableData(equityResearchFilter, pageNumber, perPageMaxRecords, sortBy, orderBy);
			Map<String, Collection<EquityResearchResult>> searchResult = new HashMap<>();
			searchResult.put(type, researchReport.values());
			return new ResponseEntity<Map<String, Collection<EquityResearchResult>>>(searchResult, HttpStatus.OK);
		} catch (Exception e) {
			ErrorUtil.logError("WebEquityResearchReport -> getResearchResultTableData(...) method", e);
			return ErrorUtil.getError(EQUITY_RESEARCH_REPORT.getCode(), EQUITY_RESEARCH_REPORT.getUserMessage(), e);
		}
	}

	@Override
	public ResponseEntity<?> getResearchResultDashboardData(@RequestBody EquityResearchFilter equityResearchFilter,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "pageNumber", required = true) String pageNumber,
			@RequestParam(value = "perPageMaxRecords", required = true) String perPageMaxRecords,
			@RequestParam(value = "sortBy", required = true) String sortBy,
			@RequestParam(value = "orderBy", required = true) String orderBy,
			@RequestParam(value = "productId", required = true) String productId) throws WebApiException {
		try {
			Map<String, ? extends AbsResearchReportResult> researchReportTableData = equityResearchService
					.getResearchReportTableData(equityResearchFilter, pageNumber, perPageMaxRecords, sortBy, orderBy);
			EquityResearchResult absResearchReportResult = (EquityResearchResult) researchReportTableData
					.get(productId);
			Map<String, EquityResearchResult> dashboardResult = new HashMap<>();

			dashboardResult.put(type, absResearchReportResult);
			return new ResponseEntity<Map<String, EquityResearchResult>>(dashboardResult, HttpStatus.OK);
		} catch (Exception e) {
			ErrorUtil.logError("WebEquityResearchReport -> getResearchResultDashboardData(...) method", e);
			return ErrorUtil.getError(EQUITY_RESEARCH_RESULT_DASHBOARD.getCode(),
					EQUITY_RESEARCH_RESULT_DASHBOARD.getUserMessage(), e);
		}
	}

	@Override
	public ResponseEntity<?> downloadResearchReport(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("reportFileName") String reportFileName, @RequestParam("vendorName") String vendorName)
					throws WebApiException {
		try {
			String reportPath = buildReportPath(request, reportFileName, vendorName);
			File file = new File(reportPath);
			if (!file.exists()) {
				throw new Exception("Vendor Report Offering File " + reportFileName + " does not exist!!");
			} else {
				InputStream in = new FileInputStream(file);
				response.setHeader("Content-Disposition", "attachment; filename=" + reportFileName);
				response.setHeader("Content-Length", String.valueOf(file.length()));
				FileCopyUtils.copy(in, response.getOutputStream());
			}
			StatusPojo statusPojo = new StatusPojo("true", "Equity research report downloaded successfully.");
			return new ResponseEntity<StatusPojo>(statusPojo, HttpStatus.OK);
		} catch (Exception e) {
			ErrorUtil.logError("WebEquityResearchReport -> downloadResearchReport(...) method", e);
			return ErrorUtil.getError(EQUITY_RESEARCH_REPORT_DOWNLOAD.getCode(),
					EQUITY_RESEARCH_REPORT_DOWNLOAD.getUserMessage(), e);
		}
	}

	@Override
	public ResponseEntity<?> getRecordStatistics(@RequestBody EquityResearchFilter equityResearchFilter,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "perPageMaxRecords", required = true) String perPageMaxRecords)
					throws WebApiException {
		try {
			if (!"equity".equals(type)) {
				throw new Exception("Research Report type must be equity !!");
			}
			String recordStatistics = equityResearchService.getRecordStatistics(equityResearchFilter,
					perPageMaxRecords);
			return new ResponseEntity<String>(recordStatistics, HttpStatus.OK);
		} catch (Exception e) {
			ErrorUtil.logError("WebEquityResearchReport -> getRecordStatistics(...) method", e);
			return ErrorUtil.getError(EQUITY_RESEARCH_RECORD_STAT.getCode(),
					EQUITY_RESEARCH_RECORD_STAT.getUserMessage(), e);
		}
	}
	
	private String buildReportPath(HttpServletRequest request, String reportFileName, String vendorName)
			throws Exception {
		String loggedInUser = vendorName;
		String basePath = finvendorProperties.getProperty("research_report_offering_file_basepath");
		String fullyQualifiedReportFilePath = basePath + File.separator + loggedInUser + File.separator
				+ reportFileName;
		return fullyQualifiedReportFilePath;
	}
}
