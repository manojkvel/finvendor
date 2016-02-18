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
@Table(name="vendor_offering")
public class VendorOffering  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="vendor_offering_id")
	@GeneratedValue
    private Integer vendor_offering_id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;

	@ManyToOne(targetEntity=Vendor.class,fetch=FetchType.LAZY)
	@JoinColumn(name="vendor_id", nullable=false)
	private Vendor vendor;
	
	@ManyToOne(targetEntity=AssetClass.class,fetch=FetchType.EAGER)
	@JoinColumn(name="asset_class_id", nullable=false)
	private AssetClass assetClass;
	
	@ManyToOne(targetEntity=SecurityType.class,fetch=FetchType.LAZY)
	@JoinColumn(name="security_type_id", nullable=true)
	private SecurityType securityType;
	
	@ManyToOne(targetEntity=Solutions.class,fetch=FetchType.EAGER)
	@JoinColumn(name="solution_id", nullable=false)
	private Solutions solutions;
	
	
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="vendorOffering",cascade = CascadeType.ALL)
	private Set<OfferingFiles> offeringFiles = new HashSet<OfferingFiles>();
	

	public Set<OfferingFiles> getOfferingFiles() {
		return offeringFiles;
	}

	public void setOfferingFiles(Set<OfferingFiles> offeringFiles) {
		this.offeringFiles = offeringFiles;
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	 * @return the securityType
	 */
	public SecurityType getSecurityType() {
		return securityType;
	}

	/**
	 * @param securityType the securityType to set
	 */
	public void setSecurityType(SecurityType securityType) {
		this.securityType = securityType;
	}

	/**
	 * @return the assetClass
	 */
	public AssetClass getAssetClass() {
		return assetClass;
	}

	/**
	 * @param assetClass the assetClass to set
	 */
	public void setAssetClass(AssetClass assetClass) {
		this.assetClass = assetClass;
	}

	/**
	 * @return the vendor_offering_id
	 */
	public Integer getVendor_offering_id() {
		return vendor_offering_id;
	}

	/**
	 * @param vendor_offering_id the vendor_offering_id to set
	 */
	public void setVendor_offering_id(Integer vendor_offering_id) {
		this.vendor_offering_id = vendor_offering_id;
	}
	
	

}
