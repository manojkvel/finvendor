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
@Table(name="vendor_awards")
public class VendorAwardsMap implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="va_id")
    @GeneratedValue
    private Integer va_id;
	
	@Column(name="Awardname")
	private String awardname;
   
	@Column(name="Awardsponsor")
	private String awardsponsor;
	
	@Column(name="Awardedyear")
	private String awardedyear;
	
	@ManyToOne(targetEntity=Vendor.class,fetch=FetchType.LAZY)
	@JoinColumn(name="vendor_id", nullable=false)
	private Vendor vendor;

	
	
	/**
	 * @return the va_id
	 */
	public Integer getVa_id() {
		return va_id;
	}

	/**
	 * @param va_id the va_id to set
	 */
	public void setVa_id(Integer va_id) {
		this.va_id = va_id;
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

	public String getAwardname() {
		return awardname;
	}

	public void setAwardname(String awardname) {
		this.awardname = awardname;
	}

	public String getAwardsponsor() {
		return awardsponsor;
	}

	public void setAwardsponsor(String awardsponsor) {
		this.awardsponsor = awardsponsor;
	}

	public String getAwardedyear() {
		return awardedyear;
	}

	public void setAwardedyear(String awardedyear) {
		this.awardedyear = awardedyear;
	}
	
	
}
