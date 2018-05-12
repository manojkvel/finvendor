package com.finvendor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author ayush on 28-apr-2018
 */
@Entity
@Table(name="company_watchlist")
@NamedQueries({
	@NamedQuery(name = CompanyWatchList.COMPANY_WATCHLIST_BY_USER_NQ, query = "from com.finvendor.model.CompanyWatchList where user_name like:username") })
public class CompanyWatchList {
	public static final String COMPANY_WATCHLIST_BY_USER_NQ = "companyWatchListByUser";
	@Id
    @Column(name="company_id")
    private Integer company_id;
	
    @Column(name="company_name")
    private String company_name;
    
    @Column(name="user_name")
    private String user_name;
    
    @Column(name="close_price")
    private String close_price;
    
    @Column(name="curr_date")
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

	public String getClose_price() {
		return close_price;
	}

	public void setClose_price(String close_price) {
		this.close_price = close_price;
	}

	public String getCurr_date() {
		return curr_date;
	}

	public void setCurr_date(String curr_date) {
		this.curr_date = curr_date;
	}

}
