<<<<<<< HEAD
/**
 * 
 */
package com.finvendor.form;

import com.finvendor.model.VendorAnalyticsSoftwareDetails;

public class VendorAnalyticsSoftwareDetailsForm{

	private String id;
	private String solution;
	private String offering;
	private String offeringDesc;
	private String analyticsSolutionsType;
	private String applicationSubscriptionCost;
	private String analyticsSolutionsSubType;
	private String applicationName;
	private String applicationBriefDesc;
	private String accessibility;
	private String suitability;
	private String applicationCostType;
	private String applicationSubscriptionCCY;
	private String applicationSubscriptionType;
	private String realTimeMarketData;
	private String customizableCalculationModels;
	private String addOns;
	private String operatingSystem;
	private String softwarespecifications;
	private String launchedYear;
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
	public String getOfferingDesc() {
		return offeringDesc;
	}
	public void setOfferingDesc(String offeringDesc) {
		this.offeringDesc = offeringDesc;
	}
	public String getAnalyticsSolutionsType() {
		return analyticsSolutionsType;
	}
	public void setAnalyticsSolutionsType(String analyticsSolutionsType) {
		this.analyticsSolutionsType = analyticsSolutionsType;
	}
	public String getApplicationSubscriptionCost() {
		return applicationSubscriptionCost;
	}
	public void setApplicationSubscriptionCost(String applicationSubscriptionCost) {
		this.applicationSubscriptionCost = applicationSubscriptionCost;
	}
	public String getAnalyticsSolutionsSubType() {
		return analyticsSolutionsSubType;
	}
	public void setAnalyticsSolutionsSubType(String analyticsSolutionsSubType) {
		this.analyticsSolutionsSubType = analyticsSolutionsSubType;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getApplicationBriefDesc() {
		return applicationBriefDesc;
	}
	public void setApplicationBriefDesc(String applicationBriefDesc) {
		this.applicationBriefDesc = applicationBriefDesc;
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
	public String getApplicationCostType() {
		return applicationCostType;
	}
	public void setApplicationCostType(String applicationCostType) {
		this.applicationCostType = applicationCostType;
	}
	public String getApplicationSubscriptionCCY() {
		return applicationSubscriptionCCY;
	}
	public void setApplicationSubscriptionCCY(String applicationSubscriptionCCY) {
		this.applicationSubscriptionCCY = applicationSubscriptionCCY;
	}
	public String getApplicationSubscriptionType() {
		return applicationSubscriptionType;
	}
	public void setApplicationSubscriptionType(String applicationSubscriptionType) {
		this.applicationSubscriptionType = applicationSubscriptionType;
	}
	public String getRealTimeMarketData() {
		return realTimeMarketData;
	}
	public void setRealTimeMarketData(String realTimeMarketData) {
		this.realTimeMarketData = realTimeMarketData;
	}
	public String getCustomizableCalculationModels() {
		return customizableCalculationModels;
	}
	public void setCustomizableCalculationModels(String customizableCalculationModels) {
		this.customizableCalculationModels = customizableCalculationModels;
	}
	public String getAddOns() {
		return addOns;
	}
	public void setAddOns(String addOns) {
		this.addOns = addOns;
	}
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	public String getSoftwarespecifications() {
		return softwarespecifications;
	}
	public void setSoftwarespecifications(String softwarespecifications) {
		this.softwarespecifications = softwarespecifications;
	}
	public String getLaunchedYear() {
		return launchedYear;
	}
	public void setLaunchedYear(String launchedYear) {
		this.launchedYear = launchedYear;
	}
	public String getExistingUserBase() {
		return existingUserBase;
	}
	public void setExistingUserBase(String existingUserBase) {
		this.existingUserBase = existingUserBase;
	}
	public VendorAnalyticsSoftwareDetails insertDataToModel(VendorAnalyticsSoftwareDetailsForm analyticsSoftwareDetailsForm) {
		VendorAnalyticsSoftwareDetails vendorAnalyticsSoftwareDetails = new VendorAnalyticsSoftwareDetails();
		
		vendorAnalyticsSoftwareDetails.setAccessibility(analyticsSoftwareDetailsForm.getAccessibility());
		vendorAnalyticsSoftwareDetails.setAddOns(analyticsSoftwareDetailsForm.getAddOns());
		vendorAnalyticsSoftwareDetails.setAnalyticsSolutionsSubType(analyticsSoftwareDetailsForm.getAnalyticsSolutionsSubType());
		vendorAnalyticsSoftwareDetails.setOffering(analyticsSoftwareDetailsForm.getOffering());
		vendorAnalyticsSoftwareDetails.setOfferingDesc(analyticsSoftwareDetailsForm.getOfferingDesc());
		vendorAnalyticsSoftwareDetails.setAnalyticsSolutionsType(analyticsSoftwareDetailsForm.getOffering());
		vendorAnalyticsSoftwareDetails.setApplicationBriefDesc(analyticsSoftwareDetailsForm.getApplicationBriefDesc());
		vendorAnalyticsSoftwareDetails.setApplicationCostType(analyticsSoftwareDetailsForm.getApplicationCostType());
		vendorAnalyticsSoftwareDetails.setApplicationName(analyticsSoftwareDetailsForm.getApplicationName());
		vendorAnalyticsSoftwareDetails.setApplicationSubscriptionCCY(analyticsSoftwareDetailsForm.getApplicationSubscriptionCCY());
		vendorAnalyticsSoftwareDetails.setApplicationSubscriptionCost(analyticsSoftwareDetailsForm.getApplicationSubscriptionCost());
		vendorAnalyticsSoftwareDetails.setApplicationSubscriptionType(analyticsSoftwareDetailsForm.getApplicationSubscriptionType());
		vendorAnalyticsSoftwareDetails.setCustomizableCalculationModels(analyticsSoftwareDetailsForm.getCustomizableCalculationModels());
		vendorAnalyticsSoftwareDetails.setExistingUserBase(analyticsSoftwareDetailsForm.getExistingUserBase());
		vendorAnalyticsSoftwareDetails.setLaunchedYear(analyticsSoftwareDetailsForm.getLaunchedYear());
		vendorAnalyticsSoftwareDetails.setOperatingSystem(analyticsSoftwareDetailsForm.getOperatingSystem());
		vendorAnalyticsSoftwareDetails.setRealTimeMarketData(analyticsSoftwareDetailsForm.getRealTimeMarketData());
		vendorAnalyticsSoftwareDetails.setSoftwarespecifications(analyticsSoftwareDetailsForm.getSoftwarespecifications());
		vendorAnalyticsSoftwareDetails.setSuitability(analyticsSoftwareDetailsForm.getSuitability());
		return vendorAnalyticsSoftwareDetails;
	}
	public VendorAnalyticsSoftwareDetailsForm insertDataToForm(VendorAnalyticsSoftwareDetails vendorAnalyticsSoftwareDetails) {

		VendorAnalyticsSoftwareDetailsForm vendorTradingSoftwareDetailsForm = new VendorAnalyticsSoftwareDetailsForm();
		vendorTradingSoftwareDetailsForm.setId(vendorAnalyticsSoftwareDetails.getAnalyticsSoftwareDetailsId().toString());
		vendorTradingSoftwareDetailsForm.setSolution(vendorAnalyticsSoftwareDetails.getSolution().getName());
		vendorTradingSoftwareDetailsForm.setOffering(vendorAnalyticsSoftwareDetails.getOffering());
		vendorTradingSoftwareDetailsForm.setOfferingDesc(vendorAnalyticsSoftwareDetails.getOfferingDesc());
		vendorTradingSoftwareDetailsForm.setAccessibility(vendorAnalyticsSoftwareDetails.getAccessibility());
		vendorTradingSoftwareDetailsForm.setAddOns(vendorAnalyticsSoftwareDetails.getAddOns());
		vendorTradingSoftwareDetailsForm.setAnalyticsSolutionsSubType(vendorAnalyticsSoftwareDetails.getAnalyticsSolutionsSubType());
		vendorTradingSoftwareDetailsForm.setAnalyticsSolutionsType(vendorAnalyticsSoftwareDetails.getAnalyticsSolutionsType());
		vendorTradingSoftwareDetailsForm.setApplicationBriefDesc(vendorAnalyticsSoftwareDetails.getApplicationBriefDesc());
		vendorTradingSoftwareDetailsForm.setApplicationCostType(vendorAnalyticsSoftwareDetails.getApplicationCostType());
		vendorTradingSoftwareDetailsForm.setApplicationName(vendorAnalyticsSoftwareDetails.getApplicationName());
		vendorTradingSoftwareDetailsForm.setApplicationSubscriptionCCY(vendorAnalyticsSoftwareDetails.getApplicationSubscriptionCCY());
		vendorTradingSoftwareDetailsForm.setApplicationSubscriptionCost(vendorAnalyticsSoftwareDetails.getApplicationSubscriptionCost());
		vendorTradingSoftwareDetailsForm.setApplicationSubscriptionType(vendorAnalyticsSoftwareDetails.getApplicationSubscriptionType());
		vendorTradingSoftwareDetailsForm.setCustomizableCalculationModels(vendorAnalyticsSoftwareDetails.getCustomizableCalculationModels());
		vendorTradingSoftwareDetailsForm.setExistingUserBase(vendorAnalyticsSoftwareDetails.getExistingUserBase());
		vendorTradingSoftwareDetailsForm.setLaunchedYear(vendorAnalyticsSoftwareDetails.getLaunchedYear());
		vendorTradingSoftwareDetailsForm.setOperatingSystem(vendorAnalyticsSoftwareDetails.getOperatingSystem());
		vendorTradingSoftwareDetailsForm.setRealTimeMarketData(vendorAnalyticsSoftwareDetails.getRealTimeMarketData());
		vendorTradingSoftwareDetailsForm.setSoftwarespecifications(vendorAnalyticsSoftwareDetails.getSoftwarespecifications());
		vendorTradingSoftwareDetailsForm.setSuitability(vendorAnalyticsSoftwareDetails.getSuitability());
		return vendorTradingSoftwareDetailsForm;
	
		
		
		
	}
		
	
	}
=======
/**
 * 
 */
package com.finvendor.form;

import com.finvendor.model.VendorAnalyticsSoftwareDetails;

public class VendorAnalyticsSoftwareDetailsForm{

	private String id;
	private String solution;
	private String offering;
	private String offeringDesc;
	private String analyticsSolutionsType;
	private String applicationSubscriptionCost;
	private String analyticsSolutionsSubType;
	private String applicationName;
	private String applicationBriefDesc;
	private String accessibility;
	private String suitability;
	private String applicationCostType;
	private String applicationSubscriptionCCY;
	private String applicationSubscriptionType;
	private String realTimeMarketData;
	private String customizableCalculationModels;
	private String addOns;
	private String operatingSystem;
	private String softwarespecifications;
	private String launchedYear;
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
	public String getOfferingDesc() {
		return offeringDesc;
	}
	public void setOfferingDesc(String offeringDesc) {
		this.offeringDesc = offeringDesc;
	}
	public String getAnalyticsSolutionsType() {
		return analyticsSolutionsType;
	}
	public void setAnalyticsSolutionsType(String analyticsSolutionsType) {
		this.analyticsSolutionsType = analyticsSolutionsType;
	}
	public String getApplicationSubscriptionCost() {
		return applicationSubscriptionCost;
	}
	public void setApplicationSubscriptionCost(String applicationSubscriptionCost) {
		this.applicationSubscriptionCost = applicationSubscriptionCost;
	}
	public String getAnalyticsSolutionsSubType() {
		return analyticsSolutionsSubType;
	}
	public void setAnalyticsSolutionsSubType(String analyticsSolutionsSubType) {
		this.analyticsSolutionsSubType = analyticsSolutionsSubType;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getApplicationBriefDesc() {
		return applicationBriefDesc;
	}
	public void setApplicationBriefDesc(String applicationBriefDesc) {
		this.applicationBriefDesc = applicationBriefDesc;
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
	public String getApplicationCostType() {
		return applicationCostType;
	}
	public void setApplicationCostType(String applicationCostType) {
		this.applicationCostType = applicationCostType;
	}
	public String getApplicationSubscriptionCCY() {
		return applicationSubscriptionCCY;
	}
	public void setApplicationSubscriptionCCY(String applicationSubscriptionCCY) {
		this.applicationSubscriptionCCY = applicationSubscriptionCCY;
	}
	public String getApplicationSubscriptionType() {
		return applicationSubscriptionType;
	}
	public void setApplicationSubscriptionType(String applicationSubscriptionType) {
		this.applicationSubscriptionType = applicationSubscriptionType;
	}
	public String getRealTimeMarketData() {
		return realTimeMarketData;
	}
	public void setRealTimeMarketData(String realTimeMarketData) {
		this.realTimeMarketData = realTimeMarketData;
	}
	public String getCustomizableCalculationModels() {
		return customizableCalculationModels;
	}
	public void setCustomizableCalculationModels(String customizableCalculationModels) {
		this.customizableCalculationModels = customizableCalculationModels;
	}
	public String getAddOns() {
		return addOns;
	}
	public void setAddOns(String addOns) {
		this.addOns = addOns;
	}
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	public String getSoftwarespecifications() {
		return softwarespecifications;
	}
	public void setSoftwarespecifications(String softwarespecifications) {
		this.softwarespecifications = softwarespecifications;
	}
	public String getLaunchedYear() {
		return launchedYear;
	}
	public void setLaunchedYear(String launchedYear) {
		this.launchedYear = launchedYear;
	}
	public String getExistingUserBase() {
		return existingUserBase;
	}
	public void setExistingUserBase(String existingUserBase) {
		this.existingUserBase = existingUserBase;
	}
	public VendorAnalyticsSoftwareDetails insertDataToModel(VendorAnalyticsSoftwareDetailsForm analyticsSoftwareDetailsForm) {
		VendorAnalyticsSoftwareDetails vendorAnalyticsSoftwareDetails = new VendorAnalyticsSoftwareDetails();
		
		vendorAnalyticsSoftwareDetails.setAccessibility(analyticsSoftwareDetailsForm.getAccessibility());
		vendorAnalyticsSoftwareDetails.setAddOns(analyticsSoftwareDetailsForm.getAddOns());
		vendorAnalyticsSoftwareDetails.setAnalyticsSolutionsSubType(analyticsSoftwareDetailsForm.getAnalyticsSolutionsSubType());
		vendorAnalyticsSoftwareDetails.setOffering(analyticsSoftwareDetailsForm.getOffering());
		vendorAnalyticsSoftwareDetails.setOfferingDesc(analyticsSoftwareDetailsForm.getOfferingDesc());
		vendorAnalyticsSoftwareDetails.setAnalyticsSolutionsType(analyticsSoftwareDetailsForm.getOffering());
		vendorAnalyticsSoftwareDetails.setApplicationBriefDesc(analyticsSoftwareDetailsForm.getApplicationBriefDesc());
		vendorAnalyticsSoftwareDetails.setApplicationCostType(analyticsSoftwareDetailsForm.getApplicationCostType());
		vendorAnalyticsSoftwareDetails.setApplicationName(analyticsSoftwareDetailsForm.getApplicationName());
		vendorAnalyticsSoftwareDetails.setApplicationSubscriptionCCY(analyticsSoftwareDetailsForm.getApplicationSubscriptionCCY());
		vendorAnalyticsSoftwareDetails.setApplicationSubscriptionCost(analyticsSoftwareDetailsForm.getApplicationSubscriptionCost());
		vendorAnalyticsSoftwareDetails.setApplicationSubscriptionType(analyticsSoftwareDetailsForm.getApplicationSubscriptionType());
		vendorAnalyticsSoftwareDetails.setCustomizableCalculationModels(analyticsSoftwareDetailsForm.getCustomizableCalculationModels());
		vendorAnalyticsSoftwareDetails.setExistingUserBase(analyticsSoftwareDetailsForm.getExistingUserBase());
		vendorAnalyticsSoftwareDetails.setLaunchedYear(analyticsSoftwareDetailsForm.getLaunchedYear());
		vendorAnalyticsSoftwareDetails.setOperatingSystem(analyticsSoftwareDetailsForm.getOperatingSystem());
		vendorAnalyticsSoftwareDetails.setRealTimeMarketData(analyticsSoftwareDetailsForm.getRealTimeMarketData());
		vendorAnalyticsSoftwareDetails.setSoftwarespecifications(analyticsSoftwareDetailsForm.getSoftwarespecifications());
		vendorAnalyticsSoftwareDetails.setSuitability(analyticsSoftwareDetailsForm.getSuitability());
		return vendorAnalyticsSoftwareDetails;
	}
	public VendorAnalyticsSoftwareDetailsForm insertDataToForm(VendorAnalyticsSoftwareDetails vendorAnalyticsSoftwareDetails) {

		VendorAnalyticsSoftwareDetailsForm vendorTradingSoftwareDetailsForm = new VendorAnalyticsSoftwareDetailsForm();
		vendorTradingSoftwareDetailsForm.setId(vendorAnalyticsSoftwareDetails.getAnalyticsSoftwareDetailsId().toString());
		vendorTradingSoftwareDetailsForm.setSolution(vendorAnalyticsSoftwareDetails.getSolution().getName());
		vendorTradingSoftwareDetailsForm.setOffering(vendorAnalyticsSoftwareDetails.getOffering());
		vendorTradingSoftwareDetailsForm.setOfferingDesc(vendorAnalyticsSoftwareDetails.getOfferingDesc());
		vendorTradingSoftwareDetailsForm.setAccessibility(vendorAnalyticsSoftwareDetails.getAccessibility());
		vendorTradingSoftwareDetailsForm.setAddOns(vendorAnalyticsSoftwareDetails.getAddOns());
		vendorTradingSoftwareDetailsForm.setAnalyticsSolutionsSubType(vendorAnalyticsSoftwareDetails.getAnalyticsSolutionsSubType());
		vendorTradingSoftwareDetailsForm.setAnalyticsSolutionsType(vendorAnalyticsSoftwareDetails.getAnalyticsSolutionsType());
		vendorTradingSoftwareDetailsForm.setApplicationBriefDesc(vendorAnalyticsSoftwareDetails.getApplicationBriefDesc());
		vendorTradingSoftwareDetailsForm.setApplicationCostType(vendorAnalyticsSoftwareDetails.getApplicationCostType());
		vendorTradingSoftwareDetailsForm.setApplicationName(vendorAnalyticsSoftwareDetails.getApplicationName());
		vendorTradingSoftwareDetailsForm.setApplicationSubscriptionCCY(vendorAnalyticsSoftwareDetails.getApplicationSubscriptionCCY());
		vendorTradingSoftwareDetailsForm.setApplicationSubscriptionCost(vendorAnalyticsSoftwareDetails.getApplicationSubscriptionCost());
		vendorTradingSoftwareDetailsForm.setApplicationSubscriptionType(vendorAnalyticsSoftwareDetails.getApplicationSubscriptionType());
		vendorTradingSoftwareDetailsForm.setCustomizableCalculationModels(vendorAnalyticsSoftwareDetails.getCustomizableCalculationModels());
		vendorTradingSoftwareDetailsForm.setExistingUserBase(vendorAnalyticsSoftwareDetails.getExistingUserBase());
		vendorTradingSoftwareDetailsForm.setLaunchedYear(vendorAnalyticsSoftwareDetails.getLaunchedYear());
		vendorTradingSoftwareDetailsForm.setOperatingSystem(vendorAnalyticsSoftwareDetails.getOperatingSystem());
		vendorTradingSoftwareDetailsForm.setRealTimeMarketData(vendorAnalyticsSoftwareDetails.getRealTimeMarketData());
		vendorTradingSoftwareDetailsForm.setSoftwarespecifications(vendorAnalyticsSoftwareDetails.getSoftwarespecifications());
		vendorTradingSoftwareDetailsForm.setSuitability(vendorAnalyticsSoftwareDetails.getSuitability());
		return vendorTradingSoftwareDetailsForm;
	
		
		
		
	}
		
	
	}
>>>>>>> origin/master
