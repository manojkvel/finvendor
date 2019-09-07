package com.finvendor.api.exception;

/**
 * API Exception
 *
 * @author Ayush
 */
public class ApiBadRequestException extends Exception {
    public ApiBadRequestException() {
        super();
    }

    public ApiBadRequestException(String message) {
        super(message);
    }

    public ApiBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiBadRequestException(Throwable cause) {
        super(cause);
    }
}
