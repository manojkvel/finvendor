package com.finvendor.server.researchreport.dto.result.dashboard.impl;

import com.finvendor.server.researchreport.dto.result.dashboard.AbsResearchReportDashboardResult;

/**
 * @author ayush on Feb 18, 2018
 */
public class EquityResearchResultDashboard extends AbsResearchReportDashboardResult{
	private String companyName;
	private String reportName;
	private String reportSummary;
	private String reportFileName;

	public EquityResearchResultDashboard() {
	}

	public EquityResearchResultDashboard(String companyName, String reportName, String reportSummary,
			String reportFileName) {
		super();
		this.companyName = companyName;
		this.reportName = reportName;
		this.reportSummary = reportSummary;
		this.reportFileName = reportFileName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportSummary() {
		return reportSummary;
	}

	public void setReportSummary(String reportSummary) {
		this.reportSummary = reportSummary;
	}

	public String getReportFileName() {
		return reportFileName;
	}

	public void setReportFileName(String reportFileName) {
		this.reportFileName = reportFileName;
	}
}
