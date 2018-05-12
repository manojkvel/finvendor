package com.finvendor.modelpojo.staticpojo.wathlist.company;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * @author ayush on May 01, 2018
 */
@JsonPropertyOrder({ "companyId", "companyName", "userName", "minPrice", "maxPrice", "dayPrice","weekPrice","monthPrice","enableRsrchPrice", "currentDate" })
public class CompanyPriceAlertPojo extends AbstractHomePageSearchPojo {
	private static final long serialVersionUID = -1531614291471423894L;
	private String minPrice;
	private String maxPrice;
	private String dayPrice;
	private String weekPrice;
	private String monthPrice;
	private String enableRsrchPrice;

	public String getMinPrice() {
		return minPrice;
	}

	public String getMaxPrice() {
		return maxPrice;
	}

	public String getDayPrice() {
		return dayPrice;
	}

	public String getWeekPrice() {
		return weekPrice;
	}

	public String getMonthPrice() {
		return monthPrice;
	}

	public String getEnableRsrchPrice() {
		return enableRsrchPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}

	public void setDayPrice(String dayPrice) {
		this.dayPrice = dayPrice;
	}

	public void setWeekPrice(String weekPrice) {
		this.weekPrice = weekPrice;
	}

	public void setMonthPrice(String monthPrice) {
		this.monthPrice = monthPrice;
	}

	public void setEnableRsrchPrice(String enableRsrchPrice) {
		this.enableRsrchPrice = enableRsrchPrice;
	}
}
