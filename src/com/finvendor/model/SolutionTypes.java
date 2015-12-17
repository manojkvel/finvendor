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
@Table(name="solution_type")
public class SolutionTypes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="solution_type_id")
    @GeneratedValue
    private Integer solution_type_id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="solutionTypes")
	private Set<Solutions> solutions=new HashSet<Solutions>();

	/**
	 * @return the solution_type_id
	 */
	public Integer getSolution_type_id() {
		return solution_type_id;
	}

	/**
	 * @param solution_type_id the solution_type_id to set
	 */
	public void setSolution_type_id(Integer solution_type_id) {
		this.solution_type_id = solution_type_id;
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
	 * @return the solutions
	 */
	public Set<Solutions> getSolutions() {
		return solutions;
	}

	/**
	 * @param solutions the solutions to set
	 */
	public void setSolutions(Set<Solutions> solutions) {
		this.solutions = solutions;
	}
}
