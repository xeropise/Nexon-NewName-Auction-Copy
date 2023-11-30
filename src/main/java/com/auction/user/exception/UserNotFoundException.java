package com.auction.user.exception;

import com.auction.common.constant.ErrorMessage;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super(ErrorMessage.USER_NOT_FOUND.getMessage());
    }
}
