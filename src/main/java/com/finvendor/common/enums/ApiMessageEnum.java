package com.finvendor.common.enums;

public enum ApiMessageEnum {
    SUCCESS("fv-200","Success"),
    CREATED("fv-201","Created"),
    CONFLICT("fv-409","Conflict"),
    INTERNAL_SERVER_ERROR("fv-500","Internal Server Error"),
    BAD_REQUEST("fv-400","Validation failed"),
    RESOURCE_NOT_FOUND("fv-404","Resource not found"),
    INVALID_USER_NAME("usr-001","Invalid user name"),
    CREATE_SUBSCRIPTION("subs-0001","User subscription created successfully"),
    UPDATE_SUBSCRIPTION_SUCCESS("Fv-200","User subscription updated successfully"),
    FAILED_TO_UPDATE_SUBSCRIPTION("Fv-500","Failed to update user subscription - could be following possibility 1. User/Subscription does not exist 2. Subscription is already updated"),
    GET_SUBSCRIPTION("subs-0005","User subscriptions retrieved successfully"),
    FAILED_TO_FIND_SUBSCRIPTION("subs-0006","Failed to retrieved user subscriptions - Invalid user"),
    FAILED_TO_SAVE_SUBSCRIPTION("subs-0007","Failed to save user subscriptions - Invalid user"),
    LOGIN_SUCCESS("lgn-0008","Login success"),

    SUBSCRIPTION_ID_NOT_EXIST("Fv-404","Subscription Id does not exist."),
    USER_NOT_EXIST("subs-0010","User does not exist"),
    PAYMENT_ALREADY_VERIFIED("Fv-409","User already paid the subscription amount"),
    FAILED_TO_FIND_USER_SUBSCRIPTIONS("subs-0012","Failed to retrieved user subscriptions - Invalid user"),
    GET_SUBSCRIPTION_TYPE("subs-0013","User subscription type retrieved successfully"),
    DUP_SUBSCRIPTION("subs-0014","User subscription already exist."),
    USER_PROFILE_SUBSCRIPTION("subs-0015","User profile subscription details retrieved successfully."),
    VERIFICATION_TYPE("subs-0016","Subscription type should be ACTIVE or TERMINATE."),



    ;
    private String code;
    private String message;

    ApiMessageEnum(String code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return message;
    }
}
