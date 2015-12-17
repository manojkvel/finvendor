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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author rayulu vemula
 *
 */
@Entity
@Table(name="exchange")
public class Exchange implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="exchange_id")
    @GeneratedValue
    private Integer exchange_id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="mic_cd")
	private String mic_cd;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="exchange")
	private Set<CountryExchangeMap> countryExchangeMaps=new HashSet<CountryExchangeMap>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="exchange")
	private Set<VendorRegionCountryExchangeMap> vendorRegionCountryMaps=new HashSet<VendorRegionCountryExchangeMap>();

	
	/**
	 * @return the exchange_id
	 */
	public Integer getExchange_id() {
		return exchange_id;
	}

	/**
	 * @param exchange_id the exchange_id to set
	 */
	public void setExchange_id(Integer exchange_id) {
		this.exchange_id = exchange_id;
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
	 * @return the mic_cd
	 */
	public String getMic_cd() {
		return mic_cd;
	}

	/**
	 * @param mic_cd the mic_cd to set
	 */
	public void setMic_cd(String mic_cd) {
		this.mic_cd = mic_cd;
	}

	/**
	 * @return the countryExchangeMaps
	 */
	public Set<CountryExchangeMap> getCountryExchangeMaps() {
		return countryExchangeMaps;
	}

	/**
	 * @param countryExchangeMaps the countryExchangeMaps to set
	 */
	public void setCountryExchangeMaps(Set<CountryExchangeMap> countryExchangeMaps) {
		this.countryExchangeMaps = countryExchangeMaps;
	}

	/**
	 * @return the vendorRegionCountryMaps
	 */
	public Set<VendorRegionCountryExchangeMap> getVendorRegionCountryMaps() {
		return vendorRegionCountryMaps;
	}

	/**
	 * @param vendorRegionCountryMaps the vendorRegionCountryMaps to set
	 */
	public void setVendorRegionCountryMaps(Set<VendorRegionCountryExchangeMap> vendorRegionCountryMaps) {
		this.vendorRegionCountryMaps = vendorRegionCountryMaps;
	}

	
}
