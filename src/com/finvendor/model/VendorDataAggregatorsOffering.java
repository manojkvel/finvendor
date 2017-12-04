<<<<<<< HEAD
package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ven_data_aggr_offering")
public class VendorDataAggregatorsOffering 
	implements Serializable {

	private static final long serialVersionUID = 16102016095901L;
	
	@Id
    @Column(name="product_id")
	private String productId; 
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="product_description")
	private String productDescription;
	
	@Column(name="launched_year")
	private String launchedYear;
	
	@Column(name="security_types")
	private String securityTypes;
	
	@ManyToOne(targetEntity=Vendor.class)
	@JoinColumn(name="vendor_id", nullable=false)
	private Vendor vendor;
	
	@ManyToOne(targetEntity=AssetClass.class)
	@JoinColumn(name="asset_class_id", nullable=false)
	private AssetClass assetClass;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="product_id")
	private VendorDataAggregatorsOfferingCoverage offeringCoverge;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="product_id")
	private VendorDataAggregatorsOfferingDistribution offeringDistribution;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getLaunchedYear() {
		return launchedYear;
	}

	public void setLaunchedYear(String launchedYear) {
		this.launchedYear = launchedYear;
	}

	public String getSecurityTypes() {
		return securityTypes;
	}

	public void setSecurityTypes(String securityTypes) {
		this.securityTypes = securityTypes;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public AssetClass getAssetClass() {
		return assetClass;
	}

	public void setAssetClass(AssetClass assetClass) {
		this.assetClass = assetClass;
	}

	public VendorDataAggregatorsOfferingCoverage getOfferingCoverge() {
		return offeringCoverge;
	}

	public void setOfferingCoverge(VendorDataAggregatorsOfferingCoverage offeringCoverge) {
		this.offeringCoverge = offeringCoverge;
	}

	public VendorDataAggregatorsOfferingDistribution getOfferingDistribution() {
		return offeringDistribution;
	}

	public void setOfferingDistribution(VendorDataAggregatorsOfferingDistribution offeringDistribution) {
		this.offeringDistribution = offeringDistribution;
	}
	
}
=======
package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ven_data_aggr_offering")
public class VendorDataAggregatorsOffering 
	implements Serializable {

	private static final long serialVersionUID = 16102016095901L;
	
	@Id
    @Column(name="product_id")
	private String productId; 
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="product_description")
	private String productDescription;
	
	@Column(name="launched_year")
	private String launchedYear;
	
	@Column(name="security_types")
	private String securityTypes;
	
	@ManyToOne(targetEntity=Vendor.class)
	@JoinColumn(name="vendor_id", nullable=false)
	private Vendor vendor;
	
	@ManyToOne(targetEntity=AssetClass.class)
	@JoinColumn(name="asset_class_id", nullable=false)
	private AssetClass assetClass;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="product_id")
	private VendorDataAggregatorsOfferingCoverage offeringCoverge;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="product_id")
	private VendorDataAggregatorsOfferingDistribution offeringDistribution;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getLaunchedYear() {
		return launchedYear;
	}

	public void setLaunchedYear(String launchedYear) {
		this.launchedYear = launchedYear;
	}

	public String getSecurityTypes() {
		return securityTypes;
	}

	public void setSecurityTypes(String securityTypes) {
		this.securityTypes = securityTypes;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public AssetClass getAssetClass() {
		return assetClass;
	}

	public void setAssetClass(AssetClass assetClass) {
		this.assetClass = assetClass;
	}

	public VendorDataAggregatorsOfferingCoverage getOfferingCoverge() {
		return offeringCoverge;
	}

	public void setOfferingCoverge(VendorDataAggregatorsOfferingCoverage offeringCoverge) {
		this.offeringCoverge = offeringCoverge;
	}

	public VendorDataAggregatorsOfferingDistribution getOfferingDistribution() {
		return offeringDistribution;
	}

	public void setOfferingDistribution(VendorDataAggregatorsOfferingDistribution offeringDistribution) {
		this.offeringDistribution = offeringDistribution;
	}
	
}
>>>>>>> origin/master
