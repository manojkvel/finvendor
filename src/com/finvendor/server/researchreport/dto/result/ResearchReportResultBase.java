package com.finvendor.server.researchreport.dto.result;

/**
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
public abstract class ResearchReportResultBase {
	
	protected String since;
	protected String awarded;
	
	protected String reportName;
	protected String reportDate;
	protected String analystNames;
	
	public ResearchReportResultBase() {
	}

	public String getSince() {
		return since;
	}

	public void setSince(String since) {
		this.since = since;
	}

	public String getAwarded() {
		return awarded;
	}

	public void setAwarded(String awarded) {
		this.awarded = awarded;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getAnalystNames() {
		return analystNames;
	}

	public void setAnalystNames(String analystNames) {
		this.analystNames = analystNames;
	}

	@Override
	public String toString() {
		return "ResearchReportResultBase [since=" + since + ", award=" + awarded + ", reportName=" + reportName
				+ ", reportDate=" + reportDate + ", analystNames=" + analystNames + "]";
	}
}
