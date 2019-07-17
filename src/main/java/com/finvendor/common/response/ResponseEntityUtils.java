package com.finvendor.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

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
        Object baseResponseDto = new BaseResponseDto<>("in-001", errStrArr, null);
        return new ResponseEntity<>(baseResponseDto, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<BaseResponseDto> getConstraintViolationResponseEntity(ConstraintViolationException e) {
        String message = e.getMessage().substring(e.getMessage().indexOf(":") + 2);
        BaseResponseDto<String, String> validationErr = new BaseResponseDto<>("in-002", message, null);
        return new ResponseEntity<BaseResponseDto>(validationErr, HttpStatus.BAD_REQUEST);
    }
}
