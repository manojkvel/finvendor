package com.finvendor.serverwebapi.resources.researchreport.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.finvendor.server.researchreport.dto.filter.impl.EquityResearchFilter;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.researchreport.IWebResearchReport;

public class WebSectorResearchReportImpl implements IWebResearchReport {

	public WebSectorResearchReportImpl() {
	}




	@Override
	public ResponseEntity<?> getResearchResultTableData(
			EquityResearchFilter equityResearchFilter, String type, String pageNumber, String perPageMaxRecords,
			String sortBy, String orderBy) throws WebApiException {
		return null;
	}

	@Override
	public ResponseEntity<?> getRecordStatistics(EquityResearchFilter equityResearchFilter, String type, String perPageMaxRecords)
			throws WebApiException {
		return null;
	}

	@Override
	public ResponseEntity<?> getResearchResultDashboardData(EquityResearchFilter equityResearchFilter,
			String type, String pageNumber, String perPageMaxRecords, String sortBy, String orderBy, String productId)
					throws WebApiException {
		return null;
	}

	@Override
	public ResponseEntity<?> downloadResearchReport(HttpServletRequest request, HttpServletResponse response, String productId, String reportName) throws WebApiException {
		return null;
	}
}
