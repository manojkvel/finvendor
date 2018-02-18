package com.finvendor.json.bean;

public class VendorResearchReportsOfferingJson {

	private String productId; 	
	private String productName;	
	@Override
	public String toString() {
		return "VendorResearchReportsOfferingJson [productId=" + productId + ", productName=" + productName
				+ ", productDescription=" + productDescription + ", launchedYear=" + launchedYear + ", researchArea="
				+ researchArea + ", researchAreaDescription=" + researchAreaDescription + ", researchSubArea="
				+ researchSubArea + ", stocksFundsIssuesCovered=" + stocksFundsIssuesCovered + ", accessbility="
				+ accessbility + ", suitability=" + suitability + ", subCostPy=" + subCostPy + ", repFormat="
				+ repFormat + ", rsrchReportFor=" + rsrchReportFor + ", repDate=" + repDate + ", rsrchReportAccess="
				+ rsrchReportAccess + ", rsrchRecommType=" + rsrchRecommType + ", rsrchReportDesc=" + rsrchReportDesc
				+ ", rsrchUploadReport=" + rsrchUploadReport + ", targetPrice=" + targetPrice + ", analystName="
				+ analystName + ", analystAwards=" + analystAwards + ", anaystCfaCharter=" + anaystCfaCharter + "]";
	}
	private String productDescription;	
	private String launchedYear;	
	private int researchArea;
	private String researchAreaDescription;
	private String researchSubArea;
	private String stocksFundsIssuesCovered;
/*	private String regionsCovered;
	private String countriesCovered;
	private int totalAnalyst;
	private String existingClientBase;*/
	private String accessbility;
	private String suitability;
/*	private String costType;*/
/*	private float subCostPm;*/
	private float subCostPy;
	private String repFormat;
	private String rsrchReportFor;
	private String repDate;
	private String rsrchReportAccess;
	private String rsrchRecommType;
	private String priceAtRecomm;
	private String rsrchReportDesc;
	private String rsrchUploadReport;
	private String targetPrice;

public String getRsrchReportDesc() {
		return rsrchReportDesc;
	}
	public void setRsrchReportDesc(String rsrchReportDesc) {
		this.rsrchReportDesc = rsrchReportDesc;
	}
	public String getRsrchUploadReport() {
		return rsrchUploadReport;
	}
	public void setRsrchUploadReport(String rsrchUploadReport) {
		this.rsrchUploadReport = rsrchUploadReport;
	}
	public String getTargetPrice() {
		return targetPrice;
	}
	public void setTargetPrice(String targetPrice) {
		this.targetPrice = targetPrice;
	}
public String getRsrchReportFor() {
		return rsrchReportFor;
	}
	public void setRsrchReportFor(String rsrchReportFor) {
		this.rsrchReportFor = rsrchReportFor;
	}
	public String getRepDate() {
		return repDate;
	}
	public void setRepDate(String repDate) {
		this.repDate = repDate;
	}
	public String getRsrchReportAccess() {
		return rsrchReportAccess;
	}
	public void setRsrchReportAccess(String rsrchReportAccess) {
		this.rsrchReportAccess = rsrchReportAccess;
	}
	public String getRsrchRecommType() {
		return rsrchRecommType;
	}
	public void setRsrchRecommType(String rsrchRecommType) {
		this.rsrchRecommType = rsrchRecommType;
	}
	/*	private String resPeriodMon;
	private String resPeriodYear;*/
	private String analystName;
	/*private int analystRegion;
	private String analystRegionDescription;
	private int analystCountry;
	private String analystCountryDescription;
	private String analystYearOfExp;*/
	private String analystAwards;
	private String anaystCfaCharter;
	
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
	public int getResearchArea() {
		return researchArea;
	}
	public void setResearchArea(int researchArea) {
		this.researchArea = researchArea;
	}
	public String getResearchAreaDescription() {
		return researchAreaDescription;
	}
	public void setResearchAreaDescription(String researchAreaDescription) {
		this.researchAreaDescription = researchAreaDescription;
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
	/*public String getRegionsCovered() {
		return regionsCovered;
	}
	public void setRegionsCovered(String regionsCovered) {
		this.regionsCovered = regionsCovered;
	}
	public String getCountriesCovered() {
		return countriesCovered;
	}
	public void setCountriesCovered(String countriesCovered) {
		this.countriesCovered = countriesCovered;
	}
	public int getTotalAnalyst() {
		return totalAnalyst;
	}
	public void setTotalAnalyst(int totalAnalyst) {
		this.totalAnalyst = totalAnalyst;
	}
	public String getExistingClientBase() {
		return existingClientBase;
	}
	public void setExistingClientBase(String existingClientBase) {
		this.existingClientBase = existingClientBase;
	}*/
	public String getAccessbility() {
		return accessbility;
	}
	public void setAccessbility(String accessbility) {
		this.accessbility = accessbility;
	}
	public String getSuitability() {
		return suitability;
	}
	public void setSuitability(String suitability) {
		this.suitability = suitability;
	}
	/*public String getCostType() {
		return costType;
	}
	public void setCostType(String costType) {
		this.costType = costType;
	}
	public float getSubCostPm() {
		return subCostPm;
	}
	public void setSubCostPm(float subCostPm) {
		this.subCostPm = subCostPm;
	}*/
	public float getSubCostPy() {
		return subCostPy;
	}
	public void setSubCostPy(float subCostPy) {
		this.subCostPy = subCostPy;
	}
	public String getRepFormat() {
		return repFormat;
	}
	public void setRepFormat(String repFormat) {
		this.repFormat = repFormat;
	}
	/*public String getResPeriodMon() {
		return resPeriodMon;
	}
	public void setResPeriodMon(String resPeriodMon) {
		this.resPeriodMon = resPeriodMon;
	}
	public String getResPeriodYear() {
		return resPeriodYear;
	}
	public void setResPeriodYear(String resPeriodYear) {
		this.resPeriodYear = resPeriodYear;
	}*/
	public String getAnalystName() {
		return analystName;
	}
	public void setAnalystName(String analystName) {
		this.analystName = analystName;
	}
	/*public int getAnalystRegion() {
		return analystRegion;
	}
	public void setAnalystRegion(int analystRegion) {
		this.analystRegion = analystRegion;
	}
	public String getAnalystRegionDescription() {
		return analystRegionDescription;
	}
	public void setAnalystRegionDescription(String analystRegionDescription) {
		this.analystRegionDescription = analystRegionDescription;
	}
	public int getAnalystCountry() {
		return analystCountry;
	}
	public void setAnalystCountry(int analystCountry) {
		this.analystCountry = analystCountry;
	}
	public String getAnalystCountryDescription() {
		return analystCountryDescription;
	}
	public void setAnalystCountryDescription(String analystCountryDescription) {
		this.analystCountryDescription = analystCountryDescription;
	}
	public String getAnalystYearOfExp() {
		return analystYearOfExp;
	}
	public void setAnalystYearOfExp(String analystYearOfExp) {
		this.analystYearOfExp = analystYearOfExp;
	}*/
	public String getAnalystAwards() {
		return analystAwards;
	}
	public void setAnalystAwards(String analystAwards) {
		this.analystAwards = analystAwards;
	}
	public String getAnaystCfaCharter() {
		return anaystCfaCharter;
	}
	public void setAnaystCfaCharter(String anaystCfaCharter) {
		this.anaystCfaCharter = anaystCfaCharter;
	}
	public String getPriceAtRecomm() {
		return priceAtRecomm;
	}
	public void setPriceAtRecomm(String priceAtRecomm) {
		this.priceAtRecomm = priceAtRecomm;
	}
	
	
}
