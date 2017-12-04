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
@Table(name="vendor_researchdetails")
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
	
	@Column(name="ResearchReportName")
	private String researchReportName;
	@Column(name="ResearchReportDesc")
	private String researchReportDesc;
	
	@Column(name="Accessibility")
	private String accessibility;
	@Column(name="Suitability")
	private String suitability;
	@Column(name="ReportFormat")
	private String reportFormat;
	@Column(name="ResearchApplicableYear")
	private String researchApplicableYear;
	@Column(name="ResearchApplicableMonth")
	private String researchApplicableMonth;
	@Column(name="ReportCostType")
	private String reportCostType;
	@Column(name="SubsriptionCostUSDpermonth")
	private String subsriptionCostUSDpermonth;
	@Column(name="subsriptionCostUSDperannum")
	private String subsriptionCostUSDperannum;
	@Column(name="ExistingUserBase")
	private Boolean existingUserBase;
	
	
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
	public String getAccessibility() {
		return accessibility;
	}
	public void setAccessibility(String accessibility) {
		this.accessibility = accessibility;
	}
	
	public String getSuitability() {
		return suitability;
	}
	public void setSuitability(String suitability) {
		this.suitability = suitability;
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
	public String getSubsriptionCostUSDpermonth() {
		return subsriptionCostUSDpermonth;
	}
	public void setSubsriptionCostUSDpermonth(String subsriptionCostUSDpermonth) {
		this.subsriptionCostUSDpermonth = subsriptionCostUSDpermonth;
	}
	public String getSubsriptionCostUSDperannum() {
		return subsriptionCostUSDperannum;
	}
	public void setSubsriptionCostUSDperannum(String subsriptionCostUSDperannum) {
		this.subsriptionCostUSDperannum = subsriptionCostUSDperannum;
	}
	
	public String getReportCostType() {
		return reportCostType;
	}
	public void setReportCostType(String reportCostType) {
		this.reportCostType = reportCostType;
	}
	public Boolean getExistingUserBase() {
		return existingUserBase;
	}
	public void setExistingUserBase(Boolean existingUserBase) {
		this.existingUserBase = existingUserBase;
	}
	
	
}
