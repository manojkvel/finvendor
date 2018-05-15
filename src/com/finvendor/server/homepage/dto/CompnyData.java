package com.finvendor.server.homepage.dto;

public class CompnyData {

	private String companyName;
	private String isinCode;
	private String ticker;

	public CompnyData(String companyName, String isinCode, String ticker) {
		super();
		this.companyName = companyName;
		this.isinCode = isinCode;
		this.ticker = ticker;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getIsinCode() {
		return isinCode;
	}

	public void setIsinCode(String isinCode) {
		this.isinCode = isinCode;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	@Override
	public String toString() {
		return "CompnyData [companyName=" + companyName + ", isinCode=" + isinCode + ", ticker=" + ticker + "]";
	}
}
