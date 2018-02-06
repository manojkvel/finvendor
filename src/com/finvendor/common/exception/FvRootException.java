package com.finvendor.common.exception;import java.io.Serializable;/**
 * Super Type of all Application checked excpetion (abstract class), To be sub * classed by both technical and business exception *  * @author Ayush */
public abstract class FvRootException extends Exception implements Serializable {	private static final long serialVersionUID = 1L;	public FvRootException() {		super();	}
	public FvRootException(final String message) {		super(message);	}
	public FvRootException(final Throwable cause) {		super(cause);	}
	public FvRootException(final String message, final Throwable cause) {		super(message, cause);	}
	final public Exception getOriginalCause() {		Throwable res = this;		Throwable e = this;		while ((e = e.getCause()) != null) {			res = e;		}		return (Exception) res;	}}
