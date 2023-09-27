package com.auction.user.exception;

public class AccountExistsException extends RuntimeException{
    public AccountExistsException(String message) {
        super(message);
    }
}
