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
@Table(name="rsch_sub_area_company_dtls")
public class ResearchSubAreaCompanyDetails implements Serializable {

	private static final long serialVersionUID = -3751449132568377457L;
	
	@Id
    @Column(name="id")
	private String id;
	
	@Column(name="company_id")
	private String compnayId;
	
	@Column(name="company_name")
	private String companyName;
	
	@ManyToOne(targetEntity=ResearchSubArea.class, fetch=FetchType.EAGER)
	@JoinColumn(name="rsch_sub_area_id", nullable=false)
	private ResearchSubArea researchSubArea;
	
	@ManyToOne(targetEntity=ResearchAreaStockClassification.class, fetch=FetchType.EAGER)
	@JoinColumn(name="stock_class_type_id", nullable=false)
	private ResearchAreaStockClassification researchAreaStockClassification;
	
	@ManyToOne(targetEntity=Country.class, fetch=FetchType.EAGER)
	@JoinColumn(name="country_id", nullable=false)
	private Country country;
	
	@Column(name="isin_code")
	private String isinCode;
	
	@Column(name="ticker")
	private String ticker;
	
	@Column(name="exchg_code")
	private String exchgCode;

	public String getCompnayId() {
		return compnayId;
	}

	public void setCompnayId(String compnayId) {
		this.compnayId = compnayId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public ResearchSubArea getResearchSubArea() {
		return researchSubArea;
	}

	public void setResearchSubArea(ResearchSubArea researchSubArea) {
		this.researchSubArea = researchSubArea;
	}

	public ResearchAreaStockClassification getResearchAreaStockClassification() {
		return researchAreaStockClassification;
	}

	public void setResearchAreaStockClassification(ResearchAreaStockClassification researchAreaStockClassification) {
		this.researchAreaStockClassification = researchAreaStockClassification;
	}

}
 