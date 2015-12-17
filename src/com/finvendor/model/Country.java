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
@Table(name="country")
public class Country implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="country_id")
    @GeneratedValue
    private Integer country_id;
	
	@Column(name="name")
	private String name;

	@Column(name="iso_2_cd")
	private String iso_2_cd;
	
	@Column(name="iso_3_cd")
	private String iso_3_cd;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="country")
	private Set<RegionCountryMap> regionCountryMaps=new HashSet<RegionCountryMap>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="country")
	private Set<CountryExchangeMap> countryExchangeMaps=new HashSet<CountryExchangeMap>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="country")
	private Set<VendorRegionCountryExchangeMap> vendorRegionCountryMaps=new HashSet<VendorRegionCountryExchangeMap>();

	/**
	 * @return the country_id
	 */
	public Integer getCountry_id() {
		return country_id;
	}

	/**
	 * @param country_id the country_id to set
	 */
	public void setCountry_id(Integer country_id) {
		this.country_id = country_id;
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
	 * @return the iso_2_cd
	 */
	public String getIso_2_cd() {
		return iso_2_cd;
	}

	/**
	 * @param iso_2_cd the iso_2_cd to set
	 */
	public void setIso_2_cd(String iso_2_cd) {
		this.iso_2_cd = iso_2_cd;
	}

	/**
	 * @return the iso_3_cd
	 */
	public String getIso_3_cd() {
		return iso_3_cd;
	}

	/**
	 * @param iso_3_cd the iso_3_cd to set
	 */
	public void setIso_3_cd(String iso_3_cd) {
		this.iso_3_cd = iso_3_cd;
	}

	/**
	 * @return the regionCountryMaps
	 */
	public Set<RegionCountryMap> getRegionCountryMaps() {
		return regionCountryMaps;
	}

	/**
	 * @param regionCountryMaps the regionCountryMaps to set
	 */
	public void setRegionCountryMaps(Set<RegionCountryMap> regionCountryMaps) {
		this.regionCountryMaps = regionCountryMaps;
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
