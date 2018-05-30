package com.finvendor.server.companyprofile.pricealert.dto;

/**
 * 
 * @author Ayush on 20-May-2018
 */
public class CompanyEmailContent {

	private String userName;
	private String companyName;
	private String priceDate;

	private String cmpWhenPriceAlertWasSet;
	
	private float todaysCmp;
	private float yesterdayCmp;

	private String todaysCmpInPercentage = "N/A";

	private float lastWeekCmp;
	private String lastWeekCmpInPercentage = "N/A";

	private float lastMonthCmp;
	private String lastMonthCmpInPercentage = "N/A";

	private float noTimeFrame;
	private String noTimeFrameInPercentage = "N/A";

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPriceDate() {
		return priceDate;
	}

	public void setPriceDate(String priceDate) {
		this.priceDate = priceDate;
	}

	public float getTodaysCmp() {
		return todaysCmp;
	}

	public void setTodaysCmp(float todaysCmp) {
		this.todaysCmp = todaysCmp;
	}

	public float getYesterdayCmp() {
		return yesterdayCmp;
	}

	public void setYesterdayCmp(float yesterdayCmp) {
		this.yesterdayCmp = yesterdayCmp;
	}

	public String getTodaysCmpInPercentage() {
		return todaysCmpInPercentage;
	}

	public void setTodaysCmpInPercentage(String todaysCmpInPercentage) {
		this.todaysCmpInPercentage = todaysCmpInPercentage;
	}

	public float getLastWeekCmp() {
		return lastWeekCmp;
	}

	public void setLastWeekCmp(float lastWeekCmp) {
		this.lastWeekCmp = lastWeekCmp;
	}

	public String getLastWeekCmpInPercentage() {
		return lastWeekCmpInPercentage;
	}

	public void setLastWeekCmpInPercentage(String lastWeekCmpInPercentage) {
		this.lastWeekCmpInPercentage = lastWeekCmpInPercentage;
	}

	public float getLastMonthCmp() {
		return lastMonthCmp;
	}

	public void setLastMonthCmp(float lastMonthCmp) {
		this.lastMonthCmp = lastMonthCmp;
	}

	public String getLastMonthCmpInPercentage() {
		return lastMonthCmpInPercentage;
	}

	public void setLastMonthCmpInPercentage(String lastMonthCmpInPercentage) {
		this.lastMonthCmpInPercentage = lastMonthCmpInPercentage;
	}

	public float getNoTimeFrame() {
		return noTimeFrame;
	}

	public void setNoTimeFrame(float noTimeFrame) {
		this.noTimeFrame = noTimeFrame;
	}

	public String getNoTimeFrameInPercentage() {
		return noTimeFrameInPercentage;
	}

	public void setNoTimeFrameInPercentage(String noTimeFrameInPercentage) {
		this.noTimeFrameInPercentage = noTimeFrameInPercentage;
	}

	public String getCmpWhenPriceAlertWasSet() {
		return cmpWhenPriceAlertWasSet;
	}

	public void setCmpWhenPriceAlertWasSet(String cmpWhenPriceAlertWasSet) {
		this.cmpWhenPriceAlertWasSet = cmpWhenPriceAlertWasSet;
	}
}
