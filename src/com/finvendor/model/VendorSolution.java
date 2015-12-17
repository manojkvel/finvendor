/**
 * 
 */
package com.finvendor.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
@Table(name="vendor_solution")
public class VendorSolution implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="vendor_solution_id")
	@GeneratedValue
    private Integer vendor_solution_id;
	
	@ManyToOne(targetEntity=Vendor.class,fetch=FetchType.LAZY)
	@JoinColumn(name="vendor_id", nullable=false)
	private Vendor vendor;
	
	@ManyToOne(targetEntity=Solutions.class,fetch=FetchType.LAZY)
	@JoinColumn(name="solution_id", nullable=false)
	private Solutions solutions;
	
	@ManyToOne(targetEntity=Cost.class,fetch=FetchType.LAZY)
	@JoinColumn(name="cost_id", nullable=false)
	private Cost cost;

	@OneToMany(fetch=FetchType.LAZY,mappedBy="vendorSolution")
	private Set<VendorDistribution> vendorDistributions=new HashSet<VendorDistribution>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="vendorSolution")
	private Set<VendorSupport> vendorSupports=new HashSet<VendorSupport>();
	
	 

	/**
	 * @return the vendor
	 */
	public Vendor getVendor() {
		return vendor;
	}

	/**
	 * @param vendor the vendor to set
	 */
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	/**
	 * @return the solutions
	 */
	public Solutions getSolutions() {
		return solutions;
	}

	/**
	 * @param solutions the solutions to set
	 */
	public void setSolutions(Solutions solutions) {
		this.solutions = solutions;
	}

	/**
	 * @return the cost
	 */
	public Cost getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(Cost cost) {
		this.cost = cost;
	}

	/**
	 * @return the vendorDistributions
	 */
	public Set<VendorDistribution> getVendorDistributions() {
		return vendorDistributions;
	}

	/**
	 * @param vendorDistributions the vendorDistributions to set
	 */
	public void setVendorDistributions(Set<VendorDistribution> vendorDistributions) {
		this.vendorDistributions = vendorDistributions;
	}

	/**
	 * @return the vendorSupports
	 */
	public Set<VendorSupport> getVendorSupports() {
		return vendorSupports;
	}

	/**
	 * @param vendorSupports the vendorSupports to set
	 */
	public void setVendorSupports(Set<VendorSupport> vendorSupports) {
		this.vendorSupports = vendorSupports;
	}

	/**
	 * @return the vendor_solution_id
	 */
	public Integer getVendor_solution_id() {
		return vendor_solution_id;
	}

	/**
	 * @param vendor_solution_id the vendor_solution_id to set
	 */
	public void setVendor_solution_id(Integer vendor_solution_id) {
		this.vendor_solution_id = vendor_solution_id;
	}
}
