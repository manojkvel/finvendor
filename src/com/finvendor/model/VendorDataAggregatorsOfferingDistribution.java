package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ven_data_aggr_offering_distribution")
public class VendorDataAggregatorsOfferingDistribution 
	implements Serializable {

	private static final long serialVersionUID = 16102016095905L;
	
	@Id
    @Column(name="product_id")
	private String productId;
	
	@Column(name="feed_type")
	private String feedType;
	
	@Column(name="feed_sub_type")
	private String feedSubType;
	
	@Column(name="frequency")
	private String frequency;
	
	@Column(name="distribution_method")
	private String distributionMethod;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getFeedType() {
		return feedType;
	}

	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}

	public String getFeedSubType() {
		return feedSubType;
	}

	public void setFeedSubType(String feedSubType) {
		this.feedSubType = feedSubType;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getDistributionMethod() {
		return distributionMethod;
	}

	public void setDistributionMethod(String distributionMethod) {
		this.distributionMethod = distributionMethod;
	}
		
}
