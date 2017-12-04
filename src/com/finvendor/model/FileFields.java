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
@Table(name="filefields")
public class FileFields implements Serializable {

	private static final long serialVersionUID = 120920151043L;
	@Id
    @Column(name="FileFields_id")
	@GeneratedValue
    private Integer id;
	@Column(name="field_Name")
	private String fieldName;
	
	@Column(name="description")
	private String description;
	
	@Column(name="field_index")
	private String fieldIndex;
	
	
	@Column(name="field_format")
	private String fieldFormat;
	
	@Column(name="field_DataType")
	private String fieldDataType;
	
	@Column(name="field_MaxLength")
	private String fieldMaxLength;
	
	@ManyToOne(targetEntity=OfferingFiles.class,fetch=FetchType.EAGER)
	@JoinColumn(name="Offering_Files_id", nullable=false)
	private OfferingFiles offeringFiles;

	
	public String getFieldIndex() {
		return fieldIndex;
	}
	public void setFieldIndex(String fieldIndex) {
		this.fieldIndex = fieldIndex;
	}
	public String getFieldFormat() {
		return fieldFormat;
	}
	public void setFieldFormat(String fieldFormat) {
		this.fieldFormat = fieldFormat;
	}
	public String getFieldDataType() {
		return fieldDataType;
	}
	public void setFieldDataType(String fieldDataType) {
		this.fieldDataType = fieldDataType;
	}
	public String getFieldMaxLength() {
		return fieldMaxLength;
	}
	public void setFieldMaxLength(String fieldMaxLength) {
		this.fieldMaxLength = fieldMaxLength;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public OfferingFiles getOfferingFiles() {
		return offeringFiles;
	}
	public void setOfferingFiles(OfferingFiles offeringFiles) {
		this.offeringFiles = offeringFiles;
	}
}
