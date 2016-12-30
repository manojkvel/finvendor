package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="research_area")
public class ResearchArea implements Serializable {
	
	private static final long serialVersionUID = 30122016124501L;
	
	@Id
    @Column(name="research_area_id")
    @GeneratedValue
    private Integer researchAreaId;
	
	@Column(name="description")
	private String description;

	public Integer getResearchAreaId() {
		return researchAreaId;
	}

	public void setResearchAreaId(Integer researchAreaId) {
		this.researchAreaId = researchAreaId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
