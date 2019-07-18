package com.finvendor.common.enums;

public enum ApiMessageEnum {
    INVALID_USER_NAME("usr-001","Invalid user name"),
    CREATE_SUBSCRIPTION("subs-0001","User subscription created successfully"),
    UPDATE_SUBSCRIPTION_SUCCESS("subs-0002","User subscription updated successfully"),
    FAILED_TO_UPDATE_SUBSCRIPTION("subs-0004","Failed to update user subscription - could be following possibility 1. User/Subscription does not exist 2. Subscription is already updated"),
    GET_SUBSCRIPTION("subs-0005","User subscriptions retrieved successfully"),
    FAILED_TO_FIND_SUBSCRIPTION("subs-0006","Failed to retrieved user subscriptions - Invalid user"),
    FAILED_TO_SAVE_SUBSCRIPTION("subs-0007","Failed to save user subscriptions - Invalid user"),
    LOGIN_SUCCESS("lgn-0008","Login success"),

    SUBSCRIPTION_ID_NOT_EXIST("subs-0009",""),
    USER_NOT_EXIST("subs-0010","User does not exist"),
    PAYMENT_ALREADY_VERIFIED("subs-0011","User already paid the subscription amount")





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
