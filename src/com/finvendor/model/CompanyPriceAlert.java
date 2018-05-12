package com.finvendor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "company_price_alert")
@NamedQueries({
	@NamedQuery(name = CompanyPriceAlert.COMPANY_PRICEALERT_BY_USER_NQ, query = "from com.finvendor.model.CompanyPriceAlert where user_name like:username") })

public class CompanyPriceAlert {
	public static final String COMPANY_PRICEALERT_BY_USER_NQ = "companyPriceAlertByUser";
	@Id
	@Column(name = "company_id")
	private Integer company_id;

	@Column(name = "company_name")
	private String company_name;

	@Column(name = "user_name")
	private String user_name;

	@Column(name = "min_price")
	private String min_price;

	@Column(name = "max_price")
	private String max_price;

	@Column(name = "day_price")
	private String day_price;

	@Column(name = "week_price")
	private String week_price;

	@Column(name = "month_price")
	private String month_price;

	@Column(name = "enable_rsrch_price")
	private String enable_rsrch_price;

	@Column(name = "curr_date")
	private String curr_date;

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getMin_price() {
		return min_price;
	}

	public void setMin_price(String min_price) {
		this.min_price = min_price;
	}

	public String getMax_price() {
		return max_price;
	}

	public void setMax_price(String max_price) {
		this.max_price = max_price;
	}

	public String getDay_price() {
		return day_price;
	}

	public void setDay_price(String day_price) {
		this.day_price = day_price;
	}

	public String getWeek_price() {
		return week_price;
	}

	public void setWeek_price(String week_price) {
		this.week_price = week_price;
	}

	public String getMonth_price() {
		return month_price;
	}

	public void setMonth_price(String month_price) {
		this.month_price = month_price;
	}

	public String getEnable_rsrch_price() {
		return enable_rsrch_price;
	}

	public void setEnable_rsrch_price(String enable_rsrch_price) {
		this.enable_rsrch_price = enable_rsrch_price;
	}

	public String getCurr_date() {
		return curr_date;
	}

	public void setCurr_date(String curr_date) {
		this.curr_date = curr_date;
	}

	
}
