package com.finvendor.modelpojo.staticpojo.wathlist.company;

import java.io.Serializable;
import java.util.Calendar;

/**
 * 
 * @author ayush on May 01, 2018
 */
public class AbstractHomePageSearchPojo implements Serializable {
	private static final long serialVersionUID = 5804595566308989887L;
	protected Integer companyId;
	protected String companyName;
	protected String userName;
	protected String currDate = String.valueOf(Calendar.getInstance().getTimeInMillis());

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCurrDate() {
		return currDate;
	}

	public void setCurrDate(String currDate) {
		this.currDate = currDate;
	}

	
}
