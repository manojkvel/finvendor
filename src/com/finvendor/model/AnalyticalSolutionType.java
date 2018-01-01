package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="analytical_soln_type")
public class AnalyticalSolutionType implements Serializable {
	
	private static final long serialVersionUID = 30122016155601L;
	
	@Id
    @Column(name="analytical_solution_type_id")
    @GeneratedValue
    private Integer analyticalSolutionTypeId;
	
	@Column(name="description")
	private String description;

	public Integer getAnalyticalSolutionTypeId() {
		return analyticalSolutionTypeId;
	}

	public void setAnalyticalSolutionTypeId(Integer analyticalSolutionTypeId) {
		this.analyticalSolutionTypeId = analyticalSolutionTypeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
