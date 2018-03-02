package com.finvendor.serverwebapi.resources.ifc.researchreport;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finvendor.server.researchreport.dto.filter.EquityResearchFilter;
import com.finvendor.server.researchreport.dto.result.EquityResearchResult;
import com.finvendor.server.researchreport.dto.result.dashboard.AbsResearchReportDashboardResult;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.ifc.common.WebUriConstants;

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
	//{"geo":"1","mcap":["Large Cap","Mid Cap","Small Cap","Micro Cap","Nano Cap"],"style":["Value","Growth","Income","Blend"],"analystType":["Broker","Independent","Others"],"researchedBroker":["vendor1"],"brokerYearOfInCorp":["<= 3 Yrs","3 - 5 Yrs","5 - 10 Yrs"],"brokerRank":["5","4"],"recommType":["accumulate","Reduce"],"awardWinnig":true,"researchCfa":true,"upside":[""]}
	@RequestMapping(value = WebUriConstants.ResearchReport.RESEARCH_REPORTS, method = RequestMethod.POST)
	@ResponseBody
	Map<String,EquityResearchResult> getResearchResultTableData(EquityResearchFilter equityResearchFilter, String type) throws WebApiException;
	
	/**
	 * 
	 * @param type
	 * @param companyId
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = WebUriConstants.ResearchReport.DASHBOARD_RESEARCH_REPORTS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	Map<String, List<EquityResearchResult>>  getResearchResultDashboardData(String type, String companyId,EquityResearchFilter equityResearchFilter) throws WebApiException;
	
	/**
	 * 
	 * @param reportFilePath
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = WebUriConstants.ResearchReport.DOWNLOAD_RESEARCH_REPORTS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	void downloadResearchReport(HttpServletRequest request, HttpServletResponse response, String reportFileName) throws WebApiException;
	
}
