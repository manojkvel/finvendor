package com.finvendor.modelpojo.staticpojo.wathlist.company;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * @author ayush on May 01, 2018
 */
@JsonPropertyOrder({ "companyId", "companyName", "userName", "cmp", "newCmp", "percentageChangeSinceAdded",
		"todaysChange", "todaysChangeInPercentage", "currentDate" })
public class CompanyWatchListPojo extends AbstractHomePageSearchPojo {

	private static final long serialVersionUID = -5787321024740863643L;
	private String cmp;
	private String newCmp;
	private String percentageChangeSinceAdded;
	private String todaysChange;
	private String todaysChangeInPercentage;

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

	public String getPercentageChangeSinceAdded() {
		return percentageChangeSinceAdded;
	}

	public void setPercentageChangeSinceAdded(String percentageChangeSinceAdded) {
		this.percentageChangeSinceAdded = percentageChangeSinceAdded;
	}

	public String getTodaysChange() {
		return todaysChange;
	}

	public void setTodaysChange(String todaysChange) {
		this.todaysChange = todaysChange;
	}

	public String getTodaysChangeInPercentage() {
		return todaysChangeInPercentage;
	}

	public void setTodaysChangeInPercentage(String todaysChangeInPercentage) {
		this.todaysChangeInPercentage = todaysChangeInPercentage;
	}
}
