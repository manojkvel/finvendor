/**
 * 
 */
package com.finvendor.modelpojo.staticpojo;

import com.finvendor.model.VendorTradingSoftwareDetails;

public class VendorTradingSoftwareDetailsForm{
	private String id;
	private String solution;
	private String assetClass;
	private String appName;
	private String appDesc;
	private String tradableRegions;
	private String tradableMarkets;
	private String accessibility;
	private String suitability;
	private String costType;
	private String platformCCY;
	private String platformCost;
	private String platformtype;
	private String exchangeFees;
	private String streNews;
	private String chartsAvai;
	private String orderType;
	private String watchlist;
	private String tradingCap;
	private String tradeExec;
	private String tradeType;
	private String darkVenues;
	private String addOns;
	private String opeSystem;
	private String launchedYear;
	private String clientBase;
	private String priceAlerts;
	private String offering;
	private String offeringDesc;
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getDarkVenues() {
		return darkVenues;
	}
	public void setDarkVenues(String darkVenues) {
		this.darkVenues = darkVenues;
	}
	public String getAssetClass() {
		return assetClass;
	}
	public void setAssetClass(String assetClass) {
		this.assetClass = assetClass;
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
	public VendorTradingSoftwareDetails insertDataToModel(
            VendorTradingSoftwareDetailsForm vendorTradingSoftwareDetailsForm) {
		VendorTradingSoftwareDetails vendorTradingSoftwareDetails = new VendorTradingSoftwareDetails();
	
		vendorTradingSoftwareDetails.setAccessibility(vendorTradingSoftwareDetailsForm.getAccessibility());
		vendorTradingSoftwareDetails.setAddOns(vendorTradingSoftwareDetailsForm.getAddOns());
		vendorTradingSoftwareDetails.setAppDesc(vendorTradingSoftwareDetailsForm.getAppDesc());
		vendorTradingSoftwareDetails.setAppName(vendorTradingSoftwareDetailsForm.getAppName());
		vendorTradingSoftwareDetails.setChartsAvai(vendorTradingSoftwareDetailsForm.getChartsAvai());
		vendorTradingSoftwareDetails.setClientBase(vendorTradingSoftwareDetailsForm.getClientBase());
		vendorTradingSoftwareDetails.setCostType(vendorTradingSoftwareDetailsForm.getCostType());
		vendorTradingSoftwareDetails.setDarkvenues(vendorTradingSoftwareDetailsForm.getDarkVenues());
		vendorTradingSoftwareDetails.setExchangeFees(vendorTradingSoftwareDetailsForm.getExchangeFees());
		vendorTradingSoftwareDetails.setLaunchedYear(vendorTradingSoftwareDetailsForm.getLaunchedYear());
		vendorTradingSoftwareDetails.setOpeSystem(vendorTradingSoftwareDetailsForm.getOpeSystem());
		vendorTradingSoftwareDetails.setOrderType(vendorTradingSoftwareDetailsForm.getOrderType());
		vendorTradingSoftwareDetails.setPlatformCCY(vendorTradingSoftwareDetailsForm.getPlatformCCY());
		vendorTradingSoftwareDetails.setPlatformCost(vendorTradingSoftwareDetailsForm.getPlatformCost());
		vendorTradingSoftwareDetails.setPlatformtype(vendorTradingSoftwareDetailsForm.getPlatformtype());
		vendorTradingSoftwareDetails.setPriceAlerts(vendorTradingSoftwareDetailsForm.getPriceAlerts());
		vendorTradingSoftwareDetails.setStreNews(vendorTradingSoftwareDetailsForm.getStreNews());
		vendorTradingSoftwareDetails.setSuitability(vendorTradingSoftwareDetailsForm.getSuitability());
		vendorTradingSoftwareDetails.setTradableMarkets(vendorTradingSoftwareDetailsForm.getTradableMarkets());
		vendorTradingSoftwareDetails.setTradableRegions(vendorTradingSoftwareDetailsForm.getTradableRegions());
		vendorTradingSoftwareDetails.setTradeExec(vendorTradingSoftwareDetailsForm.getTradeExec());
		vendorTradingSoftwareDetails.setTradeType(vendorTradingSoftwareDetailsForm.getTradeType());
		vendorTradingSoftwareDetails.setTradingCap(vendorTradingSoftwareDetailsForm.getTradingCap());
		vendorTradingSoftwareDetails.setOffering(vendorTradingSoftwareDetailsForm.getOffering());
		vendorTradingSoftwareDetails.setOfferingDesc(vendorTradingSoftwareDetailsForm.getOfferingDesc());
		vendorTradingSoftwareDetails.setWatchlist(vendorTradingSoftwareDetailsForm.getWatchlist());
		return vendorTradingSoftwareDetails;
	}
	public VendorTradingSoftwareDetailsForm insertDataToForm(VendorTradingSoftwareDetails vendorTradingSoftwareDetails) {
		VendorTradingSoftwareDetailsForm vendorTradingSoftwareDetailsForm = new VendorTradingSoftwareDetailsForm();
		vendorTradingSoftwareDetailsForm.setId(vendorTradingSoftwareDetails.getTradingSoftwareDetailsId().toString());
		vendorTradingSoftwareDetailsForm.setSolution(vendorTradingSoftwareDetails.getSolution().getName());
		vendorTradingSoftwareDetailsForm.setAssetClass(vendorTradingSoftwareDetails.getAssetClass().getDescription()+"-"+vendorTradingSoftwareDetails.getSecurityName());
		vendorTradingSoftwareDetailsForm.setAccessibility(vendorTradingSoftwareDetails.getAccessibility());
		vendorTradingSoftwareDetailsForm.setAddOns(vendorTradingSoftwareDetails.getAddOns());
		vendorTradingSoftwareDetailsForm.setAppDesc(vendorTradingSoftwareDetails.getAppDesc());
		vendorTradingSoftwareDetailsForm.setAppName(vendorTradingSoftwareDetails.getAppName());
		vendorTradingSoftwareDetailsForm.setChartsAvai(vendorTradingSoftwareDetails.getChartsAvai());
		vendorTradingSoftwareDetailsForm.setClientBase(vendorTradingSoftwareDetails.getClientBase());
		vendorTradingSoftwareDetailsForm.setCostType(vendorTradingSoftwareDetails.getCostType());
		vendorTradingSoftwareDetailsForm.setDarkVenues(vendorTradingSoftwareDetails.getDarkvenues());
		vendorTradingSoftwareDetailsForm.setExchangeFees(vendorTradingSoftwareDetails.getExchangeFees());
		vendorTradingSoftwareDetailsForm.setLaunchedYear(vendorTradingSoftwareDetails.getLaunchedYear());
		vendorTradingSoftwareDetailsForm.setOpeSystem(vendorTradingSoftwareDetails.getOpeSystem());
		vendorTradingSoftwareDetailsForm.setOrderType(vendorTradingSoftwareDetails.getOrderType());
		vendorTradingSoftwareDetailsForm.setPlatformCCY(vendorTradingSoftwareDetails.getPlatformCCY());
		vendorTradingSoftwareDetailsForm.setPlatformCost(vendorTradingSoftwareDetails.getPlatformCost());
		vendorTradingSoftwareDetailsForm.setPlatformtype(vendorTradingSoftwareDetails.getPlatformtype());
		vendorTradingSoftwareDetailsForm.setPriceAlerts(vendorTradingSoftwareDetails.getPriceAlerts());
		vendorTradingSoftwareDetailsForm.setStreNews(vendorTradingSoftwareDetails.getStreNews());
		vendorTradingSoftwareDetailsForm.setSuitability(vendorTradingSoftwareDetails.getSuitability());
		vendorTradingSoftwareDetailsForm.setTradableMarkets(vendorTradingSoftwareDetails.getTradableMarkets());
		vendorTradingSoftwareDetailsForm.setTradableRegions(vendorTradingSoftwareDetails.getTradableRegions());
		vendorTradingSoftwareDetailsForm.setTradeExec(vendorTradingSoftwareDetails.getTradeExec());
		vendorTradingSoftwareDetailsForm.setTradeType(vendorTradingSoftwareDetails.getTradeType());
		vendorTradingSoftwareDetailsForm.setTradingCap(vendorTradingSoftwareDetails.getTradingCap());
		vendorTradingSoftwareDetailsForm.setOffering(vendorTradingSoftwareDetails.getOffering());
		vendorTradingSoftwareDetailsForm.setOfferingDesc(vendorTradingSoftwareDetails.getOfferingDesc());
		vendorTradingSoftwareDetailsForm.setWatchlist(vendorTradingSoftwareDetails.getWatchlist());
		return vendorTradingSoftwareDetailsForm;
	}
	
	
	
	}
