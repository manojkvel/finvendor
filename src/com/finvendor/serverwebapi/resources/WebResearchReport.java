package com.finvendor.serverwebapi.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
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

import com.finvendor.common.util.ExceptionUtil;
import com.finvendor.server.researchreport.dto.result.EquityResearchResult;
import com.finvendor.server.researchreport.dto.result.dashboard.AbsResearchReportDashboardResult;
import com.finvendor.server.researchreport.dto.result.dashboard.EquityResearchResultDashboard;
import com.finvendor.server.researchreport.service.ifc.IResearchReportService;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.ifc.WebResearchReportIfc;
import com.finvendor.serverwebapi.utils.WebUtil;

/**
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
@Controller
// TBD: Later will replace with RestController to avoid @Responsebody annotation
public class WebResearchReport implements WebResearchReportIfc {
	private static Logger logger = LoggerFactory.getLogger(WebResearchReport.class);
	
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
	public Map<String, List<EquityResearchResult>> getResearchResultTableData(String type) throws WebApiException {
		try {
			List<EquityResearchResult> researchReport = (List<EquityResearchResult>) equityResearchService.getResearchReportTableData(null);
			Map<String, List<EquityResearchResult>> result = new HashMap<>();
			result.put(type, researchReport);
			return result;
		} catch (Exception e) {
			logger.error("Error has occurred in WebResearchReport -> getResearchResultTableData(...) method, Error Message: ", e.getMessage(), e);
			throw new WebApiException("Error has occurred in WebResearchReport -> getResearchResultTableData(...) method, Root Cause:: "+ExceptionUtil.getRootCauseMessage(e));
		}
	}

	@Override
	public AbsResearchReportDashboardResult getResearchResultDashboardData(String type, String companyId)
			throws WebApiException {
		try {
			return (EquityResearchResultDashboard) equityResearchService.getResearchReportDashboardData(companyId);
		} catch (Exception e) {
			logger.error("Error has occurred in WebResearchReport -> getResearchResultDashboardData(...) method, Error Message: ", e.getMessage(), e);
			throw new WebApiException("Error has occurred in WebResearchReport -> getResearchResultDashboardData(...) method, Root Cause:: " + ExceptionUtil.getRootCauseMessage(e));
		}
	}

	@Override
	public void downloadResearchReport(HttpServletRequest request, HttpServletResponse response, String reportFileName) throws WebApiException {
		try {
			String reportPath = buildReportPath(request, reportFileName);
			File file = new File(reportPath);
			if( ! file.exists()) {
				throw new Exception("Vendor Report Offering File "+reportFileName +" does not exist!!");
			} else {
				InputStream in = new FileInputStream(file);
				response.setHeader("Content-Disposition", "attachment; filename=" + reportFileName);
				response.setHeader("Content-Length", String.valueOf(file.length()));
				FileCopyUtils.copy(in, response.getOutputStream());
			}
		} catch (Exception e) {
			logger.error("Error has occurred in WebResearchReport -> downloadResearchReport(...) method, Error Message: ", e.getMessage(), e);
			throw new WebApiException("Error has occurred in WebResearchReport -> downloadResearchReport(...) method, Root Cause:: " + ExceptionUtil.getRootCauseMessage(e));
		}
	}

	private String buildReportPath(HttpServletRequest request, String reportFileName) throws Exception {
		String loggedInUser = WebUtil.getLoggedInUser(request);
		String basePath = finvendorProperties.getProperty("research_report_offering_file_basepath");
		String fullyQualifiedReportFilePath = basePath + File.separator + loggedInUser + File.separator	+ reportFileName;
		return fullyQualifiedReportFilePath;
	}
}
