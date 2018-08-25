package com.finvendor.serverwebapi.exception;

import com.finvendor.common.exception.FvRootRuntimeException;

//TDB: 
/**
 * 
 * @author ayush
 */
public class WebApiException extends FvRootRuntimeException {

	private static final long serialVersionUID = -8878588404537685332L;

    public WebApiException() {
        super();
    }
 
    public WebApiException(final String message) {
        super(message);
    }

    public WebApiException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public WebApiException(final Throwable cause) {
        super(cause);
    }

}
