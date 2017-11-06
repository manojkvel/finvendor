package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ven_rsrch_rpt_dtls")
public class VendorResearchReportsResearchDetails 
	implements Serializable {
	
	private static final long serialVersionUID = 03012017230601L;
	
	@Id
    @Column(name="product_id")
	private String productId; 
	
	@Column(name="accessbility")
	private String accessbility;
	
	@Column(name="suitability")
	private String suitability;
	
	@Column(name="cost_type")
	private String costType;
	
	@Column(name="sub_cost_pm")
	private float subCostPm;
	
	@Column(name="sub_cost_py")
	private float subCostPy;
	
	@Column(name="rep_format")
	private String repFormat;
	
	@Column(name="res_period_mon")
	private String resPeriodMon;
	
	@Column(name="res_period_year")
	private String resPeriodYear;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getAccessbility() {
		return accessbility;
	}

	public void setAccessbility(String accessbility) {
		this.accessbility = accessbility;
	}

	public String getSuitability() {
		return suitability;
	}

	public void setSuitability(String suitability) {
		this.suitability = suitability;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public float getSubCostPm() {
		return subCostPm;
	}

	public void setSubCostPm(float subCostPm) {
		this.subCostPm = subCostPm;
	}

	public float getSubCostPy() {
		return subCostPy;
	}

	public void setSubCostPy(float subCostPy) {
		this.subCostPy = subCostPy;
	}

	public String getRepFormat() {
		return repFormat;
	}

	public void setRepFormat(String repFormat) {
		this.repFormat = repFormat;
	}

	public String getResPeriodMon() {
		return resPeriodMon;
	}

	public void setResPeriodMon(String resPeriodMon) {
		this.resPeriodMon = resPeriodMon;
	}

	public String getResPeriodYear() {
		return resPeriodYear;
	}

	public void setResPeriodYear(String resPeriodYear) {
		this.resPeriodYear = resPeriodYear;
	}
	
}
