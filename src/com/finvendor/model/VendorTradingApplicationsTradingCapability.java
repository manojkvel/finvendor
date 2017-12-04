package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ven_trad_app_trad_capab")
public class VendorTradingApplicationsTradingCapability
	implements Serializable	{
	
	private static final long serialVersionUID = 20122016190001L;
	
	@Id
    @Column(name="product_id")
	private String productId;
	
	@Column(name="trad_region")
	private String tradRegion;
	
	@Column(name="trad_country")
	private String tradCountry;
	
	@Column(name="trad_exchange")
	private String tradExchange;
	
	@Column(name="trad_capab_type")
	private String tradCapabType;
	
	@Column(name="trad_exec_type")
	private String tradExecType;
	
	@Column(name="algo_trade_type")
	private String algoTradeType;
	
	@Column(name="darkpool_access")
	private String darkpoolAccess;
	
	@Column(name="darkpool_venues")
	private String darkpoolVenues;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getTradRegion() {
		return tradRegion;
	}

	public void setTradRegion(String tradRegion) {
		this.tradRegion = tradRegion;
	}

	public String getTradCountry() {
		return tradCountry;
	}

	public void setTradCountry(String tradCountry) {
		this.tradCountry = tradCountry;
	}

	public String getTradExchange() {
		return tradExchange;
	}

	public void setTradExchange(String tradExchange) {
		this.tradExchange = tradExchange;
	}

	public String getTradCapabType() {
		return tradCapabType;
	}

	public void setTradCapabType(String tradCapabType) {
		this.tradCapabType = tradCapabType;
	}

	public String getTradExecType() {
		return tradExecType;
	}

	public void setTradExecType(String tradExecType) {
		this.tradExecType = tradExecType;
	}

	public String getAlgoTradeType() {
		return algoTradeType;
	}

	public void setAlgoTradeType(String algoTradeType) {
		this.algoTradeType = algoTradeType;
	}

	public String getDarkpoolAccess() {
		return darkpoolAccess;
	}

	public void setDarkpoolAccess(String darkpoolAccess) {
		this.darkpoolAccess = darkpoolAccess;
	}

	public String getDarkpoolVenues() {
		return darkpoolVenues;
	}

	public void setDarkpoolVenues(String darkpoolVenues) {
		this.darkpoolVenues = darkpoolVenues;
	}
	
}
