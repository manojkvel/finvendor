package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ven_data_aggr_offering_coverage")
public class VendorDataAggregatorsOfferingCoverage 
	implements Serializable {

	private static final long serialVersionUID = 16102016095903L;
	
	@Id
    @Column(name="product_id")
	private String productId;
	
	@Column(name="coverage_region")
	private String coverageRegion;
	
	@Column(name="coverage_country")
	private String coverageCountry;
	
	@Column(name="coverage_exchange")
	private String coverageExchange;
	
	@Column(name="cost_range")
	private float costRange;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone_number")
	private String phoneNumber;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCoverageRegion() {
		return coverageRegion;
	}

	public void setCoverageRegion(String coverageRegion) {
		this.coverageRegion = coverageRegion;
	}

	public String getCoverageCountry() {
		return coverageCountry;
	}

	public void setCoverageCountry(String coverageCountry) {
		this.coverageCountry = coverageCountry;
	}

	public String getCoverageExchange() {
		return coverageExchange;
	}

	public void setCoverageExchange(String coverageExchange) {
		this.coverageExchange = coverageExchange;
	}

	public float getCostRange() {
		return costRange;
	}

	public void setCostRange(float costRange) {
		this.costRange = costRange;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
