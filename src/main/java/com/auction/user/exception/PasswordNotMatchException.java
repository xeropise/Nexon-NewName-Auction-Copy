package com.auction.user.exception;

public class PasswordNotMatchException extends RuntimeException {
    public PasswordNotMatchException() {
        super("");
    }

    public PasswordNotMatchException(String message) {
        super(message);
    }
}
