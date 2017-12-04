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
@Table(name="ven_trad_app_offering")
public class VendorTradingApplicationsOffering 
	implements Serializable {
	
	private static final long serialVersionUID = 19122016205001L;
	
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
	private VendorTradingApplicationsSoftwareDetails softwareDetails;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="product_id")
	private VendorTradingApplicationsTradingCapability tradingCapability;

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

	public VendorTradingApplicationsSoftwareDetails getSoftwareDetails() {
		return softwareDetails;
	}

	public void setSoftwareDetails(VendorTradingApplicationsSoftwareDetails softwareDetails) {
		this.softwareDetails = softwareDetails;
	}

	public VendorTradingApplicationsTradingCapability getTradingCapability() {
		return tradingCapability;
	}

	public void setTradingCapability(VendorTradingApplicationsTradingCapability tradingCapability) {
		this.tradingCapability = tradingCapability;
	}
		
}
