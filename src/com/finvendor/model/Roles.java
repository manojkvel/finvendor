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
@Table(name="roles")
public class Roles implements Serializable{
	
	private static final long serialVersionUID = 120920151057L;
	@Id
    @Column(name="role_id")
    @GeneratedValue
    private Integer id;
	
	@Column(name="rolename")
	private String roleName;
   
	@OneToMany(fetch=FetchType.LAZY, mappedBy="roles")
	private Set<UserRole> userRoles = new HashSet<UserRole>();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
}
