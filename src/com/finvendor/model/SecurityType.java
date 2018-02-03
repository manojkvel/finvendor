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

@Entity
@Table(name="security_types")
public class SecurityType implements Serializable{

	private static final long serialVersionUID = 160320160815L;

	@Id
    @Column(name="security_type_id")
    @GeneratedValue
    private Integer securityTypeId;
	
	@Column(name="name")
	private String name;
   
	@Column(name="asset_class_id")
    private Integer assetClassId;
		
	@OneToMany(fetch=FetchType.LAZY,mappedBy="securityType")
	private Set<AssetClassSecurityMap> assetClassSecurityMaps = new HashSet<AssetClassSecurityMap>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="securityType")
	private Set<VendorOffering> vendorOfferings = new HashSet<VendorOffering>();	

	public Integer getSecurityTypeId() {
		return securityTypeId;
	}

	public void setSecurityTypeId(Integer securityTypeId) {
		this.securityTypeId = securityTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<AssetClassSecurityMap> getAssetClassSecurityMaps() {
		return assetClassSecurityMaps;
	}

	public void setAssetClassSecurityMaps(Set<AssetClassSecurityMap> assetClassSecurityMaps) {
		this.assetClassSecurityMaps = assetClassSecurityMaps;
	}

	public Set<VendorOffering> getVendorOfferings() {
		return vendorOfferings;
	}

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
