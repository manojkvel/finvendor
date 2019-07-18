package com.finvendor.api.subscription.controller;

import com.finvendor.common.response.ApiResponse;
import com.finvendor.common.response.ResponseEntityUtils;
import com.finvendor.common.util.ErrorUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import static com.finvendor.common.exception.ExceptionEnum.SUBSCRIPTION;

/**
 * @author  ayush
 */
@ControllerAdvice(assignableTypes = SubscriptionController.class)
public class SubscriptionControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntityUtils.getInvalidMethodArgResponseEntity(e);
    }

    /**
     * In case of any validation failures (related to path variable and request param,
     * Spring will throw ConstraintViolationException.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse> constraintViolationException(ConstraintViolationException e) {
        return ResponseEntityUtils.getConstraintViolationResponseEntity(e);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleException(Exception e) {
        ErrorUtil.logError("SUBSCRIPTION Controller Error :", e);
        return ErrorUtil.getError(SUBSCRIPTION.getCode(), SUBSCRIPTION.getUserMessage(), e);
    }
}
