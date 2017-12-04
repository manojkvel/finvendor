<<<<<<< HEAD
package com.finvendor.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.finvendor.util.RequestConstans;

@Entity
@Table(name="consumer")
public class Consumer implements Serializable {
	
	private static final long serialVersionUID = 120920151051L;
	
	@Id
    @Column(name="consumer_id")
    private String id;
	
	@Column(name="fname")
	private String firstName;
	
	@Column(name="lname")
	private String lastName;
	
	@Column(name="telephone")
	private String telephone;
	
	@Column(name="company")
	private String company;
	
	@Column(name="companytype")
	private String companyType;
	
	@Column(name="tags")
	private String tags;
		
	@Column(name="designation")
	private String designation;
	
	@Column(name="secondary_email")
	private String secondaryEmail;
			
	@Column(name="company_url")
	private String companyUrl;
	
	@Column(name="company_info")
	private String companyInfo;
	
	@Column(name="regionofincorp")
	private Integer regionOfIncorporation;
	
	@Column(name="countryofincorp")
	private int countryOfIncorporation;
	
	@Column(name="yearofincorp")
	private int yearOfIncorporation;
	
	@Column(name="logotype")
	public String logoType;
	
	@Column(name="logobytes")
	@Lob
	private Blob logoBytes;
	
	@Column(name="city")
	private String city;
	
	@OneToOne
	@JoinColumn(name="username", nullable=false)
	private FinVendorUser user;
	
	@OneToOne(optional=true)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="company_sub_type")
	private CompanySubType companySubType;
	
	@Transient
	private boolean marketDataPreference;
	
	@Transient
	private boolean tradingAppPreference;
	
	@Transient
	private boolean analyticsAppPreference;
	
	@Transient
	private boolean researchReportPreference;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public FinVendorUser getUser() {
		return user;
	}

	public void setUser(FinVendorUser user) {
		this.user = user;
	}
	
	public boolean getMarketDataPreference() {
		return marketDataPreference;
	}

	public void setMarketDataPreference(boolean marketDataPreference) {
		this.marketDataPreference = marketDataPreference;
	}

	public boolean getTradingAppPreference() {
		return tradingAppPreference;
	}

	public void setTradingAppPreference(boolean tradingAppPreference) {
		this.tradingAppPreference = tradingAppPreference;
	}

	public boolean getAnalyticsAppPreference() {
		return analyticsAppPreference;
	}

	public void setAnalyticsAppPreference(boolean analyticsAppPreference) {
		this.analyticsAppPreference = analyticsAppPreference;
	}

	public boolean getResearchReportPreference() {
		return researchReportPreference;
	}

	public void setResearchReportPreference(boolean researchReportPreference) {
		this.researchReportPreference = researchReportPreference;
	}
	
	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
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
	
	public Integer getRegionOfIncorporation() {
		return regionOfIncorporation;
	}

	public void setRegionOfIncorporation(Integer regionOfIncorporation) {
		this.regionOfIncorporation = regionOfIncorporation;
	}

	public int getCountryOfIncorporation() {
		return countryOfIncorporation;
	}

	public void setCountryOfIncorporation(int countryOfIncorporation) {
		this.countryOfIncorporation = countryOfIncorporation;
	}
	
	public int getYearOfIncorporation() {
		return yearOfIncorporation;
	}

	public void setYearOfIncorporation(int yearOfIncorporation) {
		this.yearOfIncorporation = yearOfIncorporation;
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
	
	public CompanySubType getCompanySubType() {
		return companySubType;
	}

	public void setCompanySubType(CompanySubType companySubType) {
		this.companySubType = companySubType;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@PostLoad
	public void setVendorPreference() {
		if(tags != null) {
			String[] vendorPreferenceTags = tags.split(",");
			for(String vendorType : vendorPreferenceTags) {
				switch(vendorType) {
					case RequestConstans.Vendor.DATA_AGGREGATOR :
						this.setMarketDataPreference(true);
						break;
					case RequestConstans.Vendor.TRADING_APPLICATION :
						this.setTradingAppPreference(true);
						break;
					case RequestConstans.Vendor.ANALYTICS_APPLICATION :
						this.setAnalyticsAppPreference(true);
						break;
					case RequestConstans.Vendor.RESEARCH_REPORT :
						this.setResearchReportPreference(true);
						break;
				}
			}
		}
	}
}
=======
package com.finvendor.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.finvendor.util.RequestConstans;

@Entity
@Table(name="consumer")
public class Consumer implements Serializable {
	
	private static final long serialVersionUID = 120920151051L;
	
	@Id
    @Column(name="consumer_id")
    private String id;
	
	@Column(name="fname")
	private String firstName;
	
	@Column(name="lname")
	private String lastName;
	
	@Column(name="telephone")
	private String telephone;
	
	@Column(name="company")
	private String company;
	
	@Column(name="companytype")
	private String companyType;
	
	@Column(name="tags")
	private String tags;
		
	@Column(name="designation")
	private String designation;
	
	@Column(name="secondary_email")
	private String secondaryEmail;
			
	@Column(name="company_url")
	private String companyUrl;
	
	@Column(name="company_info")
	private String companyInfo;
	
	@Column(name="regionofincorp")
	private Integer regionOfIncorporation;
	
	@Column(name="countryofincorp")
	private int countryOfIncorporation;
	
	@Column(name="yearofincorp")
	private int yearOfIncorporation;
	
	@Column(name="logotype")
	public String logoType;
	
	@Column(name="logobytes")
	@Lob
	private Blob logoBytes;
	
	@Column(name="city")
	private String city;
	
	@OneToOne
	@JoinColumn(name="username", nullable=false)
	private FinVendorUser user;
	
	@OneToOne(optional=true)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="company_sub_type")
	private CompanySubType companySubType;
	
	@Transient
	private boolean marketDataPreference;
	
	@Transient
	private boolean tradingAppPreference;
	
	@Transient
	private boolean analyticsAppPreference;
	
	@Transient
	private boolean researchReportPreference;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public FinVendorUser getUser() {
		return user;
	}

	public void setUser(FinVendorUser user) {
		this.user = user;
	}
	
	public boolean getMarketDataPreference() {
		return marketDataPreference;
	}

	public void setMarketDataPreference(boolean marketDataPreference) {
		this.marketDataPreference = marketDataPreference;
	}

	public boolean getTradingAppPreference() {
		return tradingAppPreference;
	}

	public void setTradingAppPreference(boolean tradingAppPreference) {
		this.tradingAppPreference = tradingAppPreference;
	}

	public boolean getAnalyticsAppPreference() {
		return analyticsAppPreference;
	}

	public void setAnalyticsAppPreference(boolean analyticsAppPreference) {
		this.analyticsAppPreference = analyticsAppPreference;
	}

	public boolean getResearchReportPreference() {
		return researchReportPreference;
	}

	public void setResearchReportPreference(boolean researchReportPreference) {
		this.researchReportPreference = researchReportPreference;
	}
	
	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
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
	
	public Integer getRegionOfIncorporation() {
		return regionOfIncorporation;
	}

	public void setRegionOfIncorporation(Integer regionOfIncorporation) {
		this.regionOfIncorporation = regionOfIncorporation;
	}

	public int getCountryOfIncorporation() {
		return countryOfIncorporation;
	}

	public void setCountryOfIncorporation(int countryOfIncorporation) {
		this.countryOfIncorporation = countryOfIncorporation;
	}
	
	public int getYearOfIncorporation() {
		return yearOfIncorporation;
	}

	public void setYearOfIncorporation(int yearOfIncorporation) {
		this.yearOfIncorporation = yearOfIncorporation;
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
	
	public CompanySubType getCompanySubType() {
		return companySubType;
	}

	public void setCompanySubType(CompanySubType companySubType) {
		this.companySubType = companySubType;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@PostLoad
	public void setVendorPreference() {
		if(tags != null) {
			String[] vendorPreferenceTags = tags.split(",");
			for(String vendorType : vendorPreferenceTags) {
				switch(vendorType) {
					case RequestConstans.Vendor.DATA_AGGREGATOR :
						this.setMarketDataPreference(true);
						break;
					case RequestConstans.Vendor.TRADING_APPLICATION :
						this.setTradingAppPreference(true);
						break;
					case RequestConstans.Vendor.ANALYTICS_APPLICATION :
						this.setAnalyticsAppPreference(true);
						break;
					case RequestConstans.Vendor.RESEARCH_REPORT :
						this.setResearchReportPreference(true);
						break;
				}
			}
		}
	}
}
>>>>>>> origin/master
