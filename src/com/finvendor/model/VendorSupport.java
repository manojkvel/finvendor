<<<<<<< HEAD
/**
 * 
 */
package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


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

	@OneToOne
	@JoinColumn(name="id")
	private Vendor vendor;

	@ManyToOne(targetEntity=VendorSolution.class,fetch=FetchType.LAZY)
	@JoinColumn(name="vendor_solution_id")
	private VendorSolution vendorSolution;
	
	@ManyToOne(targetEntity=Support.class,fetch=FetchType.LAZY)
	@JoinColumn(name="support_id")
	private  Support support;
    
	@Column(name="c24by7")
	private Boolean c24by7;
	@Column(name="c24by6")
	private Boolean c24by6;
	@Column(name="c16by7")
	private Boolean c16by7;
	@Column(name="c16by6")
	private Boolean c16by6;
	@Column(name="c16by5")
	private Boolean c16by5;
	@Column(name="c8by7")
	private Boolean c8by7;
	@Column(name="c8by6")
	private Boolean c8by6;
	@Column(name="c8by5")
	private Boolean c8by5;
	@Column(name="cWeekend")
	private Boolean cWeekend;
	@Column(name="publicholidays")
	private Boolean publicholidays; 
	
	public Boolean getC24by7() {
		return c24by7;
	}

	public void setC24by7(Boolean c24by7) {
		this.c24by7 = c24by7;
	}

	public Boolean getC24by6() {
		return c24by6;
	}

	public void setC24by6(Boolean c24by6) {
		this.c24by6 = c24by6;
	}

	public Boolean getC16by7() {
		return c16by7;
	}

	public void setC16by7(Boolean c16by7) {
		this.c16by7 = c16by7;
	}

	public Boolean getC16by6() {
		return c16by6;
	}

	public void setC16by6(Boolean c16by6) {
		this.c16by6 = c16by6;
	}

	public Boolean getC16by5() {
		return c16by5;
	}

	public void setC16by5(Boolean c16by5) {
		this.c16by5 = c16by5;
	}

	public Boolean getC8by7() {
		return c8by7;
	}

	public void setC8by7(Boolean c8by7) {
		this.c8by7 = c8by7;
	}

	public Boolean getC8by6() {
		return c8by6;
	}

	public void setC8by6(Boolean c8by6) {
		this.c8by6 = c8by6;
	}

	public Boolean getC8by5() {
		return c8by5;
	}

	public void setC8by5(Boolean c8by5) {
		this.c8by5 = c8by5;
	}

	public Boolean getcWeekend() {
		return cWeekend;
	}

	public void setcWeekend(Boolean cWeekend) {
		this.cWeekend = cWeekend;
	}

	public Boolean getPublicholidays() {
		return publicholidays;
	}

	public void setPublicholidays(Boolean publicholidays) {
		this.publicholidays = publicholidays;
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
=======
/**
 * 
 */
package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


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

	@OneToOne
	@JoinColumn(name="id")
	private Vendor vendor;

	@ManyToOne(targetEntity=VendorSolution.class,fetch=FetchType.LAZY)
	@JoinColumn(name="vendor_solution_id")
	private VendorSolution vendorSolution;
	
	@ManyToOne(targetEntity=Support.class,fetch=FetchType.LAZY)
	@JoinColumn(name="support_id")
	private  Support support;
    
	@Column(name="c24by7")
	private Boolean c24by7;
	@Column(name="c24by6")
	private Boolean c24by6;
	@Column(name="c16by7")
	private Boolean c16by7;
	@Column(name="c16by6")
	private Boolean c16by6;
	@Column(name="c16by5")
	private Boolean c16by5;
	@Column(name="c8by7")
	private Boolean c8by7;
	@Column(name="c8by6")
	private Boolean c8by6;
	@Column(name="c8by5")
	private Boolean c8by5;
	@Column(name="cWeekend")
	private Boolean cWeekend;
	@Column(name="publicholidays")
	private Boolean publicholidays; 
	
	public Boolean getC24by7() {
		return c24by7;
	}

	public void setC24by7(Boolean c24by7) {
		this.c24by7 = c24by7;
	}

	public Boolean getC24by6() {
		return c24by6;
	}

	public void setC24by6(Boolean c24by6) {
		this.c24by6 = c24by6;
	}

	public Boolean getC16by7() {
		return c16by7;
	}

	public void setC16by7(Boolean c16by7) {
		this.c16by7 = c16by7;
	}

	public Boolean getC16by6() {
		return c16by6;
	}

	public void setC16by6(Boolean c16by6) {
		this.c16by6 = c16by6;
	}

	public Boolean getC16by5() {
		return c16by5;
	}

	public void setC16by5(Boolean c16by5) {
		this.c16by5 = c16by5;
	}

	public Boolean getC8by7() {
		return c8by7;
	}

	public void setC8by7(Boolean c8by7) {
		this.c8by7 = c8by7;
	}

	public Boolean getC8by6() {
		return c8by6;
	}

	public void setC8by6(Boolean c8by6) {
		this.c8by6 = c8by6;
	}

	public Boolean getC8by5() {
		return c8by5;
	}

	public void setC8by5(Boolean c8by5) {
		this.c8by5 = c8by5;
	}

	public Boolean getcWeekend() {
		return cWeekend;
	}

	public void setcWeekend(Boolean cWeekend) {
		this.cWeekend = cWeekend;
	}

	public Boolean getPublicholidays() {
		return publicholidays;
	}

	public void setPublicholidays(Boolean publicholidays) {
		this.publicholidays = publicholidays;
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
>>>>>>> origin/master
