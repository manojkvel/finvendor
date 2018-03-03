package com.finvendor.serverwebapi.resources.researchreport;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.finvendor.common.util.ExceptionUtil;
import com.finvendor.server.researchreport.dto.filter.EquityResearchFilter;
import com.finvendor.server.researchreport.dto.result.EquityResearchResult;
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

	private static int cachedSearchFilterHashCode;
	private static int cachedDashboardFilterHashCode;
	private static int cachedProductId;
	
	private static Map<String, List<EquityResearchResult>> cachedSearchResult = new HashMap<>();
	private static Map<String, List<EquityResearchResult>> cachedDashboardResult = new HashMap<>();

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<EquityResearchResult>> getResearchResultTableData(@RequestBody EquityResearchFilter equityResearchFilter,
			@RequestParam(value = "type", required = true) String type) throws WebApiException {

		try {
			if (equityResearchFilter.getGeo() == null) {
				throw new Exception("Equity Research filter Error - Geo must not be null !!");
			}

			// Caching
			if (cachedSearchFilterHashCode == 0 || cachedSearchFilterHashCode != equityResearchFilter.hashCode()) {
				cachedSearchFilterHashCode = equityResearchFilter.hashCode();
				List<EquityResearchResult> researchReport = (List<EquityResearchResult>) equityResearchService.getResearchReportTableData(equityResearchFilter);
				cachedSearchResult.put(type, researchReport);
			}
			return cachedSearchResult;
		} catch (Exception e) {
			cachedSearchFilterHashCode=0;
			logger.error("Web API Error: ", e.getMessage(), e);
			throw new WebApiException(
					"Error has occurred in WebResearchReport -> getResearchResultTableData(...) method, Root Cause:: "
							+ ExceptionUtil.getRootCause(e));
		}
	}

	@Override
	public Map<String, List<EquityResearchResult>> getResearchResultDashboardData(@RequestParam("type") String type, @RequestParam("productId") String productId,
			@RequestBody EquityResearchFilter equityResearchFilter)	throws WebApiException {
		int prodId = productId.hashCode();
		try { 	
			if (cachedDashboardFilterHashCode == 0 ||
					(
							(cachedProductId==0 || cachedProductId != prodId) || cachedDashboardFilterHashCode != equityResearchFilter.hashCode()
					)
				) {
				Map<String, List<EquityResearchResult>> researchResultTableData = getResearchResultTableData(
						equityResearchFilter, type);
				List<EquityResearchResult> errList = researchResultTableData.get(type);
				for (EquityResearchResult err : errList) {
					if (err.getProductId().equals(productId)) {
						List<EquityResearchResult> list = new ArrayList<>();
						list.add(err);
						cachedDashboardResult.put(type, list);
						break;
					}
				}
				cachedDashboardFilterHashCode=equityResearchFilter.hashCode();
				cachedProductId=prodId;
			}
		} catch (Exception e) {
			cachedDashboardFilterHashCode=0;
			logger.error("Web API Error: ", e.getMessage(), e);
			throw new WebApiException("Error has occurred in WebResearchReport -> getResearchResultDashboardData(...) method, Root Cause:: " + ExceptionUtil.getRootCause(e));
		}
		return cachedDashboardResult;
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
