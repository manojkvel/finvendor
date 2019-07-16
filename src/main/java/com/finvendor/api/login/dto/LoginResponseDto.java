package com.finvendor.api.login.dto;

import com.finvendor.common.response.BaseResponseDto;

import java.util.List;

public class LoginResponseDto extends BaseResponseDto<String, SubscriptionDto> {
    public LoginResponseDto(String code, String message, List<SubscriptionDto> data) {
        super(code, message, data);
    }
}
