package com.finvendor.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class FinVendorUser implements Serializable {
	
	private static final long serialVersionUID = 120920150845L;

	@Id
	@Column(name="USERNAME")
	private String userName;

	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="ENABLED")
	private Boolean enabled;
	
	@Column(name="LOGIN_ATTEMPTS")
	private short loginAttempts;
		
	@Column(name="LAST_LOGIN")
	private Timestamp lastLogin;
	
	@Column(name="REGISTRATION_DATE")
	private Timestamp registrationDate;
	
	@Column(name="LAST_MODIFIED")
	private Timestamp lastModified;

	@Column(name="VERIFIED")
	private String verified;

	@Column(name="subscription_date")
	private String subscriptionDate;

	@Column(name="subscription_type")
	private String subscriptionType;

	@Column(name="trial_period_start_time")
	private String trialPeriodStartTime;

	@Column(name="trial_period_end_time")
	private String trialPeriodEndTime;

	@Column(name="subscription_start_time")
	private String subscriptionStartTime;

	@Column(name="subscription_end_time")
	private String subscriptionEndTime;

	@Column(name="subscription_state")
	private String subscriptionState;

	@OneToMany(mappedBy="user", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private Set<UserRole> userRoles = new HashSet<UserRole>();
	
	@OneToOne(mappedBy="user", cascade={CascadeType.ALL})
	private Vendor vendor;
	
	@OneToOne(mappedBy="user", cascade={CascadeType.ALL})
	private Consumer consumer;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public short getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(short loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Timestamp getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	public Timestamp getLastModified() {
		return lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}

	public String getVerified() {
		return verified;
	}

	public void setVerified(String verified) {
		this.verified = verified;
	}
	

	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}

	public String getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public String getSubscriptionStartTime() {
		return subscriptionStartTime;
	}

	public void setSubscriptionStartTime(String subscriptionStartTime) {
		this.subscriptionStartTime = subscriptionStartTime;
	}

	public String getSubscriptionEndTime() {
		return subscriptionEndTime;
	}

	public void setSubscriptionEndTime(String subscriptionEndTime) {
		this.subscriptionEndTime = subscriptionEndTime;
	}

	public String getSubscriptionState() {
		return subscriptionState;
	}

	public void setSubscriptionState(String subscriptionState) {
		this.subscriptionState = subscriptionState;
	}

	public String getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setSubscriptionDate(String subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

	public String getTrialPeriodStartTime() {
		return trialPeriodStartTime;
	}

	public void setTrialPeriodStartTime(String trialPeriodStartTime) {
		this.trialPeriodStartTime = trialPeriodStartTime;
	}

	public String getTrialPeriodEndTime() {
		return trialPeriodEndTime;
	}

	public void setTrialPeriodEndTime(String trialPeriodEndTime) {
		this.trialPeriodEndTime = trialPeriodEndTime;
	}
}
