package com.finvendor.api.subscription.controller;

import com.finvendor.api.webutil.WebUtils;
import com.finvendor.common.response.ApiResponse;
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
import static com.finvendor.common.exception.ExceptionEnum.SUBSCRIPTION_VALIDATION_FAILED;

/**
 * @author  ayush
 */
@ControllerAdvice(assignableTypes = SubscriptionController.class)
public class SubscriptionControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return WebUtils.getInvalidMethodArgResponseEntity(e);
    }

    /**
     * In case of any validation failures (related to path variable and request param,
     * Spring will throw ConstraintViolationException.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse> constraintViolationException(ConstraintViolationException e) {
        return WebUtils.getConstraintViolationResponseEntity(e);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleException(Exception e) {
        ErrorUtil.logError("SUBSCRIPTION Controller Error :", e);
        return ErrorUtil.getError(SUBSCRIPTION.getCode(), SUBSCRIPTION.getUserMessage(), e);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<?> handleBadRequest(Exception e) {
        ApiResponse<String,Object> apiResponse = new ApiResponse<>(SUBSCRIPTION_VALIDATION_FAILED.getCode(), SUBSCRIPTION_VALIDATION_FAILED.getUserMessage(),
                null, HttpStatus.BAD_REQUEST);
        return WebUtils.buildResponseEntity(apiResponse);
    }
}
