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
@Table(name="vendor_region_country_exchange_map")
public class VendorRegionCountryExchangeMap implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="vrc_id")
    @GeneratedValue
    private Integer vrc_id;
   
	@ManyToOne(targetEntity=Vendor.class,fetch=FetchType.LAZY)
	@JoinColumn(name="vendor_id", nullable=false)
	private Vendor vendor;
	
	@ManyToOne(targetEntity=Region.class,fetch=FetchType.LAZY)
	@JoinColumn(name="region_id", nullable=false)
	private Region region;

	@ManyToOne(targetEntity=Country.class,fetch=FetchType.LAZY)
	@JoinColumn(name="country_id", nullable=false)
	private Country country;
	

	@ManyToOne(targetEntity=Exchange.class,fetch=FetchType.LAZY)
	@JoinColumn(name="exchange_id", nullable=false)
	private Exchange exchange;
	

	/**
	 * @return the vrc_id
	 */
	public Integer getVrc_id() {
		return vrc_id;
	}

	/**
	 * @param vrc_id the vrc_id to set
	 */
	public void setVrc_id(Integer vrc_id) {
		this.vrc_id = vrc_id;
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
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(Region region) {
		this.region = region;
	}

	/**
	 * @return the exchange
	 */
	public Exchange getExchange() {
		return exchange;
	}

	/**
	 * @param exchange the exchange to set
	 */
	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}
}
