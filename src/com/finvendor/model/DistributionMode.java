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
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author rayulu vemula
 *
 */
@Entity
@Table(name="distribution_mode")
public class DistributionMode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="distribution_mode_id")
    private String distribution_mode_id;
	
    @Column(name="name")
    private String name;
    
    @OneToMany(fetch=FetchType.LAZY,mappedBy="distributionMode")
	private Set<VendorDistribution> vendorDistributions=new HashSet<VendorDistribution>();

    
	/**
	 * @return the distribution_mode_id
	 */
	public String getDistribution_mode_id() {
		return distribution_mode_id;
	}

	/**
	 * @param distribution_mode_id the distribution_mode_id to set
	 */
	public void setDistribution_mode_id(String distribution_mode_id) {
		this.distribution_mode_id = distribution_mode_id;
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
    
    
}
