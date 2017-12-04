<<<<<<< HEAD
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

@Entity
@Table(name="region")
public class Region implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="region_id")
    @GeneratedValue
    private Integer region_id;
	
	@Column(name="name")
	private String name;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="region")
	private Set<Country> countires = new HashSet<Country>();
	
	/*
	@OneToMany(fetch=FetchType.LAZY,mappedBy="region")
	private Set<RegionCountryMap> regionCountryMaps=new HashSet<RegionCountryMap>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="region")
	private Set<VendorRegionCountryExchangeMap> vendorRegionCountryMaps=new HashSet<VendorRegionCountryExchangeMap>();
	*/
	
	public Integer getRegion_id() {
		return region_id;
	}
	
	public Set<Country> getCountires() {
		return countires;
	}

	public void setCountires(Set<Country> countires) {
		this.countires = countires;
	}

	public void setRegion_id(Integer region_id) {
		this.region_id = region_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	public Set<RegionCountryMap> getRegionCountryMaps() {
		return regionCountryMaps;
	}

	public void setRegionCountryMaps(Set<RegionCountryMap> regionCountryMaps) {
		this.regionCountryMaps = regionCountryMaps;
	}

	public Set<VendorRegionCountryExchangeMap> getVendorRegionCountryMaps() {
		return vendorRegionCountryMaps;
	}

	public void setVendorRegionCountryMaps(Set<VendorRegionCountryExchangeMap> vendorRegionCountryMaps) {
		this.vendorRegionCountryMaps = vendorRegionCountryMaps;
	}
	*/
	
   
 

}
=======
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

@Entity
@Table(name="region")
public class Region implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="region_id")
    @GeneratedValue
    private Integer region_id;
	
	@Column(name="name")
	private String name;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="region")
	private Set<Country> countires = new HashSet<Country>();
	
	/*
	@OneToMany(fetch=FetchType.LAZY,mappedBy="region")
	private Set<RegionCountryMap> regionCountryMaps=new HashSet<RegionCountryMap>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="region")
	private Set<VendorRegionCountryExchangeMap> vendorRegionCountryMaps=new HashSet<VendorRegionCountryExchangeMap>();
	*/
	
	public Integer getRegion_id() {
		return region_id;
	}
	
	public Set<Country> getCountires() {
		return countires;
	}

	public void setCountires(Set<Country> countires) {
		this.countires = countires;
	}

	public void setRegion_id(Integer region_id) {
		this.region_id = region_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	public Set<RegionCountryMap> getRegionCountryMaps() {
		return regionCountryMaps;
	}

	public void setRegionCountryMaps(Set<RegionCountryMap> regionCountryMaps) {
		this.regionCountryMaps = regionCountryMaps;
	}

	public Set<VendorRegionCountryExchangeMap> getVendorRegionCountryMaps() {
		return vendorRegionCountryMaps;
	}

	public void setVendorRegionCountryMaps(Set<VendorRegionCountryExchangeMap> vendorRegionCountryMaps) {
		this.vendorRegionCountryMaps = vendorRegionCountryMaps;
	}
	*/
	
   
 

}
>>>>>>> origin/master
