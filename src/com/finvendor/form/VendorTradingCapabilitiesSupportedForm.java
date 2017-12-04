<<<<<<< HEAD
/**
 * 
 */
package com.finvendor.form;

import com.finvendor.model.VendorTradingCapabilitiesSupported;

public class VendorTradingCapabilitiesSupportedForm{
	private String id;
	private String solution;
	private String offering;
	private String tradeCoverageRegion;
	private String tradableMarkets;
	private String tradeCoverageCountry;
	private String tradingCapabilitiesType;
	private String tradeExecutionsType;
	private String algorithmicTradeType;
	private String darkpoolAccess;
	private String supportedDarkpoolVenues;
	private String isRecordExist;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getSupportedDarkpoolVenues() {
		return supportedDarkpoolVenues;
	}
	public void setSupportedDarkpoolVenues(String supportedDarkpoolVenues) {
		this.supportedDarkpoolVenues = supportedDarkpoolVenues;
	}
	public String getTradableMarkets() {
		return tradableMarkets;
	}
	public void setTradableMarkets(String tradableMarkets) {
		this.tradableMarkets = tradableMarkets;
	}
	public String getIsRecordExist() {
		return isRecordExist;
	}
	public void setIsRecordExist(String isRecordExist) {
		this.isRecordExist = isRecordExist;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getOffering() {
		return offering;
	}
	public void setOffering(String offering) {
		this.offering = offering;
	}
	public String getTradeCoverageRegion() {
		return tradeCoverageRegion;
	}
	public void setTradeCoverageRegion(String tradeCoverageRegion) {
		this.tradeCoverageRegion = tradeCoverageRegion;
	}
	public String getTradeCoverageCountry() {
		return tradeCoverageCountry;
	}
	public void setTradeCoverageCountry(String tradeCoverageCountry) {
		this.tradeCoverageCountry = tradeCoverageCountry;
	}
	public String getTradingCapabilitiesType() {
		return tradingCapabilitiesType;
	}
	public void setTradingCapabilitiesType(String tradingCapabilitiesType) {
		this.tradingCapabilitiesType = tradingCapabilitiesType;
	}
	public String getTradeExecutionsType() {
		return tradeExecutionsType;
	}
	public void setTradeExecutionsType(String tradeExecutionsType) {
		this.tradeExecutionsType = tradeExecutionsType;
	}
	public String getAlgorithmicTradeType() {
		return algorithmicTradeType;
	}
	public void setAlgorithmicTradeType(String algorithmicTradeType) {
		this.algorithmicTradeType = algorithmicTradeType;
	}
	public String getDarkpoolAccess() {
		return darkpoolAccess;
	}
	public void setDarkpoolAccess(String darkpoolAccess) {
		this.darkpoolAccess = darkpoolAccess;
	}
	public VendorTradingCapabilitiesSupported insertIntoModel(VendorTradingCapabilitiesSupportedForm vendorTradingCapabilitiesSupportedForm) {
		VendorTradingCapabilitiesSupported vendorTradingCapabilitiesSupported= new VendorTradingCapabilitiesSupported();
		vendorTradingCapabilitiesSupported.setAlgorithmicTradeType(vendorTradingCapabilitiesSupportedForm.getAlgorithmicTradeType());
		vendorTradingCapabilitiesSupported.setDarkpoolAccess(vendorTradingCapabilitiesSupportedForm.getDarkpoolAccess());
		vendorTradingCapabilitiesSupported.setTradeCoverageCountry(vendorTradingCapabilitiesSupportedForm.getTradeCoverageCountry());
		vendorTradingCapabilitiesSupported.setTradeCoverageRegion(vendorTradingCapabilitiesSupportedForm.getTradeCoverageRegion());
		vendorTradingCapabilitiesSupported.setTradableMarkets(vendorTradingCapabilitiesSupportedForm.getTradableMarkets());
		vendorTradingCapabilitiesSupported.setTradeExecutionsType(vendorTradingCapabilitiesSupportedForm.getTradeExecutionsType());
		vendorTradingCapabilitiesSupported.setTradingCapabilitiesType(vendorTradingCapabilitiesSupportedForm.getTradingCapabilitiesType());
		vendorTradingCapabilitiesSupported.setOffering(vendorTradingCapabilitiesSupportedForm.getOffering());
		vendorTradingCapabilitiesSupported.setSupportedDarkpoolVenues(vendorTradingCapabilitiesSupportedForm.getSupportedDarkpoolVenues());
		
		return vendorTradingCapabilitiesSupported;
	}
	
	public VendorTradingCapabilitiesSupportedForm insertDataToForm(VendorTradingCapabilitiesSupported vendorTradingCapabilitiesSupported) {
		VendorTradingCapabilitiesSupportedForm vendorTradingCapabilitiesSupportedForm= new VendorTradingCapabilitiesSupportedForm();
		vendorTradingCapabilitiesSupportedForm.setId(vendorTradingCapabilitiesSupported.getTradingCapabilitieId().toString());
		vendorTradingCapabilitiesSupportedForm.setAlgorithmicTradeType(vendorTradingCapabilitiesSupported.getAlgorithmicTradeType());
		vendorTradingCapabilitiesSupportedForm.setDarkpoolAccess(vendorTradingCapabilitiesSupported.getDarkpoolAccess());
		vendorTradingCapabilitiesSupportedForm.setTradeCoverageCountry(vendorTradingCapabilitiesSupported.getTradeCoverageCountry());
		vendorTradingCapabilitiesSupportedForm.setTradeCoverageRegion(vendorTradingCapabilitiesSupported.getTradeCoverageRegion());
		vendorTradingCapabilitiesSupportedForm.setTradableMarkets(vendorTradingCapabilitiesSupported.getTradableMarkets());
		vendorTradingCapabilitiesSupportedForm.setTradeExecutionsType(vendorTradingCapabilitiesSupported.getTradeExecutionsType());
		vendorTradingCapabilitiesSupportedForm.setTradingCapabilitiesType(vendorTradingCapabilitiesSupported.getTradingCapabilitiesType());
		vendorTradingCapabilitiesSupportedForm.setOffering(vendorTradingCapabilitiesSupported.getOffering());
		vendorTradingCapabilitiesSupportedForm.setSolution(vendorTradingCapabilitiesSupported.getSolution().getName());
		vendorTradingCapabilitiesSupportedForm.setSupportedDarkpoolVenues(vendorTradingCapabilitiesSupported.getSupportedDarkpoolVenues());
		
		return vendorTradingCapabilitiesSupportedForm;
	}
	public void setRecordExist(String isRecordExist) {
		this.isRecordExist = isRecordExist;
		
	}
}
=======
/**
 * 
 */
package com.finvendor.form;

import com.finvendor.model.VendorTradingCapabilitiesSupported;

public class VendorTradingCapabilitiesSupportedForm{
	private String id;
	private String solution;
	private String offering;
	private String tradeCoverageRegion;
	private String tradableMarkets;
	private String tradeCoverageCountry;
	private String tradingCapabilitiesType;
	private String tradeExecutionsType;
	private String algorithmicTradeType;
	private String darkpoolAccess;
	private String supportedDarkpoolVenues;
	private String isRecordExist;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getSupportedDarkpoolVenues() {
		return supportedDarkpoolVenues;
	}
	public void setSupportedDarkpoolVenues(String supportedDarkpoolVenues) {
		this.supportedDarkpoolVenues = supportedDarkpoolVenues;
	}
	public String getTradableMarkets() {
		return tradableMarkets;
	}
	public void setTradableMarkets(String tradableMarkets) {
		this.tradableMarkets = tradableMarkets;
	}
	public String getIsRecordExist() {
		return isRecordExist;
	}
	public void setIsRecordExist(String isRecordExist) {
		this.isRecordExist = isRecordExist;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getOffering() {
		return offering;
	}
	public void setOffering(String offering) {
		this.offering = offering;
	}
	public String getTradeCoverageRegion() {
		return tradeCoverageRegion;
	}
	public void setTradeCoverageRegion(String tradeCoverageRegion) {
		this.tradeCoverageRegion = tradeCoverageRegion;
	}
	public String getTradeCoverageCountry() {
		return tradeCoverageCountry;
	}
	public void setTradeCoverageCountry(String tradeCoverageCountry) {
		this.tradeCoverageCountry = tradeCoverageCountry;
	}
	public String getTradingCapabilitiesType() {
		return tradingCapabilitiesType;
	}
	public void setTradingCapabilitiesType(String tradingCapabilitiesType) {
		this.tradingCapabilitiesType = tradingCapabilitiesType;
	}
	public String getTradeExecutionsType() {
		return tradeExecutionsType;
	}
	public void setTradeExecutionsType(String tradeExecutionsType) {
		this.tradeExecutionsType = tradeExecutionsType;
	}
	public String getAlgorithmicTradeType() {
		return algorithmicTradeType;
	}
	public void setAlgorithmicTradeType(String algorithmicTradeType) {
		this.algorithmicTradeType = algorithmicTradeType;
	}
	public String getDarkpoolAccess() {
		return darkpoolAccess;
	}
	public void setDarkpoolAccess(String darkpoolAccess) {
		this.darkpoolAccess = darkpoolAccess;
	}
	public VendorTradingCapabilitiesSupported insertIntoModel(VendorTradingCapabilitiesSupportedForm vendorTradingCapabilitiesSupportedForm) {
		VendorTradingCapabilitiesSupported vendorTradingCapabilitiesSupported= new VendorTradingCapabilitiesSupported();
		vendorTradingCapabilitiesSupported.setAlgorithmicTradeType(vendorTradingCapabilitiesSupportedForm.getAlgorithmicTradeType());
		vendorTradingCapabilitiesSupported.setDarkpoolAccess(vendorTradingCapabilitiesSupportedForm.getDarkpoolAccess());
		vendorTradingCapabilitiesSupported.setTradeCoverageCountry(vendorTradingCapabilitiesSupportedForm.getTradeCoverageCountry());
		vendorTradingCapabilitiesSupported.setTradeCoverageRegion(vendorTradingCapabilitiesSupportedForm.getTradeCoverageRegion());
		vendorTradingCapabilitiesSupported.setTradableMarkets(vendorTradingCapabilitiesSupportedForm.getTradableMarkets());
		vendorTradingCapabilitiesSupported.setTradeExecutionsType(vendorTradingCapabilitiesSupportedForm.getTradeExecutionsType());
		vendorTradingCapabilitiesSupported.setTradingCapabilitiesType(vendorTradingCapabilitiesSupportedForm.getTradingCapabilitiesType());
		vendorTradingCapabilitiesSupported.setOffering(vendorTradingCapabilitiesSupportedForm.getOffering());
		vendorTradingCapabilitiesSupported.setSupportedDarkpoolVenues(vendorTradingCapabilitiesSupportedForm.getSupportedDarkpoolVenues());
		
		return vendorTradingCapabilitiesSupported;
	}
	
	public VendorTradingCapabilitiesSupportedForm insertDataToForm(VendorTradingCapabilitiesSupported vendorTradingCapabilitiesSupported) {
		VendorTradingCapabilitiesSupportedForm vendorTradingCapabilitiesSupportedForm= new VendorTradingCapabilitiesSupportedForm();
		vendorTradingCapabilitiesSupportedForm.setId(vendorTradingCapabilitiesSupported.getTradingCapabilitieId().toString());
		vendorTradingCapabilitiesSupportedForm.setAlgorithmicTradeType(vendorTradingCapabilitiesSupported.getAlgorithmicTradeType());
		vendorTradingCapabilitiesSupportedForm.setDarkpoolAccess(vendorTradingCapabilitiesSupported.getDarkpoolAccess());
		vendorTradingCapabilitiesSupportedForm.setTradeCoverageCountry(vendorTradingCapabilitiesSupported.getTradeCoverageCountry());
		vendorTradingCapabilitiesSupportedForm.setTradeCoverageRegion(vendorTradingCapabilitiesSupported.getTradeCoverageRegion());
		vendorTradingCapabilitiesSupportedForm.setTradableMarkets(vendorTradingCapabilitiesSupported.getTradableMarkets());
		vendorTradingCapabilitiesSupportedForm.setTradeExecutionsType(vendorTradingCapabilitiesSupported.getTradeExecutionsType());
		vendorTradingCapabilitiesSupportedForm.setTradingCapabilitiesType(vendorTradingCapabilitiesSupported.getTradingCapabilitiesType());
		vendorTradingCapabilitiesSupportedForm.setOffering(vendorTradingCapabilitiesSupported.getOffering());
		vendorTradingCapabilitiesSupportedForm.setSolution(vendorTradingCapabilitiesSupported.getSolution().getName());
		vendorTradingCapabilitiesSupportedForm.setSupportedDarkpoolVenues(vendorTradingCapabilitiesSupported.getSupportedDarkpoolVenues());
		
		return vendorTradingCapabilitiesSupportedForm;
	}
	public void setRecordExist(String isRecordExist) {
		this.isRecordExist = isRecordExist;
		
	}
}
>>>>>>> origin/master
