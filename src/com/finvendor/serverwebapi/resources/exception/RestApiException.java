package com.finvendor.serverwebapi.resources.exception;

//TDB: 
/**
 * 
 * @author ayush
 */
public class RestApiException extends RuntimeException {

	private static final long serialVersionUID = -8878588404537685332L;

    public RestApiException() {
        super();
    }
 
    public RestApiException(String message) {
        super(message);
    }

    public RestApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestApiException(Throwable cause) {
        super(cause);
    }

}
