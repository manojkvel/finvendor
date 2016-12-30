package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="analytical_soln_sub_type")
public class AnalyticalSolutionSubType implements Serializable {

	private static final long serialVersionUID = 30122016155901L;
	
	@Id
    @Column(name="analytical_solution_sub_type_id")
    @GeneratedValue
    private Integer analyticalSolutionSubTypeId;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne(targetEntity=AnalyticalSolutionType.class, fetch=FetchType.EAGER)
	@JoinColumn(name="analytical_solution_type_id", nullable=false)
	private AnalyticalSolutionType analyticalSolutionType;

	public Integer getAnalyticalSolutionSubTypeId() {
		return analyticalSolutionSubTypeId;
	}

	public void setAnalyticalSolutionSubTypeId(Integer analyticalSolutionSubTypeId) {
		this.analyticalSolutionSubTypeId = analyticalSolutionSubTypeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AnalyticalSolutionType getAnalyticalSolutionType() {
		return analyticalSolutionType;
	}

	public void setAnalyticalSolutionType(AnalyticalSolutionType analyticalSolutionType) {
		this.analyticalSolutionType = analyticalSolutionType;
	}
	
}
