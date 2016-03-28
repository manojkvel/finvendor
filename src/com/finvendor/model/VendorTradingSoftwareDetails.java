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

@Entity
@Table(name="vendor_TradingSoftwareDetails")
public class VendorTradingSoftwareDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="TradingSoftwareDetails_id")
	@GeneratedValue
    private Integer TradingSoftwareDetailsId;
	
	
	@ManyToOne(targetEntity=Vendor.class,fetch=FetchType.LAZY)
	@JoinColumn(name="vendor_id", nullable=false)
	private Vendor vendor;
	
	@ManyToOne(targetEntity=Solutions.class,fetch=FetchType.EAGER)
	@JoinColumn(name="solution_id", nullable=false)
	private Solutions solution;
	
	@ManyToOne(targetEntity=AssetClass.class,fetch=FetchType.EAGER)
	@JoinColumn(name="asset_class_id", nullable=false)
	private AssetClass assetClass;
	
	@Column(name="Offering")
	private String offering;
	
	@Column(name="Offering_Desc")
	private String offeringDesc;
	
	@Column(name="AppName")
	private String appName;
	@Column(name="AppDesc")
	private String appDesc;
	@Column(name="TradableRegions")
	private String tradableRegions;
	@Column(name="TradableMarkets")
	private String tradableMarkets;
	@Column(name="accessibility")
	private String accessibility;
	@Column(name="Suitability")
	private String suitability;
	@Column(name="CostType")
	private String costType;
	@Column(name="PlatformCCY")
	private String platformCCY;
	@Column(name="PlatformCost")
	private String platformCost;
	@Column(name="Platformtype")
	private String platformtype;
	@Column(name="CxchangeFees")
	private String exchangeFees;
	@Column(name="StreNews")
	private String streNews;
	@Column(name="chartsAvai")
	private String chartsAvai;
	@Column(name="OrderType")
	private String orderType;
	@Column(name="PriceAlerts")
	private String priceAlerts;
	@Column(name="Watchlist")
	private String watchlist;
	@Column(name="TradingCap")
	private String tradingCap;
	@Column(name="TradeExec")
	private String tradeExec;
	@Column(name="TradeType")
	private String tradeType;
	@Column(name="Darkvenues")
	private String darkvenues;
	@Column(name="AddOns")
	private String addOns;
	@Column(name="OpeSystem")
	private String opeSystem;
	@Column(name="LaunchedYear")
	private String launchedYear;
	@Column(name="ClientBase")
	private String clientBase;
	@Column(name="SecurityName")
	private String SecurityName;
	
	
	public String getSecurityName() {
		return SecurityName;
	}
	public void setSecurityName(String securityName) {
		SecurityName = securityName;
	}
	public Integer getTradingSoftwareDetailsId() {
		return TradingSoftwareDetailsId;
	}
	public void setTradingSoftwareDetailsId(Integer tradingSoftwareDetailsId) {
		TradingSoftwareDetailsId = tradingSoftwareDetailsId;
	}
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
	
	public AssetClass getAssetClass() {
		return assetClass;
	}
	public void setAssetClass(AssetClass assetClass) {
		this.assetClass = assetClass;
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
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppDesc() {
		return appDesc;
	}
	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}
	public String getTradableRegions() {
		return tradableRegions;
	}
	public void setTradableRegions(String tradableRegions) {
		this.tradableRegions = tradableRegions;
	}
	public String getTradableMarkets() {
		return tradableMarkets;
	}
	public void setTradableMarkets(String tradableMarkets) {
		this.tradableMarkets = tradableMarkets;
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
	public String getCostType() {
		return costType;
	}
	public void setCostType(String costType) {
		this.costType = costType;
	}
	public String getPlatformCCY() {
		return platformCCY;
	}
	public void setPlatformCCY(String platformCCY) {
		this.platformCCY = platformCCY;
	}
	public String getPlatformCost() {
		return platformCost;
	}
	public void setPlatformCost(String platformCost) {
		this.platformCost = platformCost;
	}
	public String getPlatformtype() {
		return platformtype;
	}
	public void setPlatformtype(String platformtype) {
		this.platformtype = platformtype;
	}
	public String getExchangeFees() {
		return exchangeFees;
	}
	public void setExchangeFees(String exchangeFees) {
		this.exchangeFees = exchangeFees;
	}
	public String getStreNews() {
		return streNews;
	}
	public void setStreNews(String streNews) {
		this.streNews = streNews;
	}
	public String getChartsAvai() {
		return chartsAvai;
	}
	public void setChartsAvai(String chartsAvai) {
		this.chartsAvai = chartsAvai;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getPriceAlerts() {
		return priceAlerts;
	}
	public void setPriceAlerts(String priceAlerts) {
		this.priceAlerts = priceAlerts;
	}
	public String getWatchlist() {
		return watchlist;
	}
	public void setWatchlist(String watchlist) {
		this.watchlist = watchlist;
	}
	public String getTradingCap() {
		return tradingCap;
	}
	public void setTradingCap(String tradingCap) {
		this.tradingCap = tradingCap;
	}
	public String getTradeExec() {
		return tradeExec;
	}
	public void setTradeExec(String tradeExec) {
		this.tradeExec = tradeExec;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getDarkvenues() {
		return darkvenues;
	}
	public void setDarkvenues(String darkvenues) {
		this.darkvenues = darkvenues;
	}
	public String getAddOns() {
		return addOns;
	}
	public void setAddOns(String addOns) {
		this.addOns = addOns;
	}
	public String getOpeSystem() {
		return opeSystem;
	}
	public void setOpeSystem(String opeSystem) {
		this.opeSystem = opeSystem;
	}
	public String getLaunchedYear() {
		return launchedYear;
	}
	public void setLaunchedYear(String launchedYear) {
		this.launchedYear = launchedYear;
	}
	public String getClientBase() {
		return clientBase;
	}
	public void setClientBase(String clientBase) {
		this.clientBase = clientBase;
	}
	
	
	
	}
