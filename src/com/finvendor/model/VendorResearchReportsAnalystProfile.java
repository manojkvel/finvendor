package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ven_rsrch_rpt_analyst_prof")
public class VendorResearchReportsAnalystProfile
	implements Serializable {

	private static final long serialVersionUID = 7012017090501L;
	
	@Id
    @Column(name="product_id")
	private String productId; 
	
	@Column(name="analyst_name")
	private String analystName;
	
	/*@OneToOne(targetEntity=Region.class)
	@JoinColumn(name="analyst_region", nullable=false)
	private Region analystRegion;
	
	@OneToOne(targetEntity=Country.class)
	@JoinColumn(name="analyst_country", nullable=false)
	private Country analystCountry;
		
	@Column(name="analyst_year_of_exp")
	private String analystYearOfExp;
	*/
	@Column(name="analyst_awards")
	private String analystAwards;
	
	@Column(name="anayst_cfa_charter")
	private String anaystCfaCharter;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getAnalystName() {
		return analystName;
	}

	public void setAnalystName(String analystName) {
		this.analystName = analystName;
	}

/*	public Region getAnalystRegion() {
		return analystRegion;
	}

	public void setAnalystRegion(Region analystRegion) {
		this.analystRegion = analystRegion;
	}

	public Country getAnalystCountry() {
		return analystCountry;
	}

	public void setAnalystCountry(Country analystCountry) {
		this.analystCountry = analystCountry;
	}

	public String getAnalystYearOfExp() {
		return analystYearOfExp;
	}

	public void setAnalystYearOfExp(String analystYearOfExp) {
		this.analystYearOfExp = analystYearOfExp;
	}*/

	public String getAnalystAwards() {
		return analystAwards;
	}

	public void setAnalystAwards(String analystAwards) {
		this.analystAwards = analystAwards;
	}

	public String getAnaystCfaCharter() {
		return anaystCfaCharter;
	}

	public void setAnaystCfaCharter(String anaystCfaCharter) {
		this.anaystCfaCharter = anaystCfaCharter;
	}
	
}
