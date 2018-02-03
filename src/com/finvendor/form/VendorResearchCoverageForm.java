/**
 * 
 */
package com.finvendor.form;

import com.finvendor.model.VendorResearchCoverage;

/**
 * @author rayulu vemula
 *
 */
public class VendorResearchCoverageForm{

	private String id;
	private String solution;
	private String offering;
	private String offeringDesc;
	private String researchArea;
	private String regionsCovered;
	private String researchSubArea;
	private String totalResearchAnalyst;
	private String researchPreparedbyCFA;
	private String existingClientBase;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getOfferingDesc() {
		return offeringDesc;
	}
	public void setOfferingDesc(String offeringDesc) {
		this.offeringDesc = offeringDesc;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
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
	public String getResearchPreparedbyCFA() {
		return researchPreparedbyCFA;
	}
	public void setResearchPreparedbyCFA(String researchPreparedbyCFA) {
		this.researchPreparedbyCFA = researchPreparedbyCFA;
	}
	public String getExistingClientBase() {
		return existingClientBase;
	}
	public void setExistingClientBase(String existingClientBase) {
		this.existingClientBase = existingClientBase;
	}
	
	
	public VendorResearchCoverage insertDataToModel(VendorResearchCoverageForm vendorResearchCoverageForm) {
		VendorResearchCoverage vendorResearchCoverage = new VendorResearchCoverage();
		
		vendorResearchCoverage.setResearchArea(vendorResearchCoverageForm.getResearchArea());
		vendorResearchCoverage.setOffering(vendorResearchCoverageForm.getOffering());
		vendorResearchCoverage.setOfferingDesc(vendorResearchCoverageForm.getOfferingDesc());
		vendorResearchCoverage.setRegionsCovered(vendorResearchCoverageForm.getRegionsCovered());
		vendorResearchCoverage.setResearchArea(vendorResearchCoverageForm.getResearchArea());
		vendorResearchCoverage.setResearchSubArea(vendorResearchCoverageForm.getResearchSubArea());
		vendorResearchCoverage.setTotalResearchAnalyst(vendorResearchCoverageForm.getTotalResearchAnalyst());
		vendorResearchCoverage.setResearchPreparedbyCFA(vendorResearchCoverageForm.getResearchPreparedbyCFA()!= null?true:false);
		vendorResearchCoverage.setExistingClientBase(vendorResearchCoverageForm.getExistingClientBase());
		
		
		return vendorResearchCoverage;
	}
	public VendorResearchCoverageForm insertDataToForm(VendorResearchCoverage vendorResearchCoverage) {
		VendorResearchCoverageForm vendorResearchCoverageForm = new VendorResearchCoverageForm();
		vendorResearchCoverageForm.setId(vendorResearchCoverage.getResearchCoverageId().toString());
		vendorResearchCoverageForm.setSolution(vendorResearchCoverage.getSolution().getName());
		vendorResearchCoverageForm.setResearchArea(vendorResearchCoverage.getResearchArea());
		vendorResearchCoverageForm.setOffering(vendorResearchCoverage.getOffering());
		vendorResearchCoverageForm.setOfferingDesc(vendorResearchCoverage.getOfferingDesc());
		vendorResearchCoverageForm.setRegionsCovered(vendorResearchCoverage.getRegionsCovered());
		vendorResearchCoverageForm.setResearchSubArea(vendorResearchCoverage.getResearchSubArea());
		vendorResearchCoverageForm.setTotalResearchAnalyst(vendorResearchCoverage.getTotalResearchAnalyst());
		vendorResearchCoverageForm.setResearchPreparedbyCFA(vendorResearchCoverage.getResearchPreparedbyCFA().toString());
		vendorResearchCoverageForm.setExistingClientBase(vendorResearchCoverage.getExistingClientBase());
		return vendorResearchCoverageForm;
	}
	
	
	
	}
