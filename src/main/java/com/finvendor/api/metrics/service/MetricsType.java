package com.finvendor.api.metrics.service;

public enum MetricsType {
	EQTY_RESEARCH("EquityResearchReports"), 
	DOWNLOAD_EQTY_RESEARCH("DownloadEquityResearchReport"),
	HOME_PAGE("HomePage")
	
	;
	private String value;

	MetricsType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
