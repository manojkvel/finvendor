package com.finvendor.common.exception;

/**
 * 
 * @author ayush on May 01, 2018
 */
public class ExceptionPojo {

	private String statusCode;
	private String userMessage;
	private String technicalMessage;

	public ExceptionPojo(String statusCode, String userMessage, String technicalMessage) {
		super();
		this.statusCode = statusCode;
		this.userMessage = userMessage;
		this.technicalMessage = technicalMessage;
	}

	public ExceptionPojo() {
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getTechnicalMessage() {
		return technicalMessage;
	}

	public void setTechnicalMessage(String technicalMessage) {
		this.technicalMessage = technicalMessage;
	}

}
