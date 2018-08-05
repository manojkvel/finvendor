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
 
    public WebApiException(String message) {
        super(message);
    }

    public WebApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebApiException(Throwable cause) {
        super(cause);
    }

}
