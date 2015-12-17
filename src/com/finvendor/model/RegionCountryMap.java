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
@Table(name="region_country_map")
public class RegionCountryMap implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="rc_id")
    @GeneratedValue
    private Integer rc_id;
	
	@ManyToOne(targetEntity=Region.class,fetch=FetchType.LAZY)
	@JoinColumn(name="region_id", nullable=false)
	private Region region;
	
	@ManyToOne(targetEntity=Country.class,fetch=FetchType.LAZY)
	@JoinColumn(name="country_id", nullable=false)
	private Country country;
	
	

	/**
	 * @return the rc_id
	 */
	public Integer getRc_id() {
		return rc_id;
	}

	/**
	 * @param rc_id the rc_id to set
	 */
	public void setRc_id(Integer rc_id) {
		this.rc_id = rc_id;
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
	
	 

}
