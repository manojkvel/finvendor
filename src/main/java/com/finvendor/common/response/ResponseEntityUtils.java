package com.finvendor.common.response;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class ResponseEntityUtils {

    public static ResponseEntity<Object> getInvalidMethodArgResponseEntity(MethodArgumentNotValidException exception) {
        List<String> errMessages = new ArrayList<>();
        BindingResult bindingResult = exception.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        for (ObjectError oe : allErrors) {
            String defaultMessage = oe.getDefaultMessage();
            errMessages.add(defaultMessage);
        }
        String[] errStrArr = errMessages.toArray(new String[1]);
        Object baseResponseDto = new ApiResponse<>("in-001", errStrArr, null,BAD_REQUEST);
        return new ResponseEntity<>(baseResponseDto, ((ApiResponse)baseResponseDto).getHttpStatus());
    }

    public static ResponseEntity<ApiResponse> getConstraintViolationResponseEntity(ConstraintViolationException e) {
        String message = e.getMessage().substring(e.getMessage().indexOf(":") + 2);
        ApiResponse<String, String> validationErr = new ApiResponse<>("in-002", message, null,BAD_REQUEST);
        return new ResponseEntity<ApiResponse>(validationErr, validationErr.getHttpStatus());
    }
}
