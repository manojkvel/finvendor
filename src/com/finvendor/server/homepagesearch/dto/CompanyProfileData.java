package com.finvendor.server.homepagesearch.dto;

public class CompanyProfileData {

	private String companyName;
	private String industry;
	private String mcap;
	private String cmp;
	public CompanyProfileData(String companyName, String industry, String mcap, String cmp) {
		super();
		this.companyName = companyName;
		this.industry = industry;
		this.mcap = mcap;
		this.cmp = cmp;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getMcap() {
		return mcap;
	}
	public void setMcap(String mcap) {
		this.mcap = mcap;
	}
	public String getCmp() {
		return cmp;
	}
	public void setCmp(String cmp) {
		this.cmp = cmp;
	}
	@Override
	public String toString() {
		return "CompanyProfileData [companyName=" + companyName + ", industry=" + industry + ", mcap=" + mcap + ", cmp="
				+ cmp + "]";
	}
}
