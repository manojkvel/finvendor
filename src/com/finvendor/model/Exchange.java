<<<<<<< HEAD
package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="exchange")
public class Exchange implements Serializable{

	private static final long serialVersionUID = 5112016094301L;
	
	@Id
    @Column(name="exchange_id")
    @GeneratedValue
    private Integer exchange_id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="mic_cd")
	private String mic_cd;
	
	@ManyToOne(targetEntity=Country.class)
	@JoinColumn(name="country_id", nullable=false)
	private Country country;
	/*
	@OneToMany(fetch=FetchType.LAZY,mappedBy="exchange")
	private Set<CountryExchangeMap> countryExchangeMaps=new HashSet<CountryExchangeMap>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="exchange")
	private Set<VendorRegionCountryExchangeMap> vendorRegionCountryMaps=new HashSet<VendorRegionCountryExchangeMap>();
	*/

	
	public Integer getExchange_id() {
		return exchange_id;
	}

	public void setExchange_id(Integer exchange_id) {
		this.exchange_id = exchange_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMic_cd() {
		return mic_cd;
	}

	public void setMic_cd(String mic_cd) {
		this.mic_cd = mic_cd;
	}
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	/*
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
=======
package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="exchange")
public class Exchange implements Serializable{

	private static final long serialVersionUID = 5112016094301L;
	
	@Id
    @Column(name="exchange_id")
    @GeneratedValue
    private Integer exchange_id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="mic_cd")
	private String mic_cd;
	
	@ManyToOne(targetEntity=Country.class)
	@JoinColumn(name="country_id", nullable=false)
	private Country country;
	/*
	@OneToMany(fetch=FetchType.LAZY,mappedBy="exchange")
	private Set<CountryExchangeMap> countryExchangeMaps=new HashSet<CountryExchangeMap>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="exchange")
	private Set<VendorRegionCountryExchangeMap> vendorRegionCountryMaps=new HashSet<VendorRegionCountryExchangeMap>();
	*/

	
	public Integer getExchange_id() {
		return exchange_id;
	}

	public void setExchange_id(Integer exchange_id) {
		this.exchange_id = exchange_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMic_cd() {
		return mic_cd;
	}

	public void setMic_cd(String mic_cd) {
		this.mic_cd = mic_cd;
	}
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	/*
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
>>>>>>> origin/master
