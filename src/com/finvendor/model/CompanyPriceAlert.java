package com.finvendor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="company_price_alert")
public class CompanyPriceAlert {
	
	@Id
    @Column(name="company_id")
    @GeneratedValue
    private Integer company_id;
	
    @Column(name="company_name")
    private String company_name;
    
    @Column(name="user_name")
    private String user_name;
    
    @Column(name="target_price")
    private String target_price;
    
    @Column(name="current_date")
    private String current_date;

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

	public String getTarget_price() {
		return target_price;
	}

	public void setTarget_price(String target_price) {
		this.target_price = target_price;
	}

	public String getCurrent_date() {
		return current_date;
	}

	public void setCurrent_date(String current_date) {
		this.current_date = current_date;
	}
}
