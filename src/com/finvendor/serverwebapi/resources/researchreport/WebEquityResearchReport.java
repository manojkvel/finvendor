package com.finvendor.serverwebapi.resources.researchreport;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.finvendor.common.util.ExceptionUtil;
import com.finvendor.server.researchreport.dto.filter.EquityResearchFilter;
import com.finvendor.server.researchreport.dto.result.EquityResearchResult;
import com.finvendor.server.researchreport.dto.result.ifc.AbsResearchReportResult;
import com.finvendor.server.researchreport.service.ifc.IResearchReportService;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.ifc.researchreport.WebResearchReportIfc;
import com.finvendor.serverwebapi.utils.WebUtil;

/**
 * 
 * @author ayush on Feb 21, 2018
 */
@Controller
// TBD: Later will replace with RestController to avoid @Responsebody annotation
public class WebEquityResearchReport implements WebResearchReportIfc {
	private static Logger logger = LoggerFactory.getLogger(WebEquityResearchReport.class);

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
	public Map<String, Collection<EquityResearchResult>> getResearchResultTableData(@RequestBody EquityResearchFilter equityResearchFilter,
			@RequestParam(value = "type", required = true) String type) throws WebApiException {

		try {
			if (equityResearchFilter.getGeo() == null) {
				throw new Exception("Equity Research filter Error - Geo must not be null !!");
			}

			Map<String,EquityResearchResult> researchReport = (Map<String,EquityResearchResult>) equityResearchService.getResearchReportTableData(equityResearchFilter);
			Map<String, Collection<EquityResearchResult>> searchResult = new HashMap<>();
			searchResult.put(type, researchReport.values());
			return searchResult;
		} catch (Exception e) {
			logger.error("Web API Error: ", e.getMessage(), e);
			throw new WebApiException(
					"Error has occurred in WebResearchReport -> getResearchResultTableData(...) method, Root Cause:: "
							+ ExceptionUtil.getRootCause(e));
		}
	}

	@Override
	public Map<String, EquityResearchResult> getResearchResultDashboardData(@RequestParam("type") String type, @RequestParam("productId") String productId,
			@RequestBody EquityResearchFilter equityResearchFilter)	throws WebApiException {
		try { 	
		 		Map<String, ? extends AbsResearchReportResult> researchReportTableData = equityResearchService.getResearchReportTableData(equityResearchFilter);
				EquityResearchResult absResearchReportResult = (EquityResearchResult) researchReportTableData.get(productId);
				Map<String, EquityResearchResult> dashboardResult = new HashMap<>();
				 
				dashboardResult.put(type, absResearchReportResult);
				return dashboardResult;
		} catch (Exception e) {
			logger.error("Web API Error: ", e.getMessage(), e);
			throw new WebApiException("Error has occurred in WebResearchReport -> getResearchResultDashboardData(...) method, Root Cause:: " + ExceptionUtil.getRootCause(e));
		}
	}

	@Override
	public void downloadResearchReport(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("reportFileName") String reportFileName) throws WebApiException {
		try {
			String reportPath = buildReportPath(request, reportFileName);
			File file = new File(reportPath);
			if (!file.exists()) {
				throw new Exception("Vendor Report Offering File " + reportFileName + " does not exist!!");
			} else {
				InputStream in = new FileInputStream(file);
				response.setHeader("Content-Disposition", "attachment; filename=" + reportFileName);
				response.setHeader("Content-Length", String.valueOf(file.length()));
				FileCopyUtils.copy(in, response.getOutputStream());
			}
		} catch (Exception e) {
			logger.error("Web API Error: ", e.getMessage(), e);
			throw new WebApiException(
					"Error has occurred in WebResearchReport -> downloadResearchReport(...) method, Root Cause:: "
							+ ExceptionUtil.getRootCause(e));
		}
	}

	private String buildReportPath(HttpServletRequest request, String reportFileName) throws Exception {
		String loggedInUser = WebUtil.getLoggedInUser(request);
		String basePath = finvendorProperties.getProperty("research_report_offering_file_basepath");
		String fullyQualifiedReportFilePath = basePath + File.separator + loggedInUser + File.separator
				+ reportFileName;
		return fullyQualifiedReportFilePath;
	}
}
