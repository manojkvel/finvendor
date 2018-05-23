package com.finvendor.server.scheduler.dto;

/**
 * 
 * @author Ayush on 20-May-2018
 */
public class CompanyEmailContent {

	private String userName;
	private String companyName;
	private String priceDate;

	private float todaysCmp;
	private float yesterdayCmp;

	private String todaysCmpInPercentageStr="N/A";

	private float lastWeekCmp;
	private String lastWeekCmpInPercentageStr="N/A";

	private float lastMonthCmp;
	private String lastMonthCmpInPercentageStr="N/A";
	
	private float noTimeFrame;
	private String noTimeFrameInPercentageStr="N/A";

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

	public String getTodaysCmpInPercentageStr() {
		return todaysCmpInPercentageStr;
	}

	public void setTodaysCmpInPercentageStr(String todaysCmpInPercentageStr) {
		this.todaysCmpInPercentageStr = todaysCmpInPercentageStr;
	}

	public float getLastWeekCmp() {
		return lastWeekCmp;
	}

	public void setLastWeekCmp(float lastWeekCmp) {
		this.lastWeekCmp = lastWeekCmp;
	}

	public String getLastWeekCmpInPercentageStr() {
		return lastWeekCmpInPercentageStr;
	}

	public void setLastWeekCmpInPercentageStr(String lastWeekCmpInPercentageStr) {
		this.lastWeekCmpInPercentageStr = lastWeekCmpInPercentageStr;
	}

	public float getLastMonthCmp() {
		return lastMonthCmp;
	}

	public void setLastMonthCmp(float lastMonthCmp) {
		this.lastMonthCmp = lastMonthCmp;
	}

	public String getLastMonthCmpInPercentageStr() {
		return lastMonthCmpInPercentageStr;
	}

	public void setLastMonthCmpInPercentageStr(String lastMonthCmpInPercentageStr) {
		this.lastMonthCmpInPercentageStr = lastMonthCmpInPercentageStr;
	}

	public float getNoTimeFrame() {
		return noTimeFrame;
	}

	public void setNoTimeFrame(float noTimeFrame) {
		this.noTimeFrame = noTimeFrame;
	}

	public String getNoTimeFrameInPercentageStr() {
		return noTimeFrameInPercentageStr;
	}

	public void setNoTimeFrameInPercentageStr(String noTimeFrameInPercentageStr) {
		this.noTimeFrameInPercentageStr = noTimeFrameInPercentageStr;
	}
}
