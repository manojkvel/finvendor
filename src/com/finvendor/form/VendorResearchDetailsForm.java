/**
 * 
 */
package com.finvendor.form;

import com.finvendor.model.VendorResearchDetails;

public class VendorResearchDetailsForm{
	private String id;
	private String solution;
	private String offering;
	private String researchArea;
	private String researchSubArea;
	private String researchReportName;
	private String researchReportDesc;
	private String regionsCovered;
	private String accessibility;
	private String auitability;
	private String reportCostType;
	private String reportSubscriptionCCY;
	private String reportSubscriptionCost;
	private String reportSubscriptionType;
	private String reportFormat;
	private String researchApplicableYear;
	private String researchApplicableMonth;
	private String existingUserBase;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public VendorResearchDetails insertDataToModel(VendorResearchDetailsForm vendorResearchDetailsForm) {
		VendorResearchDetails vendorResearchDetails = new VendorResearchDetails();
		vendorResearchDetails.setAccessibility(vendorResearchDetailsForm.getAccessibility());
		vendorResearchDetails.setAuitability(vendorResearchDetailsForm.getAuitability());
		vendorResearchDetails.setOffering(vendorResearchDetailsForm.getOffering());
		vendorResearchDetails.setExistingUserBase(vendorResearchDetailsForm.getExistingUserBase());
		vendorResearchDetails.setRegionsCovered(vendorResearchDetailsForm.getRegionsCovered());
		vendorResearchDetails.setReportCostType(vendorResearchDetailsForm.getReportCostType());
		vendorResearchDetails.setReportFormat(vendorResearchDetailsForm.getReportFormat());
		vendorResearchDetails.setReportSubscriptionCCY(vendorResearchDetailsForm.getReportSubscriptionCCY());
		vendorResearchDetails.setReportSubscriptionCost(vendorResearchDetailsForm.getReportSubscriptionCost());
		vendorResearchDetails.setReportSubscriptionType(vendorResearchDetailsForm.getReportSubscriptionType());
		vendorResearchDetails.setResearchApplicableMonth(vendorResearchDetailsForm.getResearchApplicableMonth());
		vendorResearchDetails.setResearchApplicableYear(vendorResearchDetailsForm.getResearchApplicableYear());
		vendorResearchDetails.setResearchArea(vendorResearchDetailsForm.getResearchArea());
		vendorResearchDetails.setResearchReportDesc(vendorResearchDetailsForm.getResearchReportDesc());
		vendorResearchDetails.setResearchReportName(vendorResearchDetailsForm.getResearchReportName());
		vendorResearchDetails.setResearchSubArea(vendorResearchDetailsForm.getResearchSubArea());
		
		return vendorResearchDetails;
	}
	public VendorResearchDetailsForm insertDataToForm(VendorResearchDetails vendorResearchDetails) {
		VendorResearchDetailsForm vendorResearchDetailsFrom = new VendorResearchDetailsForm();
		vendorResearchDetailsFrom.setId(vendorResearchDetails.getResearchDetailsId().toString());
		vendorResearchDetailsFrom.setSolution(vendorResearchDetails.getSolution().getName());
		vendorResearchDetailsFrom.setOffering(vendorResearchDetails.getOffering());
		vendorResearchDetailsFrom.setAccessibility(vendorResearchDetails.getAccessibility());
		vendorResearchDetailsFrom.setAuitability(vendorResearchDetails.getAuitability());
		vendorResearchDetailsFrom.setExistingUserBase(vendorResearchDetails.getExistingUserBase());
		vendorResearchDetailsFrom.setRegionsCovered(vendorResearchDetails.getRegionsCovered());
		vendorResearchDetailsFrom.setReportCostType(vendorResearchDetails.getReportCostType());
		vendorResearchDetailsFrom.setReportFormat(vendorResearchDetails.getReportFormat());
		vendorResearchDetailsFrom.setReportSubscriptionCCY(vendorResearchDetails.getReportSubscriptionCCY());
		vendorResearchDetailsFrom.setReportSubscriptionCost(vendorResearchDetails.getReportSubscriptionCost());
		vendorResearchDetailsFrom.setReportSubscriptionType(vendorResearchDetails.getReportSubscriptionType());
		vendorResearchDetailsFrom.setResearchApplicableMonth(vendorResearchDetails.getResearchApplicableMonth());
		vendorResearchDetailsFrom.setResearchApplicableYear(vendorResearchDetails.getResearchApplicableYear());
		vendorResearchDetailsFrom.setResearchArea(vendorResearchDetails.getResearchArea());
		vendorResearchDetailsFrom.setResearchReportDesc(vendorResearchDetails.getResearchReportDesc());
		vendorResearchDetailsFrom.setResearchReportName(vendorResearchDetails.getResearchReportName());
		vendorResearchDetailsFrom.setResearchSubArea(vendorResearchDetails.getResearchSubArea());
		return vendorResearchDetailsFrom;
	}
}
