package com.finvendor.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="vendor")
public class Vendor implements Serializable {

	private static final long serialVersionUID = 120920151043L;
	
	@Id
    @Column(name="vendor_id")
    private String id;
	
	@Column(name="fname")
	private String firstName;
	
	@Column(name="lname")
	private String lastName;
	
	@Column(name="designation")
	private String designation;
	
	//@Column(name="email")
	//private String email;
	
	@Column(name="secondary_email")
	private String secondaryEmail;
	
	@Column(name="telephone")
	private String telephone;
	
	@Column(name="company")
	private String company;
	
	@Column(name="company_url")
	private String companyUrl;
	
	@Column(name="companytype")
	private String companyType;
	
	@Column(name="company_info")
	private String companyInfo;
	
	@Column(name="tags")
	private String tags;
	
	@Column(name="companyaddress")
	private String companyAddress;
	
	@Column(name="regionofincorp")
	private Integer regionofincorp;
	
	@Column(name="countryofincorp")
	private String 	countryofincorp;
		 
	@OneToOne
	@JoinColumn(name="username", nullable=false)
	private FinVendorUser user;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="vendor")
	private Set<VendorOffering> vendorOfferings=new HashSet<VendorOffering>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="vendor")
	private Set<VendorRegionCountryExchangeMap> vendorRegionCountryMaps=new HashSet<VendorRegionCountryExchangeMap>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="vendor")
	private Set<VendorSupport> vendorSupports=new HashSet<VendorSupport>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="vendor")
	private Set<VendorAwardsMap> vendorAwardsMaps=new HashSet<VendorAwardsMap>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="vendor")
	private Set<VendorDistribution> vendorDistributions=new HashSet<VendorDistribution>();
	 
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCountryofincorp() {
		return countryofincorp;
	}

	public void setCountryofincorp(String countryofincorp) {
		this.countryofincorp = countryofincorp;
	}

	public FinVendorUser getUser() {
		return user;
	}

	public void setUser(FinVendorUser user) {
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	//public String getEmail() {
	//	return email;
	//}

	//public void setEmail(String email) {
	//	this.email = email;
	//}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Integer getRegionofincorp() {
		return regionofincorp;
	}

	public void setRegionofincorp(Integer regionofincorp) {
		this.regionofincorp = regionofincorp;
	}

	public Set<VendorOffering> getVendorOfferings() {
		return vendorOfferings;
	}

	public void setVendorOfferings(Set<VendorOffering> vendorOfferings) {
		this.vendorOfferings = vendorOfferings;
	}

	public Set<VendorRegionCountryExchangeMap> getVendorRegionCountryMaps() {
		return vendorRegionCountryMaps;
	}

	public void setVendorRegionCountryMaps(Set<VendorRegionCountryExchangeMap> vendorRegionCountryMaps) {
		this.vendorRegionCountryMaps = vendorRegionCountryMaps;
	}

	public Set<VendorSupport> getVendorSupports() {
		return vendorSupports;
	}

	public void setVendorSupports(Set<VendorSupport> vendorSupports) {
		this.vendorSupports = vendorSupports;
	}

	public Set<VendorAwardsMap> getVendorAwardsMaps() {
		return vendorAwardsMaps;
	}

	public void setVendorAwardsMaps(Set<VendorAwardsMap> vendorAwardsMaps) {
		this.vendorAwardsMaps = vendorAwardsMaps;
	}

	public Set<VendorDistribution> getVendorDistributions() {
		return vendorDistributions;
	}

	public void setVendorDistributions(Set<VendorDistribution> vendorDistributions) {
		this.vendorDistributions = vendorDistributions;
	}

	public String getSecondaryEmail() {
		return secondaryEmail;
	}

	public void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
	}

	public String getCompanyUrl() {
		return companyUrl;
	}

	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}

	public String getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(String companyInfo) {
		this.companyInfo = companyInfo;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
}
