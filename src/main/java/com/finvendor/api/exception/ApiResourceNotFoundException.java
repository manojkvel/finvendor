package com.finvendor.api.exception;

/**
 * API Exception
 *
 * @author Ayush
 */
public class ApiResourceNotFoundException extends Exception {
    public ApiResourceNotFoundException() {
        super();
    }

    public ApiResourceNotFoundException(String message) {
        super(message);
    }

    public ApiResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
