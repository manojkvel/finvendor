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
@Table(name="vendor_awards_map")
public class VendorAwardsMap implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="va_id")
    @GeneratedValue
    private Integer va_id;
	
	@Column(name="yearofaward")
	private String yearofaward;
   
	@Column(name="awardedfor")
	private String awardedfor;
	
	@Column(name="position")
	private String position;
	
	@ManyToOne(targetEntity=Awards.class,fetch=FetchType.LAZY)
	@JoinColumn(name="award_id", nullable=false)
	private Awards awards;
	
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
	 * @return the yearofaward
	 */
	public String getYearofaward() {
		return yearofaward;
	}

	/**
	 * @param yearofaward the yearofaward to set
	 */
	public void setYearofaward(String yearofaward) {
		this.yearofaward = yearofaward;
	}

	/**
	 * @return the awardedfor
	 */
	public String getAwardedfor() {
		return awardedfor;
	}

	/**
	 * @param awardedfor the awardedfor to set
	 */
	public void setAwardedfor(String awardedfor) {
		this.awardedfor = awardedfor;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the awards
	 */
	public Awards getAwards() {
		return awards;
	}

	/**
	 * @param awards the awards to set
	 */
	public void setAwards(Awards awards) {
		this.awards = awards;
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
