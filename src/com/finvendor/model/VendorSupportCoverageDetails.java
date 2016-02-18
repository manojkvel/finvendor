/**
 * 
 */
package com.finvendor.model;

import java.io.Serializable;

/**
 * @author rayulu vemula
 *
 */
public class VendorSupportCoverageDetails implements Serializable{

	 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String assetClass;

	private String securityType;
	
	private String supportRegion;
	
	private String supportCountry;
	
	private String supportExchange;

	private String dataattribute;

	
	
	/**
	 * @return the assetClass
	 */
	public String getAssetClass() {
		return assetClass;
	}

	/**
	 * @param assetClass the assetClass to set
	 */
	public void setAssetClass(String assetClass) {
		this.assetClass = assetClass;
	}

	/**
	 * @return the securityType
	 */
	public String getSecurityType() {
		return securityType;
	}

	/**
	 * @param securityType the securityType to set
	 */
	public void setSecurityType(String securityType) {
		this.securityType = securityType;
	}

	/**
	 * @return the supportRegion
	 */
	public String getSupportRegion() {
		return supportRegion;
	}

	/**
	 * @param supportRegion the supportRegion to set
	 */
	public void setSupportRegion(String supportRegion) {
		this.supportRegion = supportRegion;
	}

	/**
	 * @return the supportCountry
	 */
	public String getSupportCountry() {
		return supportCountry;
	}

	/**
	 * @param supportCountry the supportCountry to set
	 */
	public void setSupportCountry(String supportCountry) {
		this.supportCountry = supportCountry;
	}

	/**
	 * @return the supportExchange
	 */
	public String getSupportExchange() {
		return supportExchange;
	}

	/**
	 * @param supportExchange the supportExchange to set
	 */
	public void setSupportExchange(String supportExchange) {
		this.supportExchange = supportExchange;
	}

	/**
	 * @return the dataattribute
	 */
	public String getDataattribute() {
		return dataattribute;
	}

	/**
	 * @param dataattribute the dataattribute to set
	 */
	public void setDataattribute(String dataattribute) {
		this.dataattribute = dataattribute;
	}
}
