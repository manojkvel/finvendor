package com.finvendor.api.exception;

/**
 * API Exception
 *
 * @author Ayush
 */
public class ApiInternalServerException extends Exception {
    public ApiInternalServerException() {
        super();
    }

    public ApiInternalServerException(String message) {
        super(message);
    }

    public ApiInternalServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiInternalServerException(Throwable cause) {
        super(cause);
    }
}
