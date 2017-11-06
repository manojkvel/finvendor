package com.finvendor.exception;

public class ApplicationException extends Exception{

	private static final long serialVersionUID = 6434420819120625216L;
	
	public ApplicationException(){
		super();
	}
	
	public ApplicationException(String exceptionMessage){
		super(exceptionMessage);
	}

}
