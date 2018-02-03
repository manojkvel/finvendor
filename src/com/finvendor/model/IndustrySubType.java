package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="industry_sub_type")
public class IndustrySubType implements Serializable {
	
	private static final long serialVersionUID = -3751449132568377457L;
	
	@Id
    @Column(name="id")
	private String id;
	
	@ManyToOne(targetEntity=ResearchArea.class, fetch=FetchType.EAGER)
	@JoinColumn(name="rsch_area_id", nullable=false)
	private ResearchArea researchArea;
	
	@ManyToOne(targetEntity=ResearchSubArea.class, fetch=FetchType.EAGER)
	@JoinColumn(name="rsch_sub_area_id", nullable=false)
	private ResearchSubArea researchSubArea;
	
	@Column(name="industry_sub_type_name")
	private String industrySubTypeName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ResearchArea getResearchArea() {
		return researchArea;
	}

	public void setResearchArea(ResearchArea researchArea) {
		this.researchArea = researchArea;
	}

	public ResearchSubArea getResearchSubArea() {
		return researchSubArea;
	}

	public void setResearchSubArea(ResearchSubArea researchSubArea) {
		this.researchSubArea = researchSubArea;
	}

	public String getIndustrySubTypeName() {
		return industrySubTypeName;
	}

	public void setIndustrySubTypeName(String industrySubTypeName) {
		this.industrySubTypeName = industrySubTypeName;
	}

}
