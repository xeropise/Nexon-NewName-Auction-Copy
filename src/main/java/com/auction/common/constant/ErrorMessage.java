package com.auction.common.constant;

public enum ErrorMessage {

    // user
    PASSWORD_NOT_MATCH("password not match"),
    ACCOUNT_NOT_FOUND("userAccount not found"),
    USER_ALREADY_EXIST("user already exist"),
    USER_NOT_FOUND("user not found"),

    // role
    ROLE_NOT_FOUND("role not found"),


    // bid


    // character, item
    ITEM_NOT_USABLE("item not usable");

    private String message;

    ErrorMessage(String message) {

    }

    public String getMessage() {
        return message;
    }
}
