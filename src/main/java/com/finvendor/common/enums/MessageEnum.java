package com.finvendor.common.enums;

public enum MessageEnum {
    INVALID_USER_NAME("usr-001","Invalid user name"),
    CREATE_SUBSCRIPTION("subs-0001","User subscription created successfully"),
    UPDATE_SUBSCRIPTION("subs-0002","User subscription updated successfully"),
    FAILED_TO_UPDATE_SUBSCRIPTION("subs-0003","Failed to update user subscription"),
    GET_SUBSCRIPTION("subs-0004","User subscriptions retrieved successfully"),
    FAILED_TO_FIND_SUBSCRIPTION("subs-0005","Failed to retrieved user subscriptions")





    ;
    private String code;
    private String message;

    MessageEnum(String code, String msg) {
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
