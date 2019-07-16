package com.finvendor.common.enums;

public enum MessageEnum {
    INVALID_USER_NAME("usr-001","Invalid user name")






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

    public String getMessage() {
        return message;
    }
}
