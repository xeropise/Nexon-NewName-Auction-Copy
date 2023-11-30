package com.auction.user.exception;

import com.auction.common.constant.ErrorMessage;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException() {
        super(ErrorMessage.ROLE_NOT_FOUND.getMessage());
    }
}
