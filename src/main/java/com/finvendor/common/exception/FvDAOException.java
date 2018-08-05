package com.finvendor.common.exception;/** *  * @author ayush * */
public class FvDAOException extends FvRootRuntimeException {
	private static final long serialVersionUID = 1L;
	public FvDAOException() {		super();	}
	public FvDAOException(final String message) {		super(message);	}
	public FvDAOException(final Throwable cause) {		super(cause);	}
	public FvDAOException(final String message, final Throwable cause) {		super(message, cause);	}}
