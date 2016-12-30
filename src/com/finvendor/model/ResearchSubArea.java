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
@Table(name="research_sub_area")
public class ResearchSubArea implements Serializable {
	
	private static final long serialVersionUID = 30122016124801L;
	
	@Id
    @Column(name="research_sub_area_id")
    @GeneratedValue
    private Integer researchSubAreaId;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne(targetEntity=ResearchArea.class, fetch=FetchType.EAGER)
	@JoinColumn(name="research_area_id", nullable=false)
	private ResearchArea researchArea;

	public Integer getResearchSubAreaId() {
		return researchSubAreaId;
	}

	public void setResearchSubAreaId(Integer researchSubAreaId) {
		this.researchSubAreaId = researchSubAreaId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ResearchArea getResearchArea() {
		return researchArea;
	}

	public void setResearchArea(ResearchArea researchArea) {
		this.researchArea = researchArea;
	}
	
}
