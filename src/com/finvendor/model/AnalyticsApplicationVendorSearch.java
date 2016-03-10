/**
 * 
 */
package com.finvendor.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author rayulu vemula
 *
 */
@Entity
@Table(name="analyticsapplicationvendor_search")
public class AnalyticsApplicationVendorSearch implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="tradingapplicationvendor_search_id")
    @GeneratedValue
    private Integer tradingapplicationvendorSearchId;
	private String analyticsSolutionsType;
	private String analyticsSolutionsSubType;
	private String accessibility;
	private String suitability;
	private String softwareType;
	private String vendorRegionofIncorp;
	private String vendorCountryofIncorp;
	private String customizableCalcModel;
	private String realTimeMarketData;
	private String vendorProfileFreshness;
	private String vendorYearofOperation;
	private String searchKeywords;
	private String dataAttribute;
	private String vendorSupportCoverageRegion;
	private String vendorSupportCoverageTime;
	private String awards;
	private String costRange;
	private String userBase;
	public Integer getTradingapplicationvendorSearchId() {
		return tradingapplicationvendorSearchId;
	}
	public void setTradingapplicationvendorSearchId(Integer tradingapplicationvendorSearchId) {
		this.tradingapplicationvendorSearchId = tradingapplicationvendorSearchId;
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
	public String getSoftwareType() {
		return softwareType;
	}
	public void setSoftwareType(String softwareType) {
		this.softwareType = softwareType;
	}
	public String getVendorRegionofIncorp() {
		return vendorRegionofIncorp;
	}
	public void setVendorRegionofIncorp(String vendorRegionofIncorp) {
		this.vendorRegionofIncorp = vendorRegionofIncorp;
	}
	public String getVendorCountryofIncorp() {
		return vendorCountryofIncorp;
	}
	public void setVendorCountryofIncorp(String vendorCountryofIncorp) {
		this.vendorCountryofIncorp = vendorCountryofIncorp;
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
	public String getVendorProfileFreshness() {
		return vendorProfileFreshness;
	}
	public void setVendorProfileFreshness(String vendorProfileFreshness) {
		this.vendorProfileFreshness = vendorProfileFreshness;
	}
	public String getVendorYearofOperation() {
		return vendorYearofOperation;
	}
	public void setVendorYearofOperation(String vendorYearofOperation) {
		this.vendorYearofOperation = vendorYearofOperation;
	}
	public String getSearchKeywords() {
		return searchKeywords;
	}
	public void setSearchKeywords(String searchKeywords) {
		this.searchKeywords = searchKeywords;
	}
	public String getDataAttribute() {
		return dataAttribute;
	}
	public void setDataAttribute(String dataAttribute) {
		this.dataAttribute = dataAttribute;
	}
	public String getVendorSupportCoverageRegion() {
		return vendorSupportCoverageRegion;
	}
	public void setVendorSupportCoverageRegion(String vendorSupportCoverageRegion) {
		this.vendorSupportCoverageRegion = vendorSupportCoverageRegion;
	}
	public String getVendorSupportCoverageTime() {
		return vendorSupportCoverageTime;
	}
	public void setVendorSupportCoverageTime(String vendorSupportCoverageTime) {
		this.vendorSupportCoverageTime = vendorSupportCoverageTime;
	}
	public String getAwards() {
		return awards;
	}
	public void setAwards(String awards) {
		this.awards = awards;
	}
	public String getCostRange() {
		return costRange;
	}
	public void setCostRange(String costRange) {
		this.costRange = costRange;
	}
	public String getUserBase() {
		return userBase;
	}
	public void setUserBase(String userBase) {
		this.userBase = userBase;
	}

	
}
