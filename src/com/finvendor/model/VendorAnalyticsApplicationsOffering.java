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
@Table(name="ven_analytics_app_offering")
public class VendorAnalyticsApplicationsOffering
	implements Serializable	{

	private static final long serialVersionUID = 07012017172201L;
	
	@Id
    @Column(name="product_id")
	private String productId; 
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="product_description")
	private String productDescription;
	
	@Column(name="launched_year")
	private String launchedYear;
	
	@Column(name="analytical_soln_sub_types")
	private String analyticalSolutionSubTypes;
	
	@ManyToOne(targetEntity=Vendor.class)
	@JoinColumn(name="vendor_id", nullable=false)
	private Vendor vendor;
	
	@ManyToOne(targetEntity=AnalyticalSolutionType.class)
	@JoinColumn(name="analytical_soln_type_id", nullable=false)
	private AnalyticalSolutionType analyticalSolutionType;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="product_id")
	private VendorAnalyticsApplicationsSoftwareDetails softwareDetails;

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

	public String getAnalyticalSolutionSubTypes() {
		return analyticalSolutionSubTypes;
	}

	public void setAnalyticalSolutionSubTypes(String analyticalSolutionSubTypes) {
		this.analyticalSolutionSubTypes = analyticalSolutionSubTypes;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public AnalyticalSolutionType getAnalyticalSolutionType() {
		return analyticalSolutionType;
	}

	public void setAnalyticalSolutionType(AnalyticalSolutionType analyticalSolutionType) {
		this.analyticalSolutionType = analyticalSolutionType;
	}

	public VendorAnalyticsApplicationsSoftwareDetails getSoftwareDetails() {
		return softwareDetails;
	}

	public void setSoftwareDetails(VendorAnalyticsApplicationsSoftwareDetails softwareDetails) {
		this.softwareDetails = softwareDetails;
	}
	
}
