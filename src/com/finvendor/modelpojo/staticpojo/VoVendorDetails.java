package com.finvendor.modelpojo.staticpojo;

/**
 * 
 * @author ayush on Feb 17, 2018
 */
public class VoVendorDetails {
	private String vendorId;
	private String vendorName;
	
	public VoVendorDetails() {
	}

	public VoVendorDetails(String vendorId, String vendorName) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
}
