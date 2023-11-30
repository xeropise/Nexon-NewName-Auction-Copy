package com.auction.user.exception;

import com.auction.common.constant.ErrorMessage;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
        super(ErrorMessage.USER_ALREADY_EXIST.getMessage());
    }
}
