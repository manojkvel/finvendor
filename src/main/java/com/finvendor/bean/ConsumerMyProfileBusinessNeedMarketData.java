//package com.finvendor.bean;
//
//import java.io.Serializable;
//
//import org.codehaus.jackson.annotate.JsonProperty;
//
//public class ConsumerMyProfileBusinessNeedMarketData
//		implements Serializable {
//
//	private static final long serialVersionUID = 201603181115L;
//	private String assetClass;
//	private String securityType;
//	private String dataCoverageRegion;
//	private String dataCoverageCountry;
//	private String dataCoverageExchange;
//	private String dataAttribute;
//
//	@JsonProperty("Asset Class")
//	public String getAssetClass() {
//		return assetClass;
//	}
//	public void setAssetClass(String assetClass) {
//		this.assetClass = assetClass;
//	}
//	@JsonProperty("Security type")
//	public String getSecurityType() {
//		return securityType;
//	}
//	public void setSecurityType(String securityType) {
//		this.securityType = securityType;
//	}
//	@JsonProperty("Region")
//	public String getDataCoverageRegion() {
//		return dataCoverageRegion;
//	}
//	public void setDataCoverageRegion(String dataCoverageRegion) {
//		this.dataCoverageRegion = dataCoverageRegion;
//	}
//	@JsonProperty("Country")
//	public String getDataCoverageCountry() {
//		return dataCoverageCountry;
//	}
//	public void setDataCoverageCountry(String dataCoverageCountry) {
//		this.dataCoverageCountry = dataCoverageCountry;
//	}
//	@JsonProperty("Exchange")
//	public String getDataCoverageExchange() {
//		return dataCoverageExchange;
//	}
//	public void setDataCoverageExchange(String dataCoverageExchange) {
//		this.dataCoverageExchange = dataCoverageExchange;
//	}
//	@JsonProperty("Data Attribute")
//	public String getDataAttribute() {
//		return dataAttribute;
//	}
//	public void setDataAttribute(String dataAttribute) {
//		this.dataAttribute = dataAttribute;
//	}
//
//	@Override
//	public int hashCode() {
//		return assetClass.hashCode() + securityType.hashCode() + dataCoverageRegion.hashCode()
//				+ dataCoverageCountry.hashCode() + dataCoverageExchange.hashCode()
//				+ dataAttribute.hashCode();
//	}
//
//	@Override
//	public boolean equals(Object other) {
//
//		if(other == null || !(other instanceof ConsumerMyProfileBusinessNeedMarketData)) {
//			return false;
//		}
//
//		ConsumerMyProfileBusinessNeedMarketData second = (ConsumerMyProfileBusinessNeedMarketData)other;
//		return assetClass.equals(second.getAssetClass()) && securityType.equals(second.getSecurityType())
//				&& dataCoverageRegion.equals(second.getDataCoverageRegion())
//				&& dataCoverageCountry.equals(second.getDataCoverageCountry())
//				&& dataCoverageExchange.equals(second.getDataCoverageExchange())
//				&& dataAttribute.equals(second.getDataAttribute());
//	}
//}
