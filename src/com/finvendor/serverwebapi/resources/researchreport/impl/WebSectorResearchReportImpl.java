package com.finvendor.serverwebapi.resources.researchreport.impl;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finvendor.server.researchreport.dto.filter.impl.EquityResearchFilter;
import com.finvendor.server.researchreport.dto.result.impl.EquityResearchResult;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.researchreport.IWebResearchReport;

public class WebSectorResearchReportImpl implements IWebResearchReport {

	public WebSectorResearchReportImpl() {
	}

	@Override
	public void downloadResearchReport(HttpServletRequest request, HttpServletResponse response, String reportFileName,
			String vendorName) throws WebApiException {
	}



	@Override
	public Map<String, Collection<EquityResearchResult>> getResearchResultTableData(
			EquityResearchFilter equityResearchFilter, String type, String pageNumber, String perPageMaxRecords,
			String sortBy, String orderBy) throws WebApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRecordStatistics(EquityResearchFilter equityResearchFilter, String type, String perPageMaxRecords)
			throws WebApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, EquityResearchResult> getResearchResultDashboardData(EquityResearchFilter equityResearchFilter,
			String type, String pageNumber, String perPageMaxRecords, String sortBy, String orderBy, String productId)
					throws WebApiException {
		// TODO Auto-generated method stub
		return null;
	}
}
