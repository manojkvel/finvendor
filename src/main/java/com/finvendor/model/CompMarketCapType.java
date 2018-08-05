package com.finvendor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ayush on 28-apr-2018
 */
@Entity
@Table(name="comp_mkt_cap_type")
public class CompMarketCapType {
	
	@Id
    @Column(name="company_id")
    private String company_id;
	
    @Column(name="market_cap_id")
    private String market_cap_id;

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getMarket_cap_id() {
		return market_cap_id;
	}

	public void setMarket_cap_id(String market_cap_id) {
		this.market_cap_id = market_cap_id;
	}
}
