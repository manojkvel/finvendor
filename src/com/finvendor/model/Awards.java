package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vendor_awards")
public class Awards implements Serializable{

	private static final long serialVersionUID = -9030715887562192502L;

	@Id
    @Column(name="va_id")
    @GeneratedValue
    private Integer award_id;
	
    @Column(name="Awardname")
    private String name;
    
    @Column(name="Awardsponsor")
    private String sponsor;
    
    @Column(name="vendor_id")
    private String vendorId;
    
    @Column(name="Awardedyear")
    private String year;
    
    @Column(name="awardAnalyticsSolutionsType")
    private String awardAnalyticsSolutionsType;
    
    @Column(name="awardAssetclass")
    private String awardAssetclass;
    
    @Column(name="awardResearchArea")
    private String awardResearchArea;
    
    @Column(name="awardSolutionTypes")
    private String awardSolutionTypes;
    
    @Column(name="awardVendorType")
    private String awardVendorType;

	public Integer getAward_id() {
		return award_id;
	}

	public void setAward_id(Integer award_id) {
		this.award_id = award_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getAwardAnalyticsSolutionsType() {
		return awardAnalyticsSolutionsType;
	}

	public void setAwardAnalyticsSolutionsType(String awardAnalyticsSolutionsType) {
		this.awardAnalyticsSolutionsType = awardAnalyticsSolutionsType;
	}

	public String getAwardAssetclass() {
		return awardAssetclass;
	}

	public void setAwardAssetclass(String awardAssetclass) {
		this.awardAssetclass = awardAssetclass;
	}

	public String getAwardResearchArea() {
		return awardResearchArea;
	}

	public void setAwardResearchArea(String awardResearchArea) {
		this.awardResearchArea = awardResearchArea;
	}

	public String getAwardSolutionTypes() {
		return awardSolutionTypes;
	}

	public void setAwardSolutionTypes(String awardSolutionTypes) {
		this.awardSolutionTypes = awardSolutionTypes;
	}

	public String getAwardVendorType() {
		return awardVendorType;
	}

	public void setAwardVendorType(String awardVendorType) {
		this.awardVendorType = awardVendorType;
	}
    
}
