package com.finvendor.common.exception;

/**
 * Application checked excpetion businedd issue Use (Abstract class), subclass
 * this exception for each specific business exception
 * 
 * @author Ayush
 */

public abstract class FvBusinessException extends FvRootException {
	private static final long serialVersionUID = 1L;

	public FvBusinessException() {
		super();
	}

	public FvBusinessException(final String message) {
		super(message);
	}

	public FvBusinessException(final Throwable cause) {
		super(cause);
	}

	public FvBusinessException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
