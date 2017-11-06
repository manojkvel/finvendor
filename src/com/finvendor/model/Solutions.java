/**
 * 
 */
package com.finvendor.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author rayulu vemula
 *
 */
@Entity
@Table(name="solution")
public class Solutions implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="solution_id")
    @GeneratedValue
    private Integer solution_id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
   
	@ManyToOne(targetEntity=Vendor.class,fetch=FetchType.EAGER)
	@JoinColumn(name="vendor_id", nullable=false)
	private Vendor vendor;
	
	@ManyToOne(targetEntity=SolutionTypes.class,fetch=FetchType.EAGER)
	@JoinColumn(name="solution_type_id", nullable=false)
	private SolutionTypes solutionTypes;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="solutions",cascade = CascadeType.ALL)
	private Set<VendorOffering> vendorOffering = null;

	@OneToMany(fetch=FetchType.LAZY,mappedBy="solution")
	private Set<VendorDistribution> vendorDistributions=new HashSet<VendorDistribution>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="solution")
	private Set<VendorDataCoverage> vendorDataCoverage =new HashSet<VendorDataCoverage>();
	
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="solution")
	private Set<VendorTradingSoftwareDetails> vendorTradingSoftwareDetails =new HashSet<VendorTradingSoftwareDetails>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="solution")
	private Set<VendorResearchDetails> vendorResearchDetails =new HashSet<VendorResearchDetails>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="solution")
	private Set<VendorResearchCoverage> vendorResearchCoverage =new HashSet<VendorResearchCoverage>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="solution")
	private Set<VendorAnalystProfile> vendorAnalystProfile =new HashSet<VendorAnalystProfile>();
	
	
	
	
	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public Set<VendorAnalystProfile> getVendorAnalystProfile() {
		return vendorAnalystProfile;
	}

	public void setVendorAnalystProfile(Set<VendorAnalystProfile> vendorAnalystProfile) {
		this.vendorAnalystProfile = vendorAnalystProfile;
	}

	public Set<VendorTradingSoftwareDetails> getVendorTradingSoftwareDetails() {
		return vendorTradingSoftwareDetails;
	}

	public void setVendorTradingSoftwareDetails(Set<VendorTradingSoftwareDetails> vendorTradingSoftwareDetails) {
		this.vendorTradingSoftwareDetails = vendorTradingSoftwareDetails;
	}

	public Set<VendorResearchDetails> getVendorResearchDetails() {
		return vendorResearchDetails;
	}

	public void setVendorResearchDetails(Set<VendorResearchDetails> vendorResearchDetails) {
		this.vendorResearchDetails = vendorResearchDetails;
	}

	public Set<VendorResearchCoverage> getVendorResearchCoverage() {
		return vendorResearchCoverage;
	}

	public void setVendorResearchCoverage(Set<VendorResearchCoverage> vendorResearchCoverage) {
		this.vendorResearchCoverage = vendorResearchCoverage;
	}

	public Set<VendorDistribution> getVendorDistributions() {
		return vendorDistributions;
	}

	public void setVendorDistributions(Set<VendorDistribution> vendorDistributions) {
		this.vendorDistributions = vendorDistributions;
	}

	public Set<VendorDataCoverage> getVendorDataCoverage() {
		return vendorDataCoverage;
	}

	public void setVendorDataCoverage(Set<VendorDataCoverage> vendorDataCoverage) {
		this.vendorDataCoverage = vendorDataCoverage;
	}

	/**
	 * @return the solution_id
	 */
	public Integer getSolution_id() {
		return solution_id;
	}

	/**
	 * @param solution_id the solution_id to set
	 */
	public void setSolution_id(Integer solution_id) {
		this.solution_id = solution_id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the solutionTypes
	 */
	public SolutionTypes getSolutionTypes() {
		return solutionTypes;
	}

	/**
	 * @param solutionTypes the solutionTypes to set
	 */
	public void setSolutionTypes(SolutionTypes solutionTypes) {
		this.solutionTypes = solutionTypes;
	}

	public Set<VendorOffering> getVendorOffering() {
		return vendorOffering;
	}

	public void setVendorOffering(Set<VendorOffering> vendorOffering) {
		this.vendorOffering = vendorOffering;
	}
}
