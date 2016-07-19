package com.finvendor.model;

public class VendorSearchResult {
	
	private String vendorId;
	private String vendorName;
	private String vendorCompany;
	private String vendorCountry;
	private String vendorType;
	
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
	public String getVendorCompany() {
		return vendorCompany;
	}
	public void setVendorCompany(String vendorCompany) {
		this.vendorCompany = vendorCompany;
	}
	public String getVendorCountry() {
		return vendorCountry;
	}
	public void setVendorCountry(String vendorCountry) {
		this.vendorCountry = vendorCountry;
	}
	public String getVendorType() {
		return vendorType;
	}
	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vendorCompany == null) ? 0 : vendorCompany.hashCode());
		result = prime * result + ((vendorCountry == null) ? 0 : vendorCountry.hashCode());
		result = prime * result + ((vendorId == null) ? 0 : vendorId.hashCode());
		result = prime * result + ((vendorName == null) ? 0 : vendorName.hashCode());
		result = prime * result + ((vendorType == null) ? 0 : vendorType.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VendorSearchResult other = (VendorSearchResult) obj;
		if (vendorCompany == null) {
			if (other.vendorCompany != null)
				return false;
		} else if (!vendorCompany.equals(other.vendorCompany))
			return false;
		if (vendorCountry == null) {
			if (other.vendorCountry != null)
				return false;
		} else if (!vendorCountry.equals(other.vendorCountry))
			return false;
		if (vendorId == null) {
			if (other.vendorId != null)
				return false;
		} else if (!vendorId.equals(other.vendorId))
			return false;
		if (vendorName == null) {
			if (other.vendorName != null)
				return false;
		} else if (!vendorName.equals(other.vendorName))
			return false;
		if (vendorType == null) {
			if (other.vendorType != null)
				return false;
		} else if (!vendorType.equals(other.vendorType))
			return false;
		return true;
	}
}
