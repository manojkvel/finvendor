package com.finvendor.server.metrics.dto;

import java.io.Serializable;

public class MetricsDto implements Serializable {
	private static final long serialVersionUID = -3519208169508030576L;

	private String userName;
	
	private String request;

	private String count;

	private String month;
	private String year;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}


	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
}
