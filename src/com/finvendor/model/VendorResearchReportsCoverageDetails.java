package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ven_rsrch_rpt_covg")
public class VendorResearchReportsCoverageDetails 
	implements Serializable {
	
	private static final long serialVersionUID = 03012017230201L;
	
	@Id
    @Column(name="product_id")
	private String productId; 
	
	@Column(name="regions_covered")
	private String regionsCovered;
	
	@Column(name="total_analyst")
	private int totalAnalyst;
	
	@Column(name="existing_client_base")
	private int existingClientBase;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getRegionsCovered() {
		return regionsCovered;
	}

	public void setRegionsCovered(String regionsCovered) {
		this.regionsCovered = regionsCovered;
	}

	public int getTotalAnalyst() {
		return totalAnalyst;
	}

	public void setTotalAnalyst(int totalAnalyst) {
		this.totalAnalyst = totalAnalyst;
	}

	public int getExistingClientBase() {
		return existingClientBase;
	}

	public void setExistingClientBase(int existingClientBase) {
		this.existingClientBase = existingClientBase;
	}
	
}
