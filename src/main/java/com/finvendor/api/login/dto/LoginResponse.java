package com.finvendor.api.login.dto;

import com.finvendor.api.user.dto.UserSubscriptionDto;
import com.finvendor.common.response.ApiResponse;
import org.springframework.http.HttpStatus;

import java.util.List;

public class LoginResponse extends ApiResponse<String,  List<UserSubscriptionDto>> {
    public LoginResponse(String code, String message, List<UserSubscriptionDto> data) {
        super(code, message, data, HttpStatus.OK);
    }
}
