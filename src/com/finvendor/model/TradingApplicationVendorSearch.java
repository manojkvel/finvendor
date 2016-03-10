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
@Table(name="tradingapplicationvendor_Search")
public class TradingApplicationVendorSearch implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="tradingapplicationvendor_search_id")
    @GeneratedValue
    private Integer tradingapplicationvendorSearchId;
	private String orderType;
	private String suitability;
	private String softwareType;
	private String regionofIncorp;
	private String countryofIncorp;
	private String profileFreshness;
	private String yearofOperation;
	private String searchKeywords;
	private String dataAttribute;
	private String coverageTime;
	private String awards;
	private String costRange;
	private String existingUser;
	private String assetClass;
	private String tradeAssetSubClass;
	private String tradingCapabilityType;
	private String tradeExecutionType;
	private String coverageRegion;
	private String coverageCountry;
	private String coverageExchange;
	private String darkpoolAccess;
	public Integer getTradingapplicationvendorSearchId() {
		return tradingapplicationvendorSearchId;
	}
	public void setTradingapplicationvendorSearchId(Integer tradingapplicationvendorSearchId) {
		this.tradingapplicationvendorSearchId = tradingapplicationvendorSearchId;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
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
	public String getRegionofIncorp() {
		return regionofIncorp;
	}
	public void setRegionofIncorp(String regionofIncorp) {
		this.regionofIncorp = regionofIncorp;
	}
	public String getCountryofIncorp() {
		return countryofIncorp;
	}
	public void setCountryofIncorp(String countryofIncorp) {
		this.countryofIncorp = countryofIncorp;
	}
	public String getProfileFreshness() {
		return profileFreshness;
	}
	public void setProfileFreshness(String profileFreshness) {
		this.profileFreshness = profileFreshness;
	}
	public String getYearofOperation() {
		return yearofOperation;
	}
	public void setYearofOperation(String yearofOperation) {
		this.yearofOperation = yearofOperation;
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
	public String getCoverageTime() {
		return coverageTime;
	}
	public void setCoverageTime(String coverageTime) {
		this.coverageTime = coverageTime;
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
	public String getExistingUser() {
		return existingUser;
	}
	public void setExistingUser(String existingUser) {
		this.existingUser = existingUser;
	}
	public String getAssetClass() {
		return assetClass;
	}
	public void setAssetClass(String assetClass) {
		this.assetClass = assetClass;
	}
	public String getTradeAssetSubClass() {
		return tradeAssetSubClass;
	}
	public void setTradeAssetSubClass(String tradeAssetSubClass) {
		this.tradeAssetSubClass = tradeAssetSubClass;
	}
	public String getTradingCapabilityType() {
		return tradingCapabilityType;
	}
	public void setTradingCapabilityType(String tradingCapabilityType) {
		this.tradingCapabilityType = tradingCapabilityType;
	}
	public String getTradeExecutionType() {
		return tradeExecutionType;
	}
	public void setTradeExecutionType(String tradeExecutionType) {
		this.tradeExecutionType = tradeExecutionType;
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
	public String getDarkpoolAccess() {
		return darkpoolAccess;
	}
	public void setDarkpoolAccess(String darkpoolAccess) {
		this.darkpoolAccess = darkpoolAccess;
	}
   	
}
