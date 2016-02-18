/**
 * 
 */
package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author rayulu vemula
 *
 */
@Entity
@Table(name="vendor_ResearchDetails")
public class VendorResearchDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="ResearchDetails_id")
	@GeneratedValue
    private Integer researchDetailsId;
	
	
	@ManyToOne(targetEntity=Vendor.class,fetch=FetchType.LAZY)
	@JoinColumn(name="vendor_id", nullable=false)
	private Vendor vendor;
	
	@ManyToOne(targetEntity=Solutions.class,fetch=FetchType.EAGER)
	@JoinColumn(name="solution_id", nullable=false)
	private Solutions solution;
	
	@Column(name="Offering")
	private String offering;
	@Column(name="ResearchArea")
	private String researchArea;
	@Column(name="ResearchSubArea")
	private String researchSubArea;
	@Column(name="ResearchReportName")
	private String researchReportName;
	@Column(name="ResearchReportDesc")
	private String researchReportDesc;
	@Column(name="RegionsCovered")
	private String regionsCovered;
	@Column(name="Accessibility")
	private String accessibility;
	@Column(name="Auitability")
	private String auitability;
	@Column(name="ReportCostType")
	private String reportCostType;
	@Column(name="ReportSubscriptionCCY")
	private String reportSubscriptionCCY;
	@Column(name="ReportSubscriptionCost")
	private String reportSubscriptionCost;
	@Column(name="ReportSubscriptionType")
	private String reportSubscriptionType;
	@Column(name="ReportFormat")
	private String reportFormat;
	@Column(name="ResearchApplicableYear")
	private String researchApplicableYear;
	@Column(name="ResearchApplicableMonth")
	private String researchApplicableMonth;
	@Column(name="ExistingUserBase")
	private String existingUserBase;
	
	
	
	public Integer getResearchDetailsId() {
		return researchDetailsId;
	}
	public void setResearchDetailsId(Integer researchDetailsId) {
		this.researchDetailsId = researchDetailsId;
	}
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	public Solutions getSolution() {
		return solution;
	}
	public void setSolution(Solutions solution) {
		this.solution = solution;
	}
	
	public String getOffering() {
		return offering;
	}
	public void setOffering(String offering) {
		this.offering = offering;
	}
	public String getResearchArea() {
		return researchArea;
	}
	public void setResearchArea(String researchArea) {
		this.researchArea = researchArea;
	}
	public String getResearchSubArea() {
		return researchSubArea;
	}
	public void setResearchSubArea(String researchSubArea) {
		this.researchSubArea = researchSubArea;
	}
	public String getResearchReportName() {
		return researchReportName;
	}
	public void setResearchReportName(String researchReportName) {
		this.researchReportName = researchReportName;
	}
	public String getResearchReportDesc() {
		return researchReportDesc;
	}
	public void setResearchReportDesc(String researchReportDesc) {
		this.researchReportDesc = researchReportDesc;
	}
	public String getRegionsCovered() {
		return regionsCovered;
	}
	public void setRegionsCovered(String regionsCovered) {
		this.regionsCovered = regionsCovered;
	}
	public String getAccessibility() {
		return accessibility;
	}
	public void setAccessibility(String accessibility) {
		this.accessibility = accessibility;
	}
	public String getAuitability() {
		return auitability;
	}
	public void setAuitability(String auitability) {
		this.auitability = auitability;
	}
	public String getReportCostType() {
		return reportCostType;
	}
	public void setReportCostType(String reportCostType) {
		this.reportCostType = reportCostType;
	}
	public String getReportSubscriptionCCY() {
		return reportSubscriptionCCY;
	}
	public void setReportSubscriptionCCY(String reportSubscriptionCCY) {
		this.reportSubscriptionCCY = reportSubscriptionCCY;
	}
	public String getReportSubscriptionCost() {
		return reportSubscriptionCost;
	}
	public void setReportSubscriptionCost(String reportSubscriptionCost) {
		this.reportSubscriptionCost = reportSubscriptionCost;
	}
	public String getReportSubscriptionType() {
		return reportSubscriptionType;
	}
	public void setReportSubscriptionType(String reportSubscriptionType) {
		this.reportSubscriptionType = reportSubscriptionType;
	}
	public String getReportFormat() {
		return reportFormat;
	}
	public void setReportFormat(String reportFormat) {
		this.reportFormat = reportFormat;
	}
	public String getResearchApplicableYear() {
		return researchApplicableYear;
	}
	public void setResearchApplicableYear(String researchApplicableYear) {
		this.researchApplicableYear = researchApplicableYear;
	}
	public String getResearchApplicableMonth() {
		return researchApplicableMonth;
	}
	public void setResearchApplicableMonth(String researchApplicableMonth) {
		this.researchApplicableMonth = researchApplicableMonth;
	}
	public String getExistingUserBase() {
		return existingUserBase;
	}
	public void setExistingUserBase(String existingUserBase) {
		this.existingUserBase = existingUserBase;
	}
	
	
	}
