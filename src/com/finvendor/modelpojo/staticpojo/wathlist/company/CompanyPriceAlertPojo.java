package com.finvendor.modelpojo.staticpojo.wathlist.company;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * @author ayush on May 01, 2018
 */
@JsonPropertyOrder({ "companyId", "companyName", "userName", 
	"dayMinPrice","dayMaxPrice","weekMinPrice","weekMaxPrice","monthMinPrice","monthMaxPrice",
	"isResearchReport", "currDate" })
public class CompanyPriceAlertPojo extends AbstractHomePageSearchPojo {
	private static final long serialVersionUID = -1531614291471423894L;
	private String dayMinPrice;
	private String dayMaxPrice;
	private String weekMinPrice;
	private String weekMaxPrice;
	private String monthMinPrice;
	private String monthMaxPrice;
	private Boolean isResearchReport;

	public String getDayMinPrice() {
		return dayMinPrice;
	}

	public String getDayMaxPrice() {
		return dayMaxPrice;
	}

	public String getWeekMinPrice() {
		return weekMinPrice;
	}

	public String getWeekMaxPrice() {
		return weekMaxPrice;
	}

	public String getMonthMinPrice() {
		return monthMinPrice;
	}

	public String getMonthMaxPrice() {
		return monthMaxPrice;
	}


	public void setDayMinPrice(String dayMinPrice) {
		this.dayMinPrice = dayMinPrice;
	}

	public void setDayMaxPrice(String dayMaxPrice) {
		this.dayMaxPrice = dayMaxPrice;
	}

	public void setWeekMinPrice(String weekMinPrice) {
		this.weekMinPrice = weekMinPrice;
	}

	public void setWeekMaxPrice(String weekMaxPrice) {
		this.weekMaxPrice = weekMaxPrice;
	}

	public void setMonthMinPrice(String monthMinPrice) {
		this.monthMinPrice = monthMinPrice;
	}

	public void setMonthMaxPrice(String monthMaxPrice) {
		this.monthMaxPrice = monthMaxPrice;
	}

	public Boolean getIsResearchReport() {
		return isResearchReport;
	}

	public void setIsResearchReport(Boolean isResearchReport) {
		this.isResearchReport = isResearchReport;
	}
}
