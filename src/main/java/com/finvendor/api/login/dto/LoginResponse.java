package com.finvendor.api.login.dto;

import com.finvendor.common.response.ApiResponse;
import org.springframework.http.HttpStatus;

import java.util.List;

public class LoginResponse extends ApiResponse<String, List<SubscriptionDto>> {
    public LoginResponse(String code, String message, List<SubscriptionDto> data) {
        super(code, message, data, HttpStatus.OK);
    }
}
