package com.finvendor.modelpojo.staticpojo;

/**
 * 
 * @author ayush on May 01, 2018
 */
public class StatusPojo {
	private String status;
	private String message;

	public StatusPojo(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
