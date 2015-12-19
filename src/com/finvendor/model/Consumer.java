package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.finvendor.controller.LoginController;
import com.finvendor.util.RequestConstans;

@Entity
@Table(name="consumer")
public class Consumer implements Serializable {
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
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
	
	@OneToOne
	@JoinColumn(name="username", nullable=false)
	private FinVendorUser user;
	
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
	
	@PostLoad
	public void setVendorPreference() {
		logger.debug("Consumer : PostLoad - setVendorPreference {}", tags);
		if(tags != null) {
			String[] vendorPreferenceTags = tags.split(",");
			for(String vendorType : vendorPreferenceTags) {
				switch(vendorType) {
					case RequestConstans.Vendor.DATA_AGGREGATOR :
						logger.debug("Consumer : PostLoad - setVendorPreference for {} as {}", user.getUserName(), RequestConstans.Vendor.DATA_AGGREGATOR);
						this.setMarketDataPreference(true);
						break;
					case RequestConstans.Vendor.TRADING_APPLICATION :
						logger.debug("Consumer : PostLoad - setVendorPreference for {} as {}", user.getUserName(), RequestConstans.Vendor.TRADING_APPLICATION);
						this.setTradingAppPreference(true);
						break;
					case RequestConstans.Vendor.ANALYTICS_APPLICATION :
						logger.debug("Consumer : PostLoad - setVendorPreference for {} as {}", user.getUserName(), RequestConstans.Vendor.ANALYTICS_APPLICATION);
						this.setAnalyticsAppPreference(true);
						break;
					case RequestConstans.Vendor.RESEARCH_REPORT :
						logger.debug("Consumer : PostLoad - setVendorPreference for {} as {}", user.getUserName(), RequestConstans.Vendor.RESEARCH_REPORT);
						this.setResearchReportPreference(true);
						break;
				}
			}
		}
	}
}
