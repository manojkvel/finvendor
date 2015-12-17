package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	
	//@Column(name="email")
	//private String email;
	
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

	//public String getEmail() {
	//	return email;
	//}

	//public void setEmail(String email) {
	//	this.email = email;
	//}

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
}
