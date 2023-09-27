package com.auction.common.model;

public enum ResponseCode {
    INTERNAL_ERROR,
    LOGIN_FAILED,
    NOT_FOUND,
    SUCCESS,
    CREATED,
    BAD_REQUEST,
    FORBIDDEN,


    // user
    ACCOUNT_EXISTS,
    PASSWORD_NOT_MATCH,
    USER_NOT_FOUND;


}
