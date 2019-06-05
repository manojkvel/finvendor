/**
 * 
 */
package com.finvendor.modelpojo.staticpojo;

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
	private String suitability;
	private String reportCostType;
	private String reportSubscriptionCost;
	private String reportFormat;
	private String researchApplicableYear;
	private String researchApplicableMonth;
	private String existingUserBase;
	private String subsriptionCostUSDpermonth;
	private String subsriptionCostUSDperannum;
	
	
	
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
	
	public String getSuitability() {
		return suitability;
	}
	public void setSuitability(String suitability) {
		this.suitability = suitability;
	}
	public String getReportCostType() {
		return reportCostType;
	}
	public void setReportCostType(String reportCostType) {
		this.reportCostType = reportCostType;
	}
	
	public String getReportSubscriptionCost() {
		return reportSubscriptionCost;
	}
	public void setReportSubscriptionCost(String reportSubscriptionCost) {
		this.reportSubscriptionCost = reportSubscriptionCost;
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
	public VendorResearchDetails insertDataToModel(VendorResearchDetailsForm vendorResearchDetailsForm) {
		VendorResearchDetails vendorResearchDetails = new VendorResearchDetails();
		vendorResearchDetails.setAccessibility(vendorResearchDetailsForm.getAccessibility());
		vendorResearchDetails.setSuitability(vendorResearchDetailsForm.getSuitability());
		vendorResearchDetails.setOffering(vendorResearchDetailsForm.getOffering());
		vendorResearchDetails.setExistingUserBase(Boolean.parseBoolean(vendorResearchDetailsForm.getExistingUserBase()));
		vendorResearchDetails.setReportFormat(vendorResearchDetailsForm.getReportFormat());
		vendorResearchDetails.setReportCostType(vendorResearchDetailsForm.getReportCostType());
		vendorResearchDetails.setResearchApplicableMonth(vendorResearchDetailsForm.getResearchApplicableMonth());
		vendorResearchDetails.setResearchApplicableYear(vendorResearchDetailsForm.getResearchApplicableYear());
		vendorResearchDetails.setResearchReportDesc(vendorResearchDetailsForm.getResearchReportDesc());
		vendorResearchDetails.setResearchReportName(vendorResearchDetailsForm.getResearchReportName());
		vendorResearchDetails.setSubsriptionCostUSDpermonth(vendorResearchDetailsForm.getSubsriptionCostUSDpermonth());
		vendorResearchDetails.setSubsriptionCostUSDperannum(vendorResearchDetailsForm.getSubsriptionCostUSDperannum());
		
		return vendorResearchDetails;
	}
	public VendorResearchDetailsForm insertDataToForm(VendorResearchDetails vendorResearchDetails) {
		VendorResearchDetailsForm vendorResearchDetailsFrom = new VendorResearchDetailsForm();
		vendorResearchDetailsFrom.setId(vendorResearchDetails.getResearchDetailsId().toString());
		vendorResearchDetailsFrom.setSolution(vendorResearchDetails.getSolution().getName());
		vendorResearchDetailsFrom.setOffering(vendorResearchDetails.getOffering());
		vendorResearchDetailsFrom.setAccessibility(vendorResearchDetails.getAccessibility());
		vendorResearchDetailsFrom.setSuitability(vendorResearchDetails.getSuitability());
		vendorResearchDetailsFrom.setExistingUserBase(vendorResearchDetails.getExistingUserBase().toString());
		vendorResearchDetailsFrom.setReportCostType(vendorResearchDetails.getReportCostType());
		vendorResearchDetailsFrom.setReportFormat(vendorResearchDetails.getReportFormat());
		vendorResearchDetailsFrom.setResearchReportDesc(vendorResearchDetails.getResearchReportDesc());
		vendorResearchDetailsFrom.setResearchReportName(vendorResearchDetails.getResearchReportName());
		vendorResearchDetailsFrom.setResearchApplicableMonth(vendorResearchDetails.getResearchApplicableMonth());
		vendorResearchDetailsFrom.setResearchApplicableYear(vendorResearchDetails.getResearchApplicableYear());
		vendorResearchDetailsFrom.setSubsriptionCostUSDpermonth(vendorResearchDetails.getSubsriptionCostUSDpermonth());
		vendorResearchDetailsFrom.setSubsriptionCostUSDperannum(vendorResearchDetails.getSubsriptionCostUSDperannum());
		
		return vendorResearchDetailsFrom;
	}
}
