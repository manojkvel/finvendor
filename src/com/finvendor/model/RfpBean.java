package com.finvendor.model;

import java.sql.Timestamp;

public class RfpBean {
	
	private String rfpId;
	private String consumerId;
	private String rfpTitle;
	private String rfpShortDesc;
	private String rfpDetailedDesc;
	private Timestamp rfpCreateddate;
	private Timestamp rfpEnddate;
	private boolean rfpClosed;
	private Timestamp rfpClosedDate;
	
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
	public Timestamp getRfpCreateddate() {
		return rfpCreateddate;
	}
	public void setRfpCreateddate(Timestamp rfpCreateddate) {
		this.rfpCreateddate = rfpCreateddate;
	}
	public Timestamp getRfpEnddate() {
		return rfpEnddate;
	}
	public void setRfpEnddate(Timestamp rfpEnddate) {
		this.rfpEnddate = rfpEnddate;
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
}
