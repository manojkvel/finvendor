package com.finvendor.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
	
	@Column(name="logolength")
	private Integer logoLength;
	
	@Column(name="LogoName")
	public String logoName;
	
	@Column(name="logoType")
	public String logoType;
	
	@Column(name="logoBytes")
	@Lob
	private Blob logoBytes;
	
	@Column(name="tags")
	private String tags;
	
	@Column(name="companyaddress")
	private String companyAddress;
	
	@Column(name="regionofincorp")
	private Integer regionofincorp;
	
	@Column(name="countryofincorp")
	private String countryofincorp;
		 	
	@OneToOne
	@JoinColumn(name="username", nullable=false)
	private FinVendorUser user;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="vendor")
	private Set<VendorAwardsMap> vendorAwardsMaps = new HashSet<VendorAwardsMap>();
	
	@OneToOne(fetch=FetchType.EAGER, mappedBy="vendor", cascade=CascadeType.ALL)
	private VendorSupport vendorSupport;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="vendor")
	private List<VendorDataAggregatorsOffering> dataAggregatorsOffering;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="vendor")
	private List<VendorTradingApplicationsOffering> tradingApplicationsOffering;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="vendor")
	private List<VendorResearchReportsOffering> researchReportsOffering;
	
	private String telephoneCode;
		
	public VendorSupport getVendorSupport() {
		return vendorSupport;
	}

	public void setVendorSupport(VendorSupport vendorSupport) {
		this.vendorSupport = vendorSupport;
	}

	
	
	/*
	@OneToMany(fetch=FetchType.LAZY,mappedBy="vendor",cascade = CascadeType.ALL)
	private Set<VendorDataCoverage> vendorDataCoverage;  

	@OneToMany(fetch=FetchType.LAZY,mappedBy="vendor",cascade = CascadeType.ALL)
	private Set<VendorDistribution> vendorDistribution;  

	@OneToMany(fetch=FetchType.LAZY,mappedBy="vendor",cascade = CascadeType.ALL)
	private Set<Solutions> solution;  
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="vendor",cascade = CascadeType.ALL)
	private Set<VendorTradingCapabilitiesSupported> vendorTradingCapabilitiesSupported;  

	@OneToMany(fetch=FetchType.LAZY,mappedBy="vendor",cascade = CascadeType.ALL)
	private Set<VendorTradingSoftwareDetails> vendorTradingSoftwareDetails;  
	@OneToMany(fetch=FetchType.LAZY,mappedBy="vendor",cascade = CascadeType.ALL)
	private Set<VendorAnalyticsfeaturesSupported> vendorAnalyticsfeaturesSupported;  

	@OneToMany(fetch=FetchType.LAZY,mappedBy="vendor",cascade = CascadeType.ALL)
	private Set<VendorAnalyticsSoftwareDetails> vendorAnalyticsSoftwareDetails;  
	@OneToMany(fetch=FetchType.LAZY,mappedBy="vendor",cascade = CascadeType.ALL)
	private Set<VendorResearchCoverage> vendorResearchCoverage;  

	@OneToMany(fetch=FetchType.LAZY,mappedBy="vendor",cascade = CascadeType.ALL)
	private Set<VendorResearchDetails> vendorResearchDetails;  
	@OneToMany(fetch=FetchType.LAZY,mappedBy="vendor",cascade = CascadeType.ALL)
	private Set<VendorAnalystProfile> vendorAnalystProfile;  
	
	*/

	
	
	/*
	public Set<Solutions> getSolution() {
		return solution;
	}

	public void setSolution(Set<Solutions> solution) {
		this.solution = solution;
	}

	public Set<VendorDistribution> getVendorDistribution() {
		return vendorDistribution;
	}

	public void setVendorDistribution(Set<VendorDistribution> vendorDistribution) {
		this.vendorDistribution = vendorDistribution;
	}

	public Set<VendorDataCoverage> getVendorDataCoverage() {
		return vendorDataCoverage;
	}

	public void setVendorDataCoverage(Set<VendorDataCoverage> vendorDataCoverage) {
		this.vendorDataCoverage = vendorDataCoverage;
	}
	
	*/

	public Integer getLogoLength() {
		return logoLength;
	}

	public void setLogoLength(Integer logoLength) {
		this.logoLength = logoLength;
	}


	public String getLogoName() {
		return logoName;
	}

	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}

	public String getLogoType() {
		return logoType;
	}

	public void setLogoType(String logoType) {
		this.logoType = logoType;
	}
	
	public Blob getLogoBytes() {
		return logoBytes;
	}

	public void setLogoBytes(Blob logoBytes) {
		this.logoBytes = logoBytes;
	}

	
	
	/*@OneToOne
	@JoinColumn(name="fileDetails", nullable=false)
	private FileDetails fileDetails;
	*/

	
	
	
	/*
	@OneToMany(fetch=FetchType.LAZY,mappedBy="vendor",cascade = CascadeType.ALL)
	private Set<VendorOffering> vendorOfferings=new HashSet<VendorOffering>();
	*/
	
	/*
	@OneToMany(fetch=FetchType.LAZY,mappedBy="vendor")
	private Set<VendorRegionCountryExchangeMap> vendorRegionCountryMaps=new HashSet<VendorRegionCountryExchangeMap>();
	*/
	
	/*
	@OneToMany(fetch=FetchType.LAZY,mappedBy="vendor")
	private Set<VendorSupport> vendorSupports=new HashSet<VendorSupport>();
	*/
	
	
	
	/*
	@OneToMany(fetch=FetchType.LAZY,mappedBy="vendor")
	private Set<VendorDataCoverage> vendorDistributions=new HashSet<VendorDataCoverage>();
	*/
	
	
	
	
	public String getTelephoneCode() {
		return telephoneCode;
	}

	public void setTelephoneCode(String telephoneCode) {
		this.telephoneCode = telephoneCode;
	}

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

	/*
	public Set<VendorOffering> getVendorOfferings() {
		return vendorOfferings;
	}

	public void setVendorOfferings(Set<VendorOffering> vendorOfferings) {
		this.vendorOfferings = vendorOfferings;
	}
	*/

	
	/*
	public Set<VendorRegionCountryExchangeMap> getVendorRegionCountryMaps() {
		return vendorRegionCountryMaps;
	}

	public void setVendorRegionCountryMaps(Set<VendorRegionCountryExchangeMap> vendorRegionCountryMaps) {
		this.vendorRegionCountryMaps = vendorRegionCountryMaps;
	}
	
	*/

	/*
	public Set<VendorSupport> getVendorSupports() {
		return vendorSupports;
	}

	public void setVendorSupports(Set<VendorSupport> vendorSupports) {
		this.vendorSupports = vendorSupports;
	}
	*/

	public Set<VendorAwardsMap> getVendorAwardsMaps() {
		return vendorAwardsMaps;
	}

	public void setVendorAwardsMaps(Set<VendorAwardsMap> vendorAwardsMaps) {
		this.vendorAwardsMaps = vendorAwardsMaps;
	}

	
	/*
	public Set<VendorDataCoverage> getVendorDistributions() {
		return vendorDistributions;
	}

	public void setVendorDistributions(Set<VendorDataCoverage> vendorDistributions) {
		this.vendorDistributions = vendorDistributions;
	}
	
	*/

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


	/*
	
	public Set<VendorTradingCapabilitiesSupported> getVendorTradingCapabilitiesSupported() {
		return vendorTradingCapabilitiesSupported;
	}

	public void setVendorTradingCapabilitiesSupported(Set<VendorTradingCapabilitiesSupported> vendorTradingCapabilitiesSupported) {
		this.vendorTradingCapabilitiesSupported = vendorTradingCapabilitiesSupported;
	}

	public Set<VendorTradingSoftwareDetails> getVendorTradingSoftwareDetails() {
		return vendorTradingSoftwareDetails;
	}

	public void setVendorTradingSoftwareDetails(Set<VendorTradingSoftwareDetails> vendorTradingSoftwareDetails) {
		this.vendorTradingSoftwareDetails = vendorTradingSoftwareDetails;
	}

	public Set<VendorAnalyticsfeaturesSupported> getVendorAnalyticsfeaturesSupported() {
		return vendorAnalyticsfeaturesSupported;
	}

	public void setVendorAnalyticsfeaturesSupported(Set<VendorAnalyticsfeaturesSupported> vendorAnalyticsfeaturesSupported) {
		this.vendorAnalyticsfeaturesSupported = vendorAnalyticsfeaturesSupported;
	}

	public Set<VendorAnalyticsSoftwareDetails> getVendorAnalyticsSoftwareDetails() {
		return vendorAnalyticsSoftwareDetails;
	}

	public void setVendorAnalyticsSoftwareDetails(Set<VendorAnalyticsSoftwareDetails> vendorAnalyticsSoftwareDetails) {
		this.vendorAnalyticsSoftwareDetails = vendorAnalyticsSoftwareDetails;
	}

	public Set<VendorResearchCoverage> getVendorResearchCoverage() {
		return vendorResearchCoverage;
	}

	public void setVendorResearchCoverage(Set<VendorResearchCoverage> vendorResearchCoverage) {
		this.vendorResearchCoverage = vendorResearchCoverage;
	}

	public Set<VendorResearchDetails> getVendorResearchDetails() {
		return vendorResearchDetails;
	}

	public void setVendorResearchDetails(Set<VendorResearchDetails> vendorResearchDetails) {
		this.vendorResearchDetails = vendorResearchDetails;
	}

	public Set<VendorAnalystProfile> getVendorAnalystProfile() {
		return vendorAnalystProfile;
	}

	public void setVendorAnalystProfile(Set<VendorAnalystProfile> vendorAnalystProfile) {
		this.vendorAnalystProfile = vendorAnalystProfile;
	}
	
*/
	public List<VendorDataAggregatorsOffering> getDataAggregatorsOffering() {
		return dataAggregatorsOffering;
	}

	public void setDataAggregatorsOffering(List<VendorDataAggregatorsOffering> dataAggregatorsOffering) {
		this.dataAggregatorsOffering = dataAggregatorsOffering;
	}
	
	public List<VendorTradingApplicationsOffering> getTradingApplicationsOffering() {
		return tradingApplicationsOffering;
	}

	public void setTradingApplicationsOffering(List<VendorTradingApplicationsOffering> tradingApplicationsOffering) {
		this.tradingApplicationsOffering = tradingApplicationsOffering;
	}

	public List<VendorResearchReportsOffering> getResearchReportsOffering() {
		return researchReportsOffering;
	}

	public void setResearchReportsOffering(List<VendorResearchReportsOffering> researchReportsOffering) {
		this.researchReportsOffering = researchReportsOffering;
	}
	
}
