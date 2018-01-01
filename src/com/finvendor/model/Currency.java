/**
 * 
 */
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

/**
 * @author rayulu vemula
 *
 */
@Entity
@Table(name="currency")
public class Currency implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="currency_id")
    @GeneratedValue
    private Integer currency_id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="iso_cd")
	private String iso_cd;
	
	@Column(name="major_minor")
	private String major_minor;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="currency")
	private Set<Cost> costs=new HashSet<Cost>();

	
	/**
	 * @return the currency_id
	 */
	public Integer getCurrency_id() {
		return currency_id;
	}

	/**
	 * @param currency_id the currency_id to set
	 */
	public void setCurrency_id(Integer currency_id) {
		this.currency_id = currency_id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the iso_cd
	 */
	public String getIso_cd() {
		return iso_cd;
	}

	/**
	 * @param iso_cd the iso_cd to set
	 */
	public void setIso_cd(String iso_cd) {
		this.iso_cd = iso_cd;
	}

	/**
	 * @return the major_minor
	 */
	public String getMajor_minor() {
		return major_minor;
	}

	/**
	 * @param major_minor the major_minor to set
	 */
	public void setMajor_minor(String major_minor) {
		this.major_minor = major_minor;
	}

	/**
	 * @return the costs
	 */
	public Set<Cost> getCosts() {
		return costs;
	}

	/**
	 * @param costs the costs to set
	 */
	public void setCosts(Set<Cost> costs) {
		this.costs = costs;
	}
	
	
	
	
}
