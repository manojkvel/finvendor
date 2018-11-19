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
@Table(name="ven_rsrch_rpt_offering")
public class VendorResearchReportsOffering 
	implements Serializable {
	
	private static final long serialVersionUID = 31122016113201L;
	
	@Id
    @Column(name="product_id")
	private String productId; 
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="product_description")
	private String productDescription;
	
	@Column(name="launched_year")
	private String launchedYear;
	
	@ManyToOne(targetEntity=Vendor.class)
	@JoinColumn(name="vendor_id", nullable=false)
	private Vendor vendor;
	
	@ManyToOne(targetEntity=ResearchArea.class)
	@JoinColumn(name="research_area", nullable=false)
	private ResearchArea researchArea;
	
	@Column(name="research_sub_area")
	private String researchSubArea;

	@Column(name="industry_subtype_id")
	private String industrySubTypeId;
	
	@Column(name="stock_fund_issue_covered")
	private String stocksFundsIssuesCovered;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="product_id")
	private VendorResearchReportsCoverageDetails coverageDetails;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="product_id")
	private VendorResearchReportsResearchDetails researchDetails;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="product_id")
	private VendorResearchReportsAnalystProfile analystProfile;

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

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public ResearchArea getResearchArea() {
		return researchArea;
	}

	public void setResearchArea(ResearchArea researchArea) {
		this.researchArea = researchArea;
	}

	public String getResearchSubArea() {
		return researchSubArea;
	}

	public void setResearchSubArea(String researchSubArea) {
		this.researchSubArea = researchSubArea;
	}

	public String getStocksFundsIssuesCovered() {
		return stocksFundsIssuesCovered;
	}

	public void setStocksFundsIssuesCovered(String stocksFundsIssuesCovered) {
		this.stocksFundsIssuesCovered = stocksFundsIssuesCovered;
	}

	public VendorResearchReportsCoverageDetails getCoverageDetails() {
		return coverageDetails;
	}

	public void setCoverageDetails(VendorResearchReportsCoverageDetails coverageDetails) {
		this.coverageDetails = coverageDetails;
	}

	public VendorResearchReportsResearchDetails getResearchDetails() {
		return researchDetails;
	}

	public void setResearchDetails(VendorResearchReportsResearchDetails researchDetails) {
		this.researchDetails = researchDetails;
	}

	public VendorResearchReportsAnalystProfile getAnalystProfile() {
		return analystProfile;
	}

	public void setAnalystProfile(VendorResearchReportsAnalystProfile analystProfile) {
		this.analystProfile = analystProfile;
	}

	public String getIndustrySubTypeId() {
		return industrySubTypeId;
	}

	public void setIndustrySubTypeId(String industrySubTypeId) {
		this.industrySubTypeId = industrySubTypeId;
	}
}
