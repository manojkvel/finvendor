package com.finvendor.server.homepagesearch.dto.staticpojo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "companyId", "companyName", "userName", "oldCmp","newCmp","diffCmp", "currentDate"})
public class CompanyWatchListPojo extends AbstractHomePageSearchPojo {
	private static final long serialVersionUID = -5787321024740863643L;
	private String oldCmp;
	private String newCmp;
	private String diffCmp;
	public String getOldCmp() {
		return oldCmp;
	}
	public void setOldCmp(String oldCmp) {
		this.oldCmp = oldCmp;
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
