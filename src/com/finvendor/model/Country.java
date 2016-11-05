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

@Entity
@Table(name="country")
public class Country implements Serializable{

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
		
	@ManyToOne(targetEntity=Region.class, fetch=FetchType.EAGER)
	@JoinColumn(name="region_id", nullable=false)
	private Region region;
	
	/*
	@OneToMany(fetch=FetchType.LAZY,mappedBy="country")
	private Set<RegionCountryMap> regionCountryMaps=new HashSet<RegionCountryMap>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="country")
	private Set<CountryExchangeMap> countryExchangeMaps=new HashSet<CountryExchangeMap>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="country")
	private Set<VendorRegionCountryExchangeMap> vendorRegionCountryMaps=new HashSet<VendorRegionCountryExchangeMap>();
	*/

	public Integer getCountry_id() {
		return country_id;
	}

	public void setCountry_id(Integer country_id) {
		this.country_id = country_id;
	}

	public String getName() {
		return name;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIso_2_cd() {
		return iso_2_cd;
	}

	public void setIso_2_cd(String iso_2_cd) {
		this.iso_2_cd = iso_2_cd;
	}

	public String getIso_3_cd() {
		return iso_3_cd;
	}

	public void setIso_3_cd(String iso_3_cd) {
		this.iso_3_cd = iso_3_cd;
	}

	/*
	public Set<RegionCountryMap> getRegionCountryMaps() {
		return regionCountryMaps;
	}

	public void setRegionCountryMaps(Set<RegionCountryMap> regionCountryMaps) {
		this.regionCountryMaps = regionCountryMaps;
	}

	public Set<CountryExchangeMap> getCountryExchangeMaps() {
		return countryExchangeMaps;
	}

	public void setCountryExchangeMaps(Set<CountryExchangeMap> countryExchangeMaps) {
		this.countryExchangeMaps = countryExchangeMaps;
	}

	public Set<VendorRegionCountryExchangeMap> getVendorRegionCountryMaps() {
		return vendorRegionCountryMaps;
	}

	public void setVendorRegionCountryMaps(Set<VendorRegionCountryExchangeMap> vendorRegionCountryMaps) {
		this.vendorRegionCountryMaps = vendorRegionCountryMaps;
	}
	*/
	
	
}
