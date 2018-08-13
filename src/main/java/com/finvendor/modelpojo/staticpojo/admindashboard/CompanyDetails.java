package com.finvendor.modelpojo.staticpojo.admindashboard;

import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CompanyDetails that = (CompanyDetails) o;
		return companyId == that.companyId &&
				Objects.equals(companyName, that.companyName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(companyId, companyName);
	}
}
