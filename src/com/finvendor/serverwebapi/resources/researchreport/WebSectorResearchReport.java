package com.finvendor.serverwebapi.resources.researchreport;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finvendor.server.researchreport.dto.filter.EquityResearchFilter;
import com.finvendor.server.researchreport.dto.result.EquityResearchResult;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.ifc.researchreport.WebResearchReportIfc;

public class WebSectorResearchReport implements WebResearchReportIfc {

	public WebSectorResearchReport() {
	}

	@Override
	public Map<String, Collection<EquityResearchResult>> getResearchResultTableData(
			EquityResearchFilter equityResearchFilter, String type) throws WebApiException {
		return null;
	}

	@Override
	public Map<String, EquityResearchResult> getResearchResultDashboardData(String type, String productId,
			EquityResearchFilter equityResearchFilter) throws WebApiException {
		return null;
	}


	@Override
	public void downloadResearchReport(HttpServletRequest request, HttpServletResponse response, String reportFileName,
			String vendorName) throws WebApiException {
	}
}
