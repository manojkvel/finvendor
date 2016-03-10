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
@Table(name="vendor_TradingCapabilitieSupported")
public class VendorTradingCapabilitiesSupported implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="TradingCapabilitie_id")
	@GeneratedValue
    private Integer TradingCapabilitieId;
	
	
	@ManyToOne(targetEntity=Vendor.class,fetch=FetchType.LAZY)
	@JoinColumn(name="vendor_id", nullable=false)
	private Vendor vendor;
	
	@ManyToOne(targetEntity=Solutions.class,fetch=FetchType.EAGER)
	@JoinColumn(name="solution_id", nullable=false)
	private Solutions solution;
	
	@Column(name="Offering")
	private String offering;
	@Column(name="TradeCoverageRegion")
	private String tradeCoverageRegion;
	@Column(name="TradeCoverageCountry")
	private String tradeCoverageCountry;
	@Column(name="TradingCapabilitiesType")
	private String tradingCapabilitiesType;
	@Column(name="TradeExecutionsType")
	private String tradeExecutionsType;
	@Column(name="AlgorithmicTradeType")
	private String algorithmicTradeType;
	@Column(name="DarkpoolAccess")
	private String darkpoolAccess;
	
	@Column(name="SupportedDarkpoolVenues")
	private String supportedDarkpoolVenues;
	
	@Column(name="TradableMarkets")
	private String tradableMarkets;
	
	
	
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
	public String getOffering() {
		return offering;
	}
	public void setOffering(String offering) {
		this.offering = offering;
	}
	public Integer getTradingCapabilitieId() {
		return TradingCapabilitieId;
	}
	public void setTradingCapabilitieId(Integer tradingCapabilitieId) {
		TradingCapabilitieId = tradingCapabilitieId;
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
	
		
	
	
	}
