package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ven_trad_app_soft_dtls")
public class VendorTradingApplicationsSoftwareDetails
	implements Serializable	{
	
	private static final long serialVersionUID = 19122016205201L;
	
	@Id
    @Column(name="product_id")
	private String productId; 
	
	@Column(name="accessbility")
	private String accessbility;
	
	@Column(name="suitability")
	private String suitability;
	
	@Column(name="cost_type")
	private String costType;
	
	@Column(name="platform_ccy")
	private String platformCcy;
	
	@Column(name="platform_cost_pm")
	private float platformCostPm;
	
	@Column(name="platform_cost_py")
	private float platformCostPy;
	
	@Column(name="order_type")
	private String orderType;
	
	@Column(name="trade_type")
	private String tradeType;
	
	@Column(name="soft_specification")
	private String softSpecification;
	
	@Column(name="add_ons")
	private String addOns;
	
	@Column(name="operating_system")
	private String operatingSystem;
	
	@Column(name="client_base")
	private String clientBase;
	
	@Column(name="price_alerts")
	private String priceAlerts;
	
	@Column(name="watchlist")
	private String watchlist;
	
	@Column(name="streaming_news")
	private String streamingNews;
	
	@Column(name="trade_using_charts")
	private String tradeUsingCharts;

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

	public String getPlatformCcy() {
		return platformCcy;
	}

	public void setPlatformCcy(String platformCcy) {
		this.platformCcy = platformCcy;
	}

	public float getPlatformCostPm() {
		return platformCostPm;
	}

	public void setPlatformCostPm(float platformCostPm) {
		this.platformCostPm = platformCostPm;
	}

	public float getPlatformCostPy() {
		return platformCostPy;
	}

	public void setPlatformCostPy(float platformCostPy) {
		this.platformCostPy = platformCostPy;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getSoftSpecification() {
		return softSpecification;
	}

	public void setSoftSpecification(String softSpecification) {
		this.softSpecification = softSpecification;
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

	public String getClientBase() {
		return clientBase;
	}

	public void setClientBase(String clientBase) {
		this.clientBase = clientBase;
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

	public String getStreamingNews() {
		return streamingNews;
	}

	public void setStreamingNews(String streamingNews) {
		this.streamingNews = streamingNews;
	}

	public String getTradeUsingCharts() {
		return tradeUsingCharts;
	}

	public void setTradeUsingCharts(String tradeUsingCharts) {
		this.tradeUsingCharts = tradeUsingCharts;
	}
	
}
