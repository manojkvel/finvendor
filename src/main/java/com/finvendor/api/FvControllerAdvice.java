package com.finvendor.api;

import com.finvendor.api.exception.ApiBadRequestException;
import com.finvendor.api.exception.ApiInternalServerException;
import com.finvendor.api.exception.ApiResourceNotFoundException;
import com.finvendor.api.webutil.WebUtils;
import com.finvendor.common.response.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import static com.finvendor.api.webutil.WebUtils.*;

/**
 * @author  ayush
 */
@ControllerAdvice
public class FvControllerAdvice extends ResponseEntityExceptionHandler {

    /**
     * This exception will occurred when any validation is failed for object under @RequestBody
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return WebUtils.getInvalidMethodArgResponseEntity(exception);
    }

    /**
     * In case of any validation failures (related to path variable and request param,
     * Spring will throw ConstraintViolationException.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse> constraintViolationException(ConstraintViolationException e) {
        return WebUtils.getConstraintViolationResponseEntity(e);
    }

    /**
     * Handle Exception
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleGeneralException(Exception e) {
        return getInternalServerErrorResponse(e);
    }

    /**
     * Handle resource not found
     */
    @ExceptionHandler({ ApiResourceNotFoundException.class})
    public ResponseEntity<?> handleResourceNotFoundError(ApiResourceNotFoundException e) {
        return getResourceNotFoundErrorResponse(e);
    }

    /**
     * Handle bad request
     */
    @ExceptionHandler({ ApiBadRequestException.class})
    public ResponseEntity<?> handleBadRequestError(ApiBadRequestException e) {
        return getBadRequestErrorResponse(e);
    }

    /**
     * Handle Internal server error
     */
    @ExceptionHandler({ ApiInternalServerException.class})
    public ResponseEntity<?> handleInternalServerError(ApiInternalServerException e) {
        return getInternalServerErrorResponse(e);
    }
}
