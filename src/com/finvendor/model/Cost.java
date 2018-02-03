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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author rayulu vemula
 *
 */
@Entity
@Table(name="cost")
public class Cost implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="cost_id")
    @GeneratedValue
    private Integer cost_id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="range")
	private String range;
	
	@ManyToOne(targetEntity=Currency.class,fetch=FetchType.LAZY)
	@JoinColumn(name="denomination", nullable=false)
	private Currency currency;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="cost")
	private Set<VendorSolution> vendorSolutions=new HashSet<VendorSolution>();

	/**
	 * @return the cost_id
	 */
	public Integer getCost_id() {
		return cost_id;
	}

	/**
	 * @param cost_id the cost_id to set
	 */
	public void setCost_id(Integer cost_id) {
		this.cost_id = cost_id;
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
	 * @return the range
	 */
	public String getRange() {
		return range;
	}

	/**
	 * @param range the range to set
	 */
	public void setRange(String range) {
		this.range = range;
	}

	/**
	 * @return the currency
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	/**
	 * @return the vendorSolutions
	 */
	public Set<VendorSolution> getVendorSolutions() {
		return vendorSolutions;
	}

	/**
	 * @param vendorSolutions the vendorSolutions to set
	 */
	public void setVendorSolutions(Set<VendorSolution> vendorSolutions) {
		this.vendorSolutions = vendorSolutions;
	}
	

}
