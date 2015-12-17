/**
 * 
 */
package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author rayulu vemula
 *
 */
@Entity
@Table(name="vendor_asset_class_search_info")
public class AssetClassDataDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="username")
	private String username;
	
	@Column(name="company")
	private String company;
	
    @Column(name="vendor_id")
    private String vendor_id;
	
	@Column(name="asset_class_id")
	private Integer asset_class_id;
	
	@Column(name="security_type_id")
	private Integer security_type_id;

	@Column(name="assetclass_description")
	private String assetclass_description;

	@Column(name="security_type_name")
	private String security_type_name;
	
	@Column(name="regionofincorp")
	private Integer regionofincorp;
	
	@Column(name="countryofincorp")
	private Integer countryofincorp;
	
	@Column(name="region_id")
	private Integer region_id;
	
	@Column(name="country_id")
	private Integer country_id;
	
	@Column(name="exchange_id")
	private Integer exchange_id;
	
	@Column(name="cost_id")
	private Integer cost_id;
	
	@Column(name="cost_range")
	private String cost_range;
	
	@Column(name="cost_name")
	private String cost_name;

	@Column(name="support_id")
	private Integer support_id;
	
	@Column(name="support_name")
	private String support_name;
	
	@Column(name="award_id")
	private Integer award_id;
	
	@Column(name="awar_name")
	private String awar_name;
	
	@Column(name="distribution_mode_id")
	private Integer distribution_mode_id;
	
	@Column(name="distribution_mode_name")
	private String distribution_mode_name;
	
	
	
	
	/**
	 * Instantiates a new ad asset class data details.
	 *
	 */
	public AssetClassDataDetails(String username,
			String company, String vendor_id, Integer asset_class_id, Integer security_type_id,String assetclass_description,String security_type_name,
			Integer regionofincorp,Integer countryofincorp,Integer region_id,
			Integer country_id,Integer exchange_id,Integer cost_id, String cost_range, String cost_name, 
			Integer support_id,String support_name, Integer award_id,
			String awar_name,Integer distribution_mode_id, String distribution_mode_name) {
		super();
		this.username = username;
		this.company = company;
		this.vendor_id = vendor_id;
		this.asset_class_id = asset_class_id;
		this.security_type_id = security_type_id;
		this.assetclass_description = assetclass_description;
		this.security_type_name = security_type_name;
		this.regionofincorp = regionofincorp;
		this.countryofincorp = countryofincorp;
		this.region_id = region_id;
		this.country_id = country_id;
		this.exchange_id = exchange_id;
		this.cost_id = cost_id;
		this.cost_range = cost_range;
		this.cost_name = cost_name;
		this.support_id = support_id;
		this.support_name = support_name;
		this.award_id = award_id;
		this.awar_name = awar_name;
		this.distribution_mode_id = distribution_mode_id;
		this.distribution_mode_name = distribution_mode_name;
	}

	public boolean equals(Object assetClassDataDetails) {
	    if(assetClassDataDetails instanceof AssetClassDataDetails)
	    {
	    	AssetClassDataDetails assetClassDataDetailsInfo = (AssetClassDataDetails) assetClassDataDetails;
	        if(this.username == assetClassDataDetailsInfo.username 
	        		&& this.company== assetClassDataDetailsInfo.company 
	        		&& this.vendor_id == assetClassDataDetailsInfo.vendor_id
	        		&& this.asset_class_id == assetClassDataDetailsInfo.asset_class_id
	        		&& this.security_type_id == assetClassDataDetailsInfo.security_type_id
	        		&& this.assetclass_description == assetClassDataDetailsInfo.assetclass_description
	        		&& this.security_type_name == assetClassDataDetailsInfo.security_type_name
	        		&& this.regionofincorp == assetClassDataDetailsInfo.regionofincorp
	        		&& this.countryofincorp == assetClassDataDetailsInfo.countryofincorp
	        		&& this.region_id == assetClassDataDetailsInfo.region_id
	        		&& this.country_id == assetClassDataDetailsInfo.country_id
	        		&& this.exchange_id == assetClassDataDetailsInfo.exchange_id
	        		&& this.cost_id == assetClassDataDetailsInfo.cost_id
	        		&& this.cost_range == assetClassDataDetailsInfo.cost_range
	        		&& this.cost_name == assetClassDataDetailsInfo.cost_name
	        	    && this.support_id == assetClassDataDetailsInfo.support_id
	        	    && this.support_name == assetClassDataDetailsInfo.support_name
	        	    && this.award_id == assetClassDataDetailsInfo.award_id
	        	    && this.awar_name == assetClassDataDetailsInfo.awar_name
	        		&& this.distribution_mode_id == assetClassDataDetailsInfo.distribution_mode_id
	        		&& this.distribution_mode_name == assetClassDataDetailsInfo.distribution_mode_name)
	            return true;
	    }
	    return false;

	}
	
	public int hashCode() {
	    return (this.username.hashCode() + 
	    		this.company.hashCode() + 
	    		this.vendor_id.hashCode() + 
	    		this.asset_class_id.hashCode() +
	    		this.security_type_id.hashCode() +
	    		this.assetclass_description.hashCode() +
	    		this.security_type_name.hashCode() +
	    		this.regionofincorp.hashCode() +
	    		this.countryofincorp.hashCode() +
	    		this.region_id.hashCode() +
	    		this.country_id.hashCode() +
	    		this.exchange_id.hashCode() +
	    		this.cost_id.hashCode() +
	    		this.cost_range.hashCode() +
	    		this.cost_name.hashCode() +
	    		this.support_id.hashCode() +
	    		this.support_name.hashCode() +
	    		this.award_id.hashCode() +
	    		this.awar_name.hashCode() +
	    		this.distribution_mode_id.hashCode() +
	    		this.distribution_mode_name.hashCode());        
	}
	
	/**
	 * @return the vendor_id
	 */
	public String getVendor_id() {
		return vendor_id;
	}

	/**
	 * @param vendor_id the vendor_id to set
	 */
	public void setVendor_id(String vendor_id) {
		this.vendor_id = vendor_id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the asset_class_id
	 */
	public Integer getAsset_class_id() {
		return asset_class_id;
	}

	/**
	 * @param asset_class_id the asset_class_id to set
	 */
	public void setAsset_class_id(Integer asset_class_id) {
		this.asset_class_id = asset_class_id;
	}
	 
	public String getAssetclass_description() {
		return assetclass_description;
	}

	public void setAssetclass_description(String assetclass_description) {
		this.assetclass_description = assetclass_description;
	}
	
	/**
	 * @return the region_id
	 */
	public Integer getRegion_id() {
		return region_id;
	}

	/**
	 * @param region_id the region_id to set
	 */
	public void setRegion_id(Integer region_id) {
		this.region_id = region_id;
	}

	/**
	 * @return the exchange_id
	 */
	public Integer getExchange_id() {
		return exchange_id;
	}

	/**
	 * @param exchange_id the exchange_id to set
	 */
	public void setExchange_id(Integer exchange_id) {
		this.exchange_id = exchange_id;
	}

	/**
	 * @return the country_id
	 */
	public Integer getCountry_id() {
		return country_id;
	}

	/**
	 * @param country_id the country_id to set
	 */
	public void setCountry_id(Integer country_id) {
		this.country_id = country_id;
	}


	/**
	 * @return the security_type_id
	 */
	public Integer getSecurity_type_id() {
		return security_type_id;
	}

	/**
	 * @param security_type_id the security_type_id to set
	 */
	public void setSecurity_type_id(Integer security_type_id) {
		this.security_type_id = security_type_id;
	}

	/**
	 * @return the cost_id
	 */
	public Integer getCost_id() {
		return cost_id;
	}

	/**
	 * @param cost_id the cost_id to set
	 */
	public void setCost_id(Integer cost_id) {
		this.cost_id = cost_id;
	}

	
	/**
	 * @return the cost_range
	 */
	public String getCost_range() {
		return cost_range;
	}

	/**
	 * @param cost_range the cost_range to set
	 */
	public void setCost_range(String cost_range) {
		this.cost_range = cost_range;
	}

	/**
	 * @return the distribution_mode_id
	 */
	public Integer getDistribution_mode_id() {
		return distribution_mode_id;
	}

	/**
	 * @param distribution_mode_id the distribution_mode_id to set
	 */
	public void setDistribution_mode_id(Integer distribution_mode_id) {
		this.distribution_mode_id = distribution_mode_id;
	}

	 

	/**
	 * @return the support_id
	 */
	public Integer getSupport_id() {
		return support_id;
	}

	/**
	 * @param support_id the support_id to set
	 */
	public void setSupport_id(Integer support_id) {
		this.support_id = support_id;
	}

	/**
	 * @return the award_id
	 */
	public Integer getAward_id() {
		return award_id;
	}

	/**
	 * @param award_id the award_id to set
	 */
	public void setAward_id(Integer award_id) {
		this.award_id = award_id;
	}

	/**
	 * @return the security_type_name
	 */
	public String getSecurity_type_name() {
		return security_type_name;
	}

	/**
	 * @param security_type_name the security_type_name to set
	 */
	public void setSecurity_type_name(String security_type_name) {
		this.security_type_name = security_type_name;
	}

	/**
	 * @return the regionofincorp
	 */
	public Integer getRegionofincorp() {
		return regionofincorp;
	}

	/**
	 * @param regionofincorp the regionofincorp to set
	 */
	public void setRegionofincorp(Integer regionofincorp) {
		this.regionofincorp = regionofincorp;
	}

	/**
	 * @return the countryofincorp
	 */
	public Integer getCountryofincorp() {
		return countryofincorp;
	}

	/**
	 * @param countryofincorp the countryofincorp to set
	 */
	public void setCountryofincorp(Integer countryofincorp) {
		this.countryofincorp = countryofincorp;
	}

	/**
	 * @return the cost_name
	 */
	public String getCost_name() {
		return cost_name;
	}

	/**
	 * @param cost_name the cost_name to set
	 */
	public void setCost_name(String cost_name) {
		this.cost_name = cost_name;
	}

	/**
	 * @return the support_name
	 */
	public String getSupport_name() {
		return support_name;
	}

	/**
	 * @param support_name the support_name to set
	 */
	public void setSupport_name(String support_name) {
		this.support_name = support_name;
	}


	/**
	 * @return the distribution_mode_name
	 */
	public String getDistribution_mode_name() {
		return distribution_mode_name;
	}

	/**
	 * @param distribution_mode_name the distribution_mode_name to set
	 */
	public void setDistribution_mode_name(String distribution_mode_name) {
		this.distribution_mode_name = distribution_mode_name;
	}

	/**
	 * @return the awar_name
	 */
	public String getAwar_name() {
		return awar_name;
	}

	/**
	 * @param awar_name the awar_name to set
	 */
	public void setAwar_name(String awar_name) {
		this.awar_name = awar_name;
	}

	 
	/*@Override
	public int compare(AssetClassDataDetails assetClassDataDetails, AssetClassDataDetails assetClassDataDetailsinfo) {
		AssetClassDataDetails assetClassData = ((AssetClassDataDetails) assetClassDataDetails);
		AssetClassDataDetails assetClassDatainfo = ((AssetClassDataDetails) assetClassDataDetailsinfo);
		return (assetClassData.hashCode() < assetClassDatainfo.hashCode() ? -1
				: (assetClassData.hashCode() == assetClassDatainfo.hashCode() ? 0 : 1));
	}*/
	
	
}
