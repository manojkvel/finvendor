package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_roles")
public class UserRole implements Serializable{

	private static final long serialVersionUID = 120920151059L;

	@Id
    @Column(name="user_role_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	@ManyToOne(targetEntity=FinVendorUser.class)
	@JoinColumn(name="username", nullable=false)
	private FinVendorUser user; 
	
	@ManyToOne(targetEntity=Roles.class)
	@JoinColumn(name="role_id", nullable=false) 
	private Roles roles;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FinVendorUser getUser() {
		return user;
	}

	public void setUser(FinVendorUser user) {
		this.user = user;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}	 
}
