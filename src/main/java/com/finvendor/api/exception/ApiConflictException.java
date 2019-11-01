package com.finvendor.api.exception;

/**
 * API Exception
 *
 * @author Ayush
 */
public class ApiConflictException extends Exception {
    public ApiConflictException() {
        super();
    }

    public ApiConflictException(String message) {
        super(message);
    }

    public ApiConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiConflictException(Throwable cause) {
        super(cause);
    }
}
