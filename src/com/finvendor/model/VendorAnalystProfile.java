<<<<<<< HEAD
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
@Table(name="vendor_analystprofile")
public class VendorAnalystProfile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="AnalystProfile_id")
	@GeneratedValue
    private Integer analystProfileId;
	
	
	@ManyToOne(targetEntity=Vendor.class,fetch=FetchType.LAZY)
	@JoinColumn(name="vendor_id", nullable=false)
	private Vendor vendor;
	
	@ManyToOne(targetEntity=Solutions.class,fetch=FetchType.EAGER)
	@JoinColumn(name="solution_id", nullable=false)
	private Solutions solution;
	

	 @Column(name="offering")
private String offering;
	 @Column(name="OfferingDesc")
	 private String offeringDesc;
	 @Column(name="ResearchArea")
private String researchArea;
	 @Column(name="ResearchSubArea")
private String researchSubArea;
	 @Column(name="AnalystName")
private String analystName;
	 @Column(name="ResearchAnalystWithCFA")
private Boolean researchAnalystWithCFA;
	 @Column(name="AnalystRegionofIncorp")
private String analystRegionofIncorp;
	 @Column(name="AnalystCountryofIncorp")
private String analystCountryofIncorp;
	 @Column(name="AnalystYearofExp")
private String analystYearofExp;
	 @Column(name="AnalystAwards")
private String analystAwards;
	
	
	 
	 
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
	public Integer getAnalystProfileId() {
		return analystProfileId;
	}
	public void setAnalystProfileId(Integer analystProfileId) {
		this.analystProfileId = analystProfileId;
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
	
	
	public Boolean getResearchAnalystWithCFA() {
		return researchAnalystWithCFA;
	}
	public void setResearchAnalystWithCFA(Boolean researchAnalystWithCFA) {
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
	
	
	}
=======
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
@Table(name="vendor_analystprofile")
public class VendorAnalystProfile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="AnalystProfile_id")
	@GeneratedValue
    private Integer analystProfileId;
	
	
	@ManyToOne(targetEntity=Vendor.class,fetch=FetchType.LAZY)
	@JoinColumn(name="vendor_id", nullable=false)
	private Vendor vendor;
	
	@ManyToOne(targetEntity=Solutions.class,fetch=FetchType.EAGER)
	@JoinColumn(name="solution_id", nullable=false)
	private Solutions solution;
	

	 @Column(name="offering")
private String offering;
	 @Column(name="OfferingDesc")
	 private String offeringDesc;
	 @Column(name="ResearchArea")
private String researchArea;
	 @Column(name="ResearchSubArea")
private String researchSubArea;
	 @Column(name="AnalystName")
private String analystName;
	 @Column(name="ResearchAnalystWithCFA")
private Boolean researchAnalystWithCFA;
	 @Column(name="AnalystRegionofIncorp")
private String analystRegionofIncorp;
	 @Column(name="AnalystCountryofIncorp")
private String analystCountryofIncorp;
	 @Column(name="AnalystYearofExp")
private String analystYearofExp;
	 @Column(name="AnalystAwards")
private String analystAwards;
	
	
	 
	 
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
	public Integer getAnalystProfileId() {
		return analystProfileId;
	}
	public void setAnalystProfileId(Integer analystProfileId) {
		this.analystProfileId = analystProfileId;
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
	
	
	public Boolean getResearchAnalystWithCFA() {
		return researchAnalystWithCFA;
	}
	public void setResearchAnalystWithCFA(Boolean researchAnalystWithCFA) {
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
	
	
	}
>>>>>>> origin/master
