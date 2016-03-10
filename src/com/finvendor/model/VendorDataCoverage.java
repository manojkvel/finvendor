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
@Table(name="vendor_datacoverage")
public class VendorDataCoverage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="dataCoverage_id")
	@GeneratedValue
    private Integer dataCoverageId;
	
	
	@ManyToOne(targetEntity=Vendor.class,fetch=FetchType.LAZY)
	@JoinColumn(name="vendor_id", nullable=false)
	private Vendor vendor;
	
	@ManyToOne(targetEntity=Solutions.class,fetch=FetchType.EAGER)
	@JoinColumn(name="solution_id", nullable=false)
	private Solutions solution;
	
	@ManyToOne(targetEntity=VendorOffering.class,fetch=FetchType.EAGER)
	@JoinColumn(name="vendor_offering_id", nullable=false)
	private VendorOffering vendorOffering;
	
	@Column(name="region_ids")
	private  String region;
	@Column(name="country_ids")
	private  String country;
	@Column(name="cost_ids")
	private  String cost;
	
	@Column(name="phoneno")
	private String phoneNo;
	@Column(name="email")
	private String email;
	
	@Column(name="coverageexchange")
	private String coverageexchange;
	
	
	public String getCoverageexchange() {
		return coverageexchange;
	}
	public void setCoverageexchange(String coverageexchange) {
		this.coverageexchange = coverageexchange;
	}
	public Integer getDataCoverageId() {
		return dataCoverageId;
	}
	public void setDataCoverageId(Integer dataCoverageId) {
		this.dataCoverageId = dataCoverageId;
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
	public void setVendorOffering(VendorOffering vendorOffering) {
		this.vendorOffering = vendorOffering;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
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
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	}
