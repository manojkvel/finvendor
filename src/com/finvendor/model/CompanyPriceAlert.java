package com.finvendor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "company_price_alert")
@NamedQueries({
		@NamedQuery(name = CompanyPriceAlert.COMPANY_PRICEALERT_BY_USER_NQ, query = "from com.finvendor.model.CompanyPriceAlert where user_name like:username"),
		@NamedQuery(name = CompanyPriceAlert.COMPANY_PRICE_ALERT_BY_COMPANY_ID_AND_USER_NAME_NAMED_QUERY, query = "from com.finvendor.model.CompanyPriceAlert where user_name like:username and company_id like:companyId") })

public class CompanyPriceAlert {
	public static final String COMPANY_PRICEALERT_BY_USER_NQ = "companyPriceAlertByUser";
	public static final String COMPANY_PRICE_ALERT_BY_COMPANY_ID_AND_USER_NAME_NAMED_QUERY = "companyPriceAlertByUserAndCompanyId";
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@Column(name = "company_id")
	private Integer company_id;

	@Column(name = "company_name")
	private String company_name;

	@Column(name = "user_name")
	private String user_name;

	@Column(name = "cmp_when_price_alert_set")
	private String cmp_when_price_alert_set;

	@Column(name = "day_min_price")
	private String day_min_price;

	@Column(name = "day_max_price")
	private String day_max_price;

	@Column(name = "week_min_price")
	private String week_min_price;

	@Column(name = "week_max_price")
	private String week_max_price;

	@Column(name = "month_min_price")
	private String month_min_price;

	@Column(name = "month_max_price")
	private String month_max_price;

	@Column(name = "is_research_price")
	private String is_research_price;

	@Column(name = "no_time_frame_min_price")
	private String no_time_frame_min_price;

	@Column(name = "no_time_frame_max_price")
	private String no_time_frame_max_price;

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

	public String getCmp_when_price_alert_set() {
		return cmp_when_price_alert_set;
	}

	public void setCmp_when_price_alert_set(String cmp_when_price_alert_set) {
		this.cmp_when_price_alert_set = cmp_when_price_alert_set;
	}

	public String getDay_min_price() {
		return day_min_price;
	}

	public void setDay_min_price(String day_min_price) {
		this.day_min_price = day_min_price;
	}

	public String getDay_max_price() {
		return day_max_price;
	}

	public void setDay_max_price(String day_max_price) {
		this.day_max_price = day_max_price;
	}

	public String getWeek_min_price() {
		return week_min_price;
	}

	public void setWeek_min_price(String week_min_price) {
		this.week_min_price = week_min_price;
	}

	public String getWeek_max_price() {
		return week_max_price;
	}

	public void setWeek_max_price(String week_max_price) {
		this.week_max_price = week_max_price;
	}

	public String getMonth_min_price() {
		return month_min_price;
	}

	public void setMonth_min_price(String month_min_price) {
		this.month_min_price = month_min_price;
	}

	public String getMonth_max_price() {
		return month_max_price;
	}

	public void setMonth_max_price(String month_max_price) {
		this.month_max_price = month_max_price;
	}

	public String getIs_research_price() {
		return is_research_price;
	}

	public void setIs_research_price(String is_research_price) {
		this.is_research_price = is_research_price;
	}

	public String getNo_time_frame_min_price() {
		return no_time_frame_min_price;
	}

	public void setNo_time_frame_min_price(String no_time_frame_min_price) {
		this.no_time_frame_min_price = no_time_frame_min_price;
	}

	public String getNo_time_frame_max_price() {
		return no_time_frame_max_price;
	}

	public void setNo_time_frame_max_price(String no_time_frame_max_price) {
		this.no_time_frame_max_price = no_time_frame_max_price;
	}

	public String getCurr_date() {
		return curr_date;
	}

	public void setCurr_date(String curr_date) {
		this.curr_date = curr_date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
