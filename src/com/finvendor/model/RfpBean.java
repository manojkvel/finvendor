<<<<<<< HEAD
package com.finvendor.model;

import java.sql.Timestamp;
import java.util.List;

public class RfpBean {
	
	private String rfpId;
	private String consumerId;
	private String rfpTitle;
	private String rfpShortDesc;
	private String rfpDetailedDesc;
	private Timestamp rfpCreatedDate;
	private Timestamp rfpEndDate;
	private boolean rfpClosed;
	private Timestamp rfpClosedDate;
	private String targetVendorType;	
	private List<String> interestedVendor;
	private List<String> shortListedVendor;
	private List<String> finalizedVendor;
	
	public String getRfpId() {
		return rfpId;
	}
	public void setRfpId(String rfpId) {
		this.rfpId = rfpId;
	}
	public String getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}
	public String getRfpTitle() {
		return rfpTitle;
	}
	public void setRfpTitle(String rfpTitle) {
		this.rfpTitle = rfpTitle;
	}
	public String getRfpShortDesc() {
		return rfpShortDesc;
	}
	public void setRfpShortDesc(String rfpShortDesc) {
		this.rfpShortDesc = rfpShortDesc;
	}
	public String getRfpDetailedDesc() {
		return rfpDetailedDesc;
	}
	public void setRfpDetailedDesc(String rfpDetailedDesc) {
		this.rfpDetailedDesc = rfpDetailedDesc;
	}
	public Timestamp getRfpCreatedDate() {
		return rfpCreatedDate;
	}
	public void setRfpCreateddate(Timestamp rfpCreatedDate) {
		this.rfpCreatedDate = rfpCreatedDate;
	}
	public Timestamp getRfpEndDate() {
		return rfpEndDate;
	}
	public void setRfpEnddate(Timestamp rfpEndDate) {
		this.rfpEndDate = rfpEndDate;
	}
	public boolean isRfpClosed() {
		return rfpClosed;
	}
	public void setRfpClosed(boolean rfpClosed) {
		this.rfpClosed = rfpClosed;
	}
	public Timestamp getRfpClosedDate() {
		return rfpClosedDate;
	}
	public void setRfpClosedDate(Timestamp rfpClosedDate) {
		this.rfpClosedDate = rfpClosedDate;
	}
	public List<String> getInterestedVendor() {
		return interestedVendor;
	}
	public void setInterestedVendor(List<String> interestedVendor) {
		this.interestedVendor = interestedVendor;
	}
	public List<String> getShortListedVendor() {
		return shortListedVendor;
	}
	public void setShortListedVendor(List<String> shortListedVendor) {
		this.shortListedVendor = shortListedVendor;
	}
	public List<String> getFinalizedVendor() {
		return finalizedVendor;
	}
	public void setFinalizedVendor(List<String> finalizedVendor) {
		this.finalizedVendor = finalizedVendor;
	}
	public String getTargetVendorType() {
		return targetVendorType;
	}
	public void setTargetVendorType(String targetVendorType) {
		this.targetVendorType = targetVendorType;
	}
	public void setRfpCreatedDate(Timestamp rfpCreatedDate) {
		this.rfpCreatedDate = rfpCreatedDate;
	}
	public void setRfpEndDate(Timestamp rfpEndDate) {
		this.rfpEndDate = rfpEndDate;
	}
}
=======
package com.finvendor.model;

import java.sql.Timestamp;
import java.util.List;

public class RfpBean {
	
	private String rfpId;
	private String consumerId;
	private String rfpTitle;
	private String rfpShortDesc;
	private String rfpDetailedDesc;
	private Timestamp rfpCreatedDate;
	private Timestamp rfpEndDate;
	private boolean rfpClosed;
	private Timestamp rfpClosedDate;
	private String targetVendorType;	
	private List<String> interestedVendor;
	private List<String> shortListedVendor;
	private List<String> finalizedVendor;
	
	public String getRfpId() {
		return rfpId;
	}
	public void setRfpId(String rfpId) {
		this.rfpId = rfpId;
	}
	public String getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}
	public String getRfpTitle() {
		return rfpTitle;
	}
	public void setRfpTitle(String rfpTitle) {
		this.rfpTitle = rfpTitle;
	}
	public String getRfpShortDesc() {
		return rfpShortDesc;
	}
	public void setRfpShortDesc(String rfpShortDesc) {
		this.rfpShortDesc = rfpShortDesc;
	}
	public String getRfpDetailedDesc() {
		return rfpDetailedDesc;
	}
	public void setRfpDetailedDesc(String rfpDetailedDesc) {
		this.rfpDetailedDesc = rfpDetailedDesc;
	}
	public Timestamp getRfpCreatedDate() {
		return rfpCreatedDate;
	}
	public void setRfpCreateddate(Timestamp rfpCreatedDate) {
		this.rfpCreatedDate = rfpCreatedDate;
	}
	public Timestamp getRfpEndDate() {
		return rfpEndDate;
	}
	public void setRfpEnddate(Timestamp rfpEndDate) {
		this.rfpEndDate = rfpEndDate;
	}
	public boolean isRfpClosed() {
		return rfpClosed;
	}
	public void setRfpClosed(boolean rfpClosed) {
		this.rfpClosed = rfpClosed;
	}
	public Timestamp getRfpClosedDate() {
		return rfpClosedDate;
	}
	public void setRfpClosedDate(Timestamp rfpClosedDate) {
		this.rfpClosedDate = rfpClosedDate;
	}
	public List<String> getInterestedVendor() {
		return interestedVendor;
	}
	public void setInterestedVendor(List<String> interestedVendor) {
		this.interestedVendor = interestedVendor;
	}
	public List<String> getShortListedVendor() {
		return shortListedVendor;
	}
	public void setShortListedVendor(List<String> shortListedVendor) {
		this.shortListedVendor = shortListedVendor;
	}
	public List<String> getFinalizedVendor() {
		return finalizedVendor;
	}
	public void setFinalizedVendor(List<String> finalizedVendor) {
		this.finalizedVendor = finalizedVendor;
	}
	public String getTargetVendorType() {
		return targetVendorType;
	}
	public void setTargetVendorType(String targetVendorType) {
		this.targetVendorType = targetVendorType;
	}
	public void setRfpCreatedDate(Timestamp rfpCreatedDate) {
		this.rfpCreatedDate = rfpCreatedDate;
	}
	public void setRfpEndDate(Timestamp rfpEndDate) {
		this.rfpEndDate = rfpEndDate;
	}
}
>>>>>>> origin/master
