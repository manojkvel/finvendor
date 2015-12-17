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
@Table(name="vendor_support")
public class VendorSupport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	@Id
    @Column(name="vs_id")
	@GeneratedValue
    private Integer vs_id;

	@ManyToOne(targetEntity=Vendor.class,fetch=FetchType.LAZY)
	@JoinColumn(name="vendor_id", nullable=false)
	private Vendor vendor;

	@ManyToOne(targetEntity=VendorSolution.class,fetch=FetchType.LAZY)
	@JoinColumn(name="vendor_solution_id", nullable=false)
	private VendorSolution vendorSolution;
	
	@ManyToOne(targetEntity=Support.class,fetch=FetchType.LAZY)
	@JoinColumn(name="support_id", nullable=false)
	private  Support support;
 

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
	 * @return the vendorSolution
	 */
	public VendorSolution getVendorSolution() {
		return vendorSolution;
	}

	/**
	 * @param vendorSolution the vendorSolution to set
	 */
	public void setVendorSolution(VendorSolution vendorSolution) {
		this.vendorSolution = vendorSolution;
	}

	/**
	 * @return the support
	 */
	public Support getSupport() {
		return support;
	}

	/**
	 * @param support the support to set
	 */
	public void setSupport(Support support) {
		this.support = support;
	}

	/**
	 * @return the vs_id
	 */
	public Integer getVs_id() {
		return vs_id;
	}

	/**
	 * @param vs_id the vs_id to set
	 */
	public void setVs_id(Integer vs_id) {
		this.vs_id = vs_id;
	}
	
	
	
	 
 	
}
