/**
 * 
 */
package com.finvendor.form;

import com.finvendor.model.VendorAnalystProfile;

public class VendorAnalystProfileForm {
	private String id;
	private String offering;
	private String offeringDesc;
	private String solution;
private String researchArea;
private String researchSubArea;
private String analystName;
private String researchAnalystWithCFA;

private String analystRegionofIncorp;
private String analystCountryofIncorp;
private String analystYearofExp;
private String analystAwards;
	
	
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
	public String getAnalystName() {
		return analystName;
	}
	public void setAnalystName(String analystName) {
		this.analystName = analystName;
	}
	
	public String getResearchAnalystWithCFA() {
		return researchAnalystWithCFA;
	}
	public void setResearchAnalystWithCFA(String researchAnalystWithCFA) {
		this.researchAnalystWithCFA = researchAnalystWithCFA;
	}
	public String getAnalystRegionofIncorp() {
		return analystRegionofIncorp;
	}
	public void setAnalystRegionofIncorp(String analystRegionofIncorp) {
		this.analystRegionofIncorp = analystRegionofIncorp;
	}
	public String getAnalystCountryofIncorp() {
		return analystCountryofIncorp;
	}
	public void setAnalystCountryofIncorp(String analystCountryofIncorp) {
		this.analystCountryofIncorp = analystCountryofIncorp;
	}
	public String getAnalystYearofExp() {
		return analystYearofExp;
	}
	public void setAnalystYearofExp(String analystYearofExp) {
		this.analystYearofExp = analystYearofExp;
	}
	public String getAnalystAwards() {
		return analystAwards;
	}
	public void setAnalystAwards(String analystAwards) {
		this.analystAwards = analystAwards;
	}
	
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public VendorAnalystProfileForm insertDataToForm(VendorAnalystProfile vendorAnalystProfile) {
		VendorAnalystProfileForm vendorAnalystProfileForm = new VendorAnalystProfileForm();
		vendorAnalystProfileForm.setId(vendorAnalystProfile.getAnalystProfileId().toString());
		vendorAnalystProfileForm.setSolution(vendorAnalystProfile.getSolution().getName());
		vendorAnalystProfileForm.setOffering(vendorAnalystProfile.getOffering());
		vendorAnalystProfileForm.setOfferingDesc(vendorAnalystProfile.getOfferingDesc());
		vendorAnalystProfileForm.setAnalystAwards(vendorAnalystProfile.getAnalystAwards());
		vendorAnalystProfileForm.setAnalystCountryofIncorp(vendorAnalystProfile.getAnalystCountryofIncorp());
		vendorAnalystProfileForm.setAnalystName(vendorAnalystProfile.getAnalystName());
		vendorAnalystProfileForm.setAnalystRegionofIncorp(vendorAnalystProfile.getAnalystRegionofIncorp());
		vendorAnalystProfileForm.setAnalystYearofExp(vendorAnalystProfile.getAnalystYearofExp());
		vendorAnalystProfileForm.setResearchAnalystWithCFA(vendorAnalystProfile.getResearchAnalystWithCFA().toString());
		vendorAnalystProfileForm.setResearchArea(vendorAnalystProfile.getResearchArea());
		vendorAnalystProfileForm.setResearchSubArea(vendorAnalystProfile.getResearchSubArea());

		return vendorAnalystProfileForm;
	}
	public VendorAnalystProfile insertDataToModel(VendorAnalystProfileForm vendorAnalystProfileForm) {
		VendorAnalystProfile vendorAnalystProfile = new VendorAnalystProfile();
		vendorAnalystProfile.setOffering(vendorAnalystProfileForm.getOffering());
		vendorAnalystProfile.setOfferingDesc(vendorAnalystProfileForm.getOfferingDesc());
		vendorAnalystProfile.setAnalystAwards(vendorAnalystProfileForm.getAnalystAwards());
		vendorAnalystProfile.setAnalystCountryofIncorp(vendorAnalystProfileForm.getAnalystCountryofIncorp());
		vendorAnalystProfile.setAnalystName(vendorAnalystProfileForm.getAnalystName());
		vendorAnalystProfile.setAnalystRegionofIncorp(vendorAnalystProfileForm.getAnalystRegionofIncorp());
		vendorAnalystProfile.setAnalystYearofExp(vendorAnalystProfileForm.getAnalystYearofExp());
		vendorAnalystProfile.setResearchAnalystWithCFA(Boolean.parseBoolean(vendorAnalystProfileForm.getResearchAnalystWithCFA()));
		vendorAnalystProfile.setResearchArea(vendorAnalystProfileForm.getResearchArea());
		vendorAnalystProfile.setResearchSubArea(vendorAnalystProfileForm.getResearchSubArea());
		
		return vendorAnalystProfile;
		}
	}
