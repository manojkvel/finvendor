/**
 * 
 */
package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author rayulu vemula
 *
 */
@Entity
@Table(name="vendor_analyticsfeaturessupported")
public class VendorAnalyticsfeaturesSupported implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="AnalyticsfeaturesSupported_id")
	@GeneratedValue
    private Integer analyticsfeaturesSupportedId;
	
	
	@ManyToOne(targetEntity=Vendor.class,fetch=FetchType.LAZY)
	@JoinColumn(name="vendor_id", nullable=false)
	private Vendor vendor;
	
	@ManyToOne(targetEntity=Solutions.class,fetch=FetchType.EAGER)
	@JoinColumn(name="solution_id", nullable=false)
	private Solutions solution;
	@Column(name="SnalyticsSolutionsType")
	private String analyticsSolutionsType;
	@Column(name="AnalyticsSolutionsSubType")
	private String analyticsSolutionsSubType;
	
	
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	public Solutions getSolution() {
		return solution;
	}
	public void setSolution(Solutions solution) {
		this.solution = solution;
	}
	public Integer getAnalyticsfeaturesSupportedId() {
		return analyticsfeaturesSupportedId;
	}
	public void setAnalyticsfeaturesSupportedId(Integer analyticsfeaturesSupportedId) {
		this.analyticsfeaturesSupportedId = analyticsfeaturesSupportedId;
	}
	public String getAnalyticsSolutionsType() {
		return analyticsSolutionsType;
	}
	public void setAnalyticsSolutionsType(String analyticsSolutionsType) {
		this.analyticsSolutionsType = analyticsSolutionsType;
	}
	public String getAnalyticsSolutionsSubType() {
		return analyticsSolutionsSubType;
	}
	public void setAnalyticsSolutionsSubType(String analyticsSolutionsSubType) {
		this.analyticsSolutionsSubType = analyticsSolutionsSubType;
	}
	
	}
