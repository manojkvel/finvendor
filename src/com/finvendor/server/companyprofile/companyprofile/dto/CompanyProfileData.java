package com.finvendor.server.companyprofile.companyprofile.dto;

/**
 * 
 * @author ayush on May 01, 2018
 */
public class CompanyProfileData {

	private String companyId;
	private String companyName;
	private String industry;
	private String mcap;
	private String cmp;
	private String absoluteLastChangedCmp;
	private String lastChangedCmpInPercentage;

	public CompanyProfileData(String companyId, String companyName, String industry, String mcap, String cmp,
			String absoluteLastChangedCmp, String lastChangedCmpInPercentage) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.industry = industry;
		this.mcap = mcap;
		this.cmp = cmp;
		this.absoluteLastChangedCmp = absoluteLastChangedCmp;
		this.lastChangedCmpInPercentage = lastChangedCmpInPercentage;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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

	public String getAbsoluteLastChangedCmp() {
		return absoluteLastChangedCmp;
	}

	public void setAbsoluteLastChangedCmp(String absoluteLastChangedCmp) {
		this.absoluteLastChangedCmp = absoluteLastChangedCmp;
	}

	public String getLastChangedCmpInPercentage() {
		return lastChangedCmpInPercentage;
	}

	public void setLastChangedCmpInPercentage(String lastChangedCmpInPercentage) {
		this.lastChangedCmpInPercentage = lastChangedCmpInPercentage;
	}
}
