package com.finvendor.modelpojo.staticpojo;

public class StatusPojo {
	private String status;
	private String messge;

	public StatusPojo(String status, String messge) {
		super();
		this.status = status;
		this.messge = messge;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessge() {
		return messge;
	}

	public void setMessge(String messge) {
		this.messge = messge;
	}
}
