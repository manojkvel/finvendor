package com.finvendor.common.enums;

public enum ApiMessageEnum {
    SUCCESS("fv-200","Success"),
    INVALID_USER_NAME("usr-001","Invalid user name"),
    CREATE_SUBSCRIPTION("subs-0001","User subscription created successfully"),
    UPDATE_SUBSCRIPTION_SUCCESS("subs-0002","User subscription updated successfully"),
    FAILED_TO_UPDATE_SUBSCRIPTION("subs-0004","Failed to update user subscription - could be following possibility 1. User/Subscription does not exist 2. Subscription is already updated"),
    GET_SUBSCRIPTION("subs-0005","User subscriptions retrieved successfully"),
    FAILED_TO_FIND_SUBSCRIPTION("subs-0006","Failed to retrieved user subscriptions - Invalid user"),
    FAILED_TO_SAVE_SUBSCRIPTION("subs-0007","Failed to save user subscriptions - Invalid user"),
    LOGIN_SUCCESS("lgn-0008","Login success"),

    SUBSCRIPTION_ID_NOT_EXIST("subs-0009","Unable to activate/terminate subscription, Subscription Id does not exist."),
    USER_NOT_EXIST("subs-0010","User does not exist"),
    PAYMENT_ALREADY_VERIFIED("subs-0011","User already paid the subscription amount"),
    FAILED_TO_FIND_USER_SUBSCRIPTIONS("subs-0012","Failed to retrieved user subscriptions - Invalid user"),
    GET_SUBSCRIPTION_TYPE("subs-0013","User subscription type retrieved successfully"),
    DUP_SUBSCRIPTION("subs-0014","User subscription already exist."),
    USER_PROFILE_SUBSCRIPTION("subs-0015","User profile subscription details retrieved successfully."),
    VERIFICATION_TYPE("subs-0016","Subscription type should be ACTIVE or TERMINATE."),
    RESOURCE_NOT_FOUND("fv-7777","Resource not found"),



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
