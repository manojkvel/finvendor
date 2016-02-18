/**
 * 
 */
package com.finvendor.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author rayulu vemula
 *
 */
@Entity
@Table(name="security_types")
public class SecurityType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="security_type_id")
    @GeneratedValue
    private Integer security_type_id;
	
	@Column(name="name")
	private String name;
   
	@Column(name="asset_class_id")
    private Integer assetClassId;
	
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="securityType")
	private Set<AssetClassSecurityMap> assetClassSecurityMaps=new HashSet<AssetClassSecurityMap>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="securityType")
	private Set<VendorOffering> vendorOfferings=new HashSet<VendorOffering>();
	

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the assetClassSecurityMaps
	 */
	public Set<AssetClassSecurityMap> getAssetClassSecurityMaps() {
		return assetClassSecurityMaps;
	}

	/**
	 * @param assetClassSecurityMaps the assetClassSecurityMaps to set
	 */
	public void setAssetClassSecurityMaps(Set<AssetClassSecurityMap> assetClassSecurityMaps) {
		this.assetClassSecurityMaps = assetClassSecurityMaps;
	}

	/**
	 * @return the vendorOfferings
	 */
	public Set<VendorOffering> getVendorOfferings() {
		return vendorOfferings;
	}

	/**
	 * @param vendorOfferings the vendorOfferings to set
	 */
	public void setVendorOfferings(Set<VendorOffering> vendorOfferings) {
		this.vendorOfferings = vendorOfferings;
	}

	public Integer getAssetClassId() {
		return assetClassId;
	}

	public void setAssetClassId(Integer assetClassId) {
		this.assetClassId = assetClassId;
	}

}
