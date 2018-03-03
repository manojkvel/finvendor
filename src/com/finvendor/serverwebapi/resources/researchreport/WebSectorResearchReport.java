package com.finvendor.serverwebapi.resources.researchreport;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finvendor.server.researchreport.dto.filter.EquityResearchFilter;
import com.finvendor.server.researchreport.dto.result.EquityResearchResult;
import com.finvendor.server.researchreport.dto.result.dashboard.AbsResearchReportDashboardResult;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.ifc.researchreport.WebResearchReportIfc;

public class WebSectorResearchReport implements WebResearchReportIfc {

	public WebSectorResearchReport() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public void downloadResearchReport(HttpServletRequest request, HttpServletResponse response, String reportFileName)
			throws WebApiException {
		// TODO Auto-generated method stub

	}




	@Override
	public Map<String, List<EquityResearchResult>> getResearchResultDashboardData(String type, String companyId,
			EquityResearchFilter equityResearchFilter) throws WebApiException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Map<String, List<EquityResearchResult>> getResearchResultTableData(EquityResearchFilter equityResearchFilter,
			String type) throws WebApiException {
		// TODO Auto-generated method stub
		return null;
	}
}
