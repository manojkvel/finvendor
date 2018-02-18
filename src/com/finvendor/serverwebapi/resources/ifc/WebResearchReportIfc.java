package com.finvendor.serverwebapi.resources.ifc;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finvendor.server.researchreport.dto.result.EquityResearchResult;
import com.finvendor.server.researchreport.dto.result.dashboard.AbsResearchReportDashboardResult;
import com.finvendor.serverwebapi.exception.WebApiException;

/**
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
@RequestMapping(WebUriConstants.BASE_URI)
public interface WebResearchReportIfc {

	/**
	 * 
	 * @param type
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = WebUriConstants.ResearchReport.RESEARCH_REPORTS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	Map<String,List<EquityResearchResult>> getResearchResultTableData(@RequestParam("type") String type) throws WebApiException;
	
	/**
	 * 
	 * @param type
	 * @param companyId
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = WebUriConstants.ResearchReport.DASHBOARD_RESEARCH_REPORTS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	AbsResearchReportDashboardResult getResearchResultDashboardData(@RequestParam("type") String type, @RequestParam("companyId") String companyId) throws WebApiException;
	
	/**
	 * 
	 * @param reportFilePath
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = WebUriConstants.ResearchReport.DOWNLOAD_RESEARCH_REPORTS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	void downloadResearchReport(HttpServletRequest request,HttpServletResponse response, @RequestParam("reportFileName") String reportFileName) throws WebApiException;
	
}
