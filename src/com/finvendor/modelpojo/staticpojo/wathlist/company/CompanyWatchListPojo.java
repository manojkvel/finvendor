package com.finvendor.modelpojo.staticpojo.wathlist.company;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "companyId", "companyName", "userName", "cmp", "newCmp", "diffCmp", "currentDate" })
public class CompanyWatchListPojo extends AbstractHomePageSearchPojo {

	private static final long serialVersionUID = -5787321024740863643L;
	private String cmp;
	private String newCmp;
	private String diffCmp;

	public String getCmp() {
		return cmp;
	}

	public void setCmp(String cmp) {
		this.cmp = cmp;
	}

	public String getNewCmp() {
		return newCmp;
	}

	public void setNewCmp(String newCmp) {
		this.newCmp = newCmp;
	}

	public String getDiffCmp() {
		return diffCmp;
	}

	public void setDiffCmp(String diffCmp) {
		this.diffCmp = diffCmp;
	}

}
