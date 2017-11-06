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
@Table(name="vendor_distribution")
public class VendorDistribution implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="Vendor_Distribution_Id")
	@GeneratedValue
    private Integer vendorDistributionId;
	
	
	@ManyToOne(targetEntity=Vendor.class,fetch=FetchType.LAZY)
	@JoinColumn(name="vendor_id", nullable=false)
	private Vendor vendor;
	
	@ManyToOne(targetEntity=Solutions.class,fetch=FetchType.EAGER)
	@JoinColumn(name="solution_id", nullable=false)
	private Solutions solution;
	
	@ManyToOne(targetEntity=VendorOffering.class,fetch=FetchType.EAGER)
	@JoinColumn(name="vendor_offering_id", nullable=false)
	private VendorOffering vendorOffering;
	
	@ManyToOne(targetEntity=OfferingFiles.class,fetch=FetchType.EAGER)
	@JoinColumn(name="offeringFilesId", nullable=false)
	private OfferingFiles offeringFiles;
	
	
	@Column(name="region_ids")
	private  String region;
	
	@Column(name="country_ids")
	private  String country;
	
	@Column(name="exchanges")
	private  String exchange;
	
	@Column(name="frequency")
	private String frequency;
	
	@Column(name="distributionmethod")
	private String distributionmethod;
	
	@Column(name="feedtype")
	private String feedtype;
	
	@Column(name="feedsubtype")
	private String feedsubtype;
	

	public Integer getVendorDistributionId() {
		return vendorDistributionId;
	}
	public void setVendorDistributionId(Integer vendorDistributionId) {
		this.vendorDistributionId = vendorDistributionId;
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
	public VendorOffering getVendorOffering() {
		return vendorOffering;
	}
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public void setVendorOffering(VendorOffering vendorOffering) {
		this.vendorOffering = vendorOffering;
	}
	
	public OfferingFiles getOfferingFiles() {
		return offeringFiles;
	}
	public void setOfferingFiles(OfferingFiles offeringFiles) {
		this.offeringFiles = offeringFiles;
	}
	
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getDistributionmethod() {
		return distributionmethod;
	}
	public void setDistributionmethod(String distributionmethod) {
		this.distributionmethod = distributionmethod;
	}
	public String getFeedtype() {
		return feedtype;
	}
	public void setFeedtype(String feedtype) {
		this.feedtype = feedtype;
	}
	public String getFeedsubtype() {
		return feedsubtype;
	}
	public void setFeedsubtype(String feedsubtype) {
		this.feedsubtype = feedsubtype;
	}
		
	
	
	}
