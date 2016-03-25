package com.finvendor.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="asset_class")
public class AssetClass implements Serializable{

	private static final long serialVersionUID = 201603062007L;
	
	@Id
    @Column(name="asset_class_id")
    @GeneratedValue
    private Integer asset_class_id;
	
	@Column(name="description")
	private String description;
   
	@Column(name="asset_class_cd")
	private String asset_class_cd;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="assetClass")
	private Set<AssetClassSecurityMap> assetClassSecurityMaps=new HashSet<AssetClassSecurityMap>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="assetClass")
	private Set<VendorOffering> vendorOfferings=new HashSet<VendorOffering>();
	
 
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the asset_class_cd
	 */
	public String getAsset_class_cd() {
		return asset_class_cd;
	}
	

	/**
	 * @param asset_class_cd the asset_class_cd to set
	 */
	public void setAsset_class_cd(String asset_class_cd) {
		this.asset_class_cd = asset_class_cd;
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

	
}
