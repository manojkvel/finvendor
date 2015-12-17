/**
 * 
 */
package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author rayulu vemula
 *
 */
@Entity
@Table(name="vendor_distribution")
public class VendorDistribution implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="vendor_distribution_id")
    private String vendor_distribution_id;
	
	@ManyToOne(targetEntity=DistributionMode.class,fetch=FetchType.LAZY)
	@JoinColumn(name="distribution_mode_id", nullable=false)
	private DistributionMode distributionMode;
	
	@ManyToOne(targetEntity=VendorSolution.class,fetch=FetchType.LAZY)
	@JoinColumn(name="vendor_solution_id", nullable=false)
	private VendorSolution vendorSolution;
	
	@ManyToOne(targetEntity=Vendor.class,fetch=FetchType.LAZY)
	@JoinColumn(name="vendor_id", nullable=false)
	private Vendor vendor;

	
	/**
	 * @return the vendor_distribution_id
	 */
	public String getVendor_distribution_id() {
		return vendor_distribution_id;
	}

	/**
	 * @param vendor_distribution_id the vendor_distribution_id to set
	 */
	public void setVendor_distribution_id(String vendor_distribution_id) {
		this.vendor_distribution_id = vendor_distribution_id;
	}

	/**
	 * @return the distributionMode
	 */
	public DistributionMode getDistributionMode() {
		return distributionMode;
	}

	/**
	 * @param distributionMode the distributionMode to set
	 */
	public void setDistributionMode(DistributionMode distributionMode) {
		this.distributionMode = distributionMode;
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
    
}
