/**
 * 
 */
package com.finvendor.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tradingapplicationvendor_search")
public class ResearchReportProvidersVendorSearch implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="tradingapplicationvendor_search_id")
    @GeneratedValue
    private String researchArea;
    private String researchSubArea;
    private String regionsCovered;
    private String researchApplicableYear;
    private String researchApplicableMonth;
    private String analystRegionofIncorp;
    private String analystCountryofIncorp;
    private String researchPrebyCFA;
    private String analystYearofExp;
    private String analystProfileFreshness;
    private String analystSupportCoverageRegion;
    private String searchKeywords;
    private String dataAttribute;
    private String analystSupportCoverageTime;
    private String analystAwards;
    private String costRange;
    private String existingUserBase;
	private String userBase;
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
	public String getRegionsCovered() {
		return regionsCovered;
	}
	public void setRegionsCovered(String regionsCovered) {
		this.regionsCovered = regionsCovered;
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
	public String getResearchPrebyCFA() {
		return researchPrebyCFA;
	}
	public void setResearchPrebyCFA(String researchPrebyCFA) {
		this.researchPrebyCFA = researchPrebyCFA;
	}
	public String getAnalystYearofExp() {
		return analystYearofExp;
	}
	public void setAnalystYearofExp(String analystYearofExp) {
		this.analystYearofExp = analystYearofExp;
	}
	public String getAnalystProfileFreshness() {
		return analystProfileFreshness;
	}
	public void setAnalystProfileFreshness(String analystProfileFreshness) {
		this.analystProfileFreshness = analystProfileFreshness;
	}
	public String getAnalystSupportCoverageRegion() {
		return analystSupportCoverageRegion;
	}
	public void setAnalystSupportCoverageRegion(String analystSupportCoverageRegion) {
		this.analystSupportCoverageRegion = analystSupportCoverageRegion;
	}
	public String getSearchKeywords() {
		return searchKeywords;
	}
	public void setSearchKeywords(String searchKeywords) {
		this.searchKeywords = searchKeywords;
	}
	public String getDataAttribute() {
		return dataAttribute;
	}
	public void setDataAttribute(String dataAttribute) {
		this.dataAttribute = dataAttribute;
	}
	public String getAnalystSupportCoverageTime() {
		return analystSupportCoverageTime;
	}
	public void setAnalystSupportCoverageTime(String analystSupportCoverageTime) {
		this.analystSupportCoverageTime = analystSupportCoverageTime;
	}
	public String getAnalystAwards() {
		return analystAwards;
	}
	public void setAnalystAwards(String analystAwards) {
		this.analystAwards = analystAwards;
	}
	public String getCostRange() {
		return costRange;
	}
	public void setCostRange(String costRange) {
		this.costRange = costRange;
	}
	public String getExistingUserBase() {
		return existingUserBase;
	}
	public void setExistingUserBase(String existingUserBase) {
		this.existingUserBase = existingUserBase;
	}
	public String getUserBase() {
		return userBase;
	}
	public void setUserBase(String userBase) {
		this.userBase = userBase;
	}
	
	
}
