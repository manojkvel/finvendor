package com.finvendor.common.util;

import com.finvendor.common.exception.FvTechnicalException;
import com.finvendor.modelpojo.staticpojo.exception.ExceptionPojo;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author ayush
 */
public final class ErrorUtil {
    private static final Logger logger = LoggerFactory.getLogger(ErrorUtil .class.getName());

    private ErrorUtil() throws FvTechnicalException {
        throw new FvTechnicalException("ExceptionUtil class instantiation via Reflection is forbidden!");
    }

    public static final String getRootCause(Throwable th) {
        Throwable rootCause = ExceptionUtils.getRootCause(th);
        if (rootCause != null) {
            return rootCause.getMessage();
        } else {
            return th.getMessage();
        }
    }

    public static ExceptionPojo buildErrorMessage(String statusCode, String userMessage, Throwable e) {
        ExceptionPojo exceptionPojo = new ExceptionPojo();
        exceptionPojo.setStatusCode(statusCode);
        exceptionPojo.setUserMessage(userMessage);
        exceptionPojo.setTechnicalMessage(getRootCause(e));
        return exceptionPojo;
    }

    public static ExceptionPojo buildErrorMessage(String statusCode, String userMessage) {
        ExceptionPojo exceptionPojo = new ExceptionPojo();
        exceptionPojo.setStatusCode(statusCode);
        exceptionPojo.setUserMessage(userMessage);
        exceptionPojo.setTechnicalMessage("Internal server error!!");
        return exceptionPojo;
    }

    public static void logError(String source, Throwable th) {
        Throwable res = th;
        Throwable e = th;
        while ((e = e.getCause()) != null) {
            res = e;
        }
        String message = res.getMessage();
        String fullStackTrace = ExceptionUtils.getFullStackTrace(res);
        StringBuilder sb = new StringBuilder(500);
        sb.append("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        sb.append(source).append("\n");
        sb.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        sb.append("Root Cause Message - ").append(message).append("\n\n");
        sb.append("Stack Trace:\n").append(fullStackTrace).append("\n");

        logger.error(sb.toString());
    }

    public static ResponseEntity<?> getError(String code, String userMessage, Exception e) {
        return new ResponseEntity<ExceptionPojo>(ErrorUtil.buildErrorMessage(code, userMessage, e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<?> getError(String code, String userMessage) {
        return new ResponseEntity<ExceptionPojo>(ErrorUtil.buildErrorMessage(code, userMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
