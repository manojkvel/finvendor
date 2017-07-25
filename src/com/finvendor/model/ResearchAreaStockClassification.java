package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rsch_area_stock_class")
public class ResearchAreaStockClassification implements Serializable {
		
	private static final long serialVersionUID = 2739608434104227444L;
	
	@Id
    @Column(name="stock_class_type_id")
	private String stockClassificationTypeId;
	
	@Column(name="stock_class_type")
	private String stockClassificationType;
	
	@Column(name="stock_class_name")
	private String stockClassificationName;
	
	public String getStockClassificationTypeId() {
		return stockClassificationTypeId;
	}
	public void setStockClassificationTypeId(String stockClassificationTypeId) {
		this.stockClassificationTypeId = stockClassificationTypeId;
	}
	public String getStockClassificationType() {
		return stockClassificationType;
	}
	public void setStockClassificationType(String stockClassificationType) {
		this.stockClassificationType = stockClassificationType;
	}
	public String getStockClassificationName() {
		return stockClassificationName;
	}
	public void setStockClassificationName(String stockClassificationName) {
		this.stockClassificationName = stockClassificationName;
	}
	
}
