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
@Table(name="vendor_analyticssoftwaredetails")
public class VendorAnalyticsSoftwareDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="AnalyticsSoftwareDetails_id")
	@GeneratedValue
    private Integer analyticsSoftwareDetailsId;
	
	
	@ManyToOne(targetEntity=Vendor.class,fetch=FetchType.LAZY)
	@JoinColumn(name="vendor_id", nullable=false)
	private Vendor vendor;
	
	@ManyToOne(targetEntity=Solutions.class,fetch=FetchType.EAGER)
	@JoinColumn(name="solution_id", nullable=false)
	private Solutions solution;
	
	@Column(name="Offering")
	private String offering;
	@Column(name="OfferingDesc")
	private String offeringDesc;
	@Column(name="AnalyticsSolutionsType")
	private String analyticsSolutionsType;
	@Column(name="ApplicationSubscriptionCost")
	private String applicationSubscriptionCost;
	@Column(name="AnalyticsSolutionsSubType")
	private String analyticsSolutionsSubType;
	@Column(name="ApplicationName")
	private String applicationName;
	@Column(name="ApplicationBriefDesc")
	private String applicationBriefDesc;
	@Column(name="Accessibility")
	private String accessibility;
	@Column(name="Suitability")
	private String suitability;
	@Column(name="ApplicationCostType")
	private String applicationCostType;
	@Column(name="ApplicationSubscriptionCCY")
	private String applicationSubscriptionCCY;
	@Column(name="ApplicationSubscriptionType")
	private String applicationSubscriptionType;
	@Column(name="RealTimeMarketData")
	private String realTimeMarketData;
	@Column(name="CustomizableCalculationModels")
	private String customizableCalculationModels;
	@Column(name="AddOns")
	private String addOns;
	@Column(name="OperatingSystem")
	private String operatingSystem;
	@Column(name="Softwarespecifications")
	private String softwarespecifications;
	@Column(name="LaunchedYear")
	private String launchedYear;
	@Column(name="ExistingUserBase")
	private String existingUserBase;
	

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
	public Integer getAnalyticsSoftwareDetailsId() {
		return analyticsSoftwareDetailsId;
	}
	public void setAnalyticsSoftwareDetailsId(Integer analyticsSoftwareDetailsId) {
		this.analyticsSoftwareDetailsId = analyticsSoftwareDetailsId;
	}
	public String getAnalyticsSolutionsType() {
		return analyticsSolutionsType;
	}
	public void setAnalyticsSolutionsType(String analyticsSolutionsType) {
		this.analyticsSolutionsType = analyticsSolutionsType;
	}
	
	
	public String getOffering() {
		return offering;
	}
	public void setOffering(String offering) {
		this.offering = offering;
	}
	public String getOfferingDesc() {
		return offeringDesc;
	}
	public void setOfferingDesc(String offeringDesc) {
		this.offeringDesc = offeringDesc;
	}
	public String getApplicationSubscriptionCost() {
		return applicationSubscriptionCost;
	}
	public void setApplicationSubscriptionCost(String applicationSubscriptionCost) {
		this.applicationSubscriptionCost = applicationSubscriptionCost;
	}
	public String getAnalyticsSolutionsSubType() {
		return analyticsSolutionsSubType;
	}
	public void setAnalyticsSolutionsSubType(String analyticsSolutionsSubType) {
		this.analyticsSolutionsSubType = analyticsSolutionsSubType;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getApplicationBriefDesc() {
		return applicationBriefDesc;
	}
	public void setApplicationBriefDesc(String applicationBriefDesc) {
		this.applicationBriefDesc = applicationBriefDesc;
	}
	public String getAccessibility() {
		return accessibility;
	}
	public void setAccessibility(String accessibility) {
		this.accessibility = accessibility;
	}
	public String getSuitability() {
		return suitability;
	}
	public void setSuitability(String suitability) {
		this.suitability = suitability;
	}
	public String getApplicationCostType() {
		return applicationCostType;
	}
	public void setApplicationCostType(String applicationCostType) {
		this.applicationCostType = applicationCostType;
	}
	public String getApplicationSubscriptionCCY() {
		return applicationSubscriptionCCY;
	}
	public void setApplicationSubscriptionCCY(String applicationSubscriptionCCY) {
		this.applicationSubscriptionCCY = applicationSubscriptionCCY;
	}
	public String getApplicationSubscriptionType() {
		return applicationSubscriptionType;
	}
	public void setApplicationSubscriptionType(String applicationSubscriptionType) {
		this.applicationSubscriptionType = applicationSubscriptionType;
	}
	public String getRealTimeMarketData() {
		return realTimeMarketData;
	}
	public void setRealTimeMarketData(String realTimeMarketData) {
		this.realTimeMarketData = realTimeMarketData;
	}
	public String getCustomizableCalculationModels() {
		return customizableCalculationModels;
	}
	public void setCustomizableCalculationModels(String customizableCalculationModels) {
		this.customizableCalculationModels = customizableCalculationModels;
	}
	public String getAddOns() {
		return addOns;
	}
	public void setAddOns(String addOns) {
		this.addOns = addOns;
	}
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	public String getSoftwarespecifications() {
		return softwarespecifications;
	}
	public void setSoftwarespecifications(String softwarespecifications) {
		this.softwarespecifications = softwarespecifications;
	}
	public String getLaunchedYear() {
		return launchedYear;
	}
	public void setLaunchedYear(String launchedYear) {
		this.launchedYear = launchedYear;
	}
	public String getExistingUserBase() {
		return existingUserBase;
	}
	public void setExistingUserBase(String existingUserBase) {
		this.existingUserBase = existingUserBase;
	}
		
	
	}
