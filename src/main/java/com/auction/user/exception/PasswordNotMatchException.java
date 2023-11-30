package com.auction.user.exception;

import com.auction.common.constant.ErrorMessage;

public class PasswordNotMatchException extends RuntimeException {
    public PasswordNotMatchException() {
        super(ErrorMessage.PASSWORD_NOT_MATCH.getMessage());
    }

    public PasswordNotMatchException(String message) {
        super(message);
    }
}
