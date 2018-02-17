package com.finvendor.modelpojo.staticpojo;

/**
 * 
 * @author ayush
 *
 */
public class CompanyDetails {
	private int companyId;
	private String companyName;

	public CompanyDetails(int companyId, String companyName) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "CompanyDetails [companyId=" + companyId + ", companyName=" + companyName + "]";
	}
}
