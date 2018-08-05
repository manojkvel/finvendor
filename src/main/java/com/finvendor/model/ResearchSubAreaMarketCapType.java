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
@Table(name="rsch_sub_area_mkt_cap_type")
public class ResearchSubAreaMarketCapType implements Serializable {

	private static final long serialVersionUID = -3951449132568377457L;
	
	@Id
    @Column(name="company_id")
	private String company_id;
	
	@ManyToOne(targetEntity=ResearchArea.class, fetch=FetchType.EAGER)
	@JoinColumn(name="rsch_area_id", nullable=false)
	private ResearchArea researchArea;
	
	@ManyToOne(targetEntity=ResearchSubArea.class, fetch=FetchType.EAGER)
	@JoinColumn(name="rsch_sub_area_id", nullable=false)
	private ResearchSubArea researchSubArea;
	
	@Column(name="mcap_id")
	private String capType;

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
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

	public String getCapType() {
		return capType;
	}

	public void setCapType(String capType) {
		this.capType = capType;
	}
	
}
