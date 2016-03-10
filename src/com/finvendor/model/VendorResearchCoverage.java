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
@Table(name="vendor_ResearchCoverage")
public class VendorResearchCoverage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="ResearchCoverage_id")
	@GeneratedValue
    private Integer researchCoverageId;
	
	
	@ManyToOne(targetEntity=Vendor.class,fetch=FetchType.LAZY)
	@JoinColumn(name="vendor_id", nullable=false)
	private Vendor vendor;
	
	@ManyToOne(targetEntity=Solutions.class,fetch=FetchType.EAGER)
	@JoinColumn(name="solution_id", nullable=false)
	private Solutions solution;
	
	
	@Column(name="Offering")
	private String offering;
	@Column(name="OfferingDesc")
	private String offeringDesc;
	@Column(name="ResearchArea")
	private String researchArea;
	@Column(name="RegionsCovered")
	private String regionsCovered;
	@Column(name="ResearchSubArea")
	private String researchSubArea;
	@Column(name="TotalResearchAnalyst")
	private String totalResearchAnalyst;
	@Column(name="ResearchPreparedbyCFA")
	private Boolean researchPreparedbyCFA;
	@Column(name="ExistingClientBase")
	private String existingClientBase;
	

	public Integer getResearchCoverageId() {
		return researchCoverageId;
	}
	public void setResearchCoverageId(Integer researchCoverageId) {
		this.researchCoverageId = researchCoverageId;
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
	
	public String getOfferingDesc() {
		return offeringDesc;
	}
	public void setOfferingDesc(String offeringDesc) {
		this.offeringDesc = offeringDesc;
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
	public String getRegionsCovered() {
		return regionsCovered;
	}
	public void setRegionsCovered(String regionsCovered) {
		this.regionsCovered = regionsCovered;
	}
	public String getResearchSubArea() {
		return researchSubArea;
	}
	public void setResearchSubArea(String researchSubArea) {
		this.researchSubArea = researchSubArea;
	}
	public String getTotalResearchAnalyst() {
		return totalResearchAnalyst;
	}
	public void setTotalResearchAnalyst(String totalResearchAnalyst) {
		this.totalResearchAnalyst = totalResearchAnalyst;
	}
	
	public Boolean getResearchPreparedbyCFA() {
		return researchPreparedbyCFA;
	}
	public void setResearchPreparedbyCFA(Boolean researchPreparedbyCFA) {
		this.researchPreparedbyCFA = researchPreparedbyCFA;
	}
	public String getExistingClientBase() {
		return existingClientBase;
	}
	public void setExistingClientBase(String existingClientBase) {
		this.existingClientBase = existingClientBase;
	}
	
		
	}
