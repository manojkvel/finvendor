package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ven_analytics_app_soft_dtls")
public class VendorAnalyticsApplicationsSoftwareDetails 
	implements Serializable	{
	
	private static final long serialVersionUID = 07012017172701L;
	
	@Id
    @Column(name="product_id")
	private String productId; 
	
	@Column(name="accessbility")
	private String accessbility;
	
	@Column(name="suitability")
	private String suitability;
	
	@Column(name="cost_type")
	private String costType;
	
	@Column(name="sub_cost_pm")
	private float subCostPm;
	
	@Column(name="sub_cost_py")
	private float subCostPy;
	
	@Column(name="add_ons")
	private String addOns;
	
	@Column(name="operating_system")
	private String operatingSystem;
		
	@Column(name="soft_specification")
	private String softSpecification;
		
	@Column(name="user_base")
	private String userBase;
	
	@Column(name="customizable_calc_model")
	private String customizableCalcModel;
	
	@Column(name="real_time_market_data")
	private String realTimeMarketData;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getAccessbility() {
		return accessbility;
	}

	public void setAccessbility(String accessbility) {
		this.accessbility = accessbility;
	}

	public String getSuitability() {
		return suitability;
	}

	public void setSuitability(String suitability) {
		this.suitability = suitability;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public float getSubCostPm() {
		return subCostPm;
	}

	public void setSubCostPm(float subCostPm) {
		this.subCostPm = subCostPm;
	}

	public float getSubCostPy() {
		return subCostPy;
	}

	public void setSubCostPy(float subCostPy) {
		this.subCostPy = subCostPy;
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

	public String getSoftSpecification() {
		return softSpecification;
	}

	public void setSoftSpecification(String softSpecification) {
		this.softSpecification = softSpecification;
	}

	public String getUserBase() {
		return userBase;
	}

	public void setUserBase(String userBase) {
		this.userBase = userBase;
	}

	public String getCustomizableCalcModel() {
		return customizableCalcModel;
	}

	public void setCustomizableCalcModel(String customizableCalcModel) {
		this.customizableCalcModel = customizableCalcModel;
	}

	public String getRealTimeMarketData() {
		return realTimeMarketData;
	}

	public void setRealTimeMarketData(String realTimeMarketData) {
		this.realTimeMarketData = realTimeMarketData;
	}
	
}
