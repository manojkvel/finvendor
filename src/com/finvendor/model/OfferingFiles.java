package com.finvendor.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="offeringfiles")
public class OfferingFiles implements Serializable {

	private static final long serialVersionUID = 120920151043L;
	
	@Id
    @Column(name="Offering_Files_id")
	@GeneratedValue
    private Integer offeringFilesId;
	public VendorOffering getVendorOffering() {
		return vendorOffering;
	}

	public void setVendorOffering(VendorOffering vendorOffering) {
		this.vendorOffering = vendorOffering;
	}

	@Column(name="file_name")
	private String fileName;
	
	@Column(name="description")
	private String description;
	
	
	@ManyToOne(targetEntity=SecurityType.class,fetch=FetchType.EAGER)
	@JoinColumn(name="security_type_id", nullable=true)
	private SecurityType securityType;
	
	@ManyToOne(targetEntity=VendorOffering.class,fetch=FetchType.LAZY)
	 @JoinColumn(name="vendor_offering_id", nullable=false)
	private VendorOffering vendorOffering;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="offeringFiles", cascade = CascadeType.ALL)
	private Set<FileFields> fileFields;



	public Integer getOfferingFilesId() {
		return offeringFilesId;
	}

	public void setOfferingFilesId(Integer offeringFilesId) {
		this.offeringFilesId = offeringFilesId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public SecurityType getSecurityType() {
		return securityType;
	}

	public void setSecurityType(SecurityType securityType) {
		this.securityType = securityType;
	}

	public Set<FileFields> getFileFields() {
		return fileFields;
	}

	public void setFileFields(Set<FileFields> fileFields) {
		this.fileFields = fileFields;
	}
	
	}
