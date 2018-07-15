package com.finvendor.server.metrics.service;

public enum MetricsType {
	EQTY_RESEARCH("EquityResearchReports"), 
	DOWNLOAD_EQTY_RESEARCH("DownloadEquityResearchReport")
	
	;
	private String value;

	private MetricsType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
