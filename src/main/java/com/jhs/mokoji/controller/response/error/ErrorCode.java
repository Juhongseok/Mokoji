package com.jhs.mokoji.controller.response.error;

public enum ErrorCode {
    INVALID_INPUT_VALUE(400, " Invalid Input Value"),

    USER_NOT_FOUND(400, "User Not Found"),
    NOTICE_NOT_FOUND(400, "Notice Not Found"),
    GROUP_NOT_FOUND(400, "Group Not Found"),
    DUPLICATE_USER(400, "This User Is Duplicated"),
    ;

    private int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}