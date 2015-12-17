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
@Table(name="solution")
public class Solutions implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="solution_id")
    @GeneratedValue
    private Integer solution_id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
   
	@ManyToOne(targetEntity=SolutionTypes.class,fetch=FetchType.LAZY)
	@JoinColumn(name="solution_type_id", nullable=false)
	private SolutionTypes solutionTypes;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="solutions")
	private Set<VendorSolution> vendorSolutions=new HashSet<VendorSolution>();

	/**
	 * @return the solution_id
	 */
	public Integer getSolution_id() {
		return solution_id;
	}

	/**
	 * @param solution_id the solution_id to set
	 */
	public void setSolution_id(Integer solution_id) {
		this.solution_id = solution_id;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the solutionTypes
	 */
	public SolutionTypes getSolutionTypes() {
		return solutionTypes;
	}

	/**
	 * @param solutionTypes the solutionTypes to set
	 */
	public void setSolutionTypes(SolutionTypes solutionTypes) {
		this.solutionTypes = solutionTypes;
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
