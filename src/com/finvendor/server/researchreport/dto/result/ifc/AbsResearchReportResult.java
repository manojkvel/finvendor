package com.finvendor.server.researchreport.dto.result.ifc;

/**
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
public abstract class AbsResearchReportResult {
	
	protected String report;
	protected String researchDate;
	protected String analystName;
	protected String vendorName;

	protected String since;
	protected String awarded;
	
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getResearchDate() {
		return researchDate;
	}
	public void setResearchDate(String researchDate) {
		this.researchDate = researchDate;
	}
	public String getAnalystName() {
		return analystName;
	}
	public void setAnalystName(String analystName) {
		this.analystName = analystName;
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
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	
}
