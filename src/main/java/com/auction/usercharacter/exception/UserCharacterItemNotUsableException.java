package com.auction.usercharacter.exception;

import com.auction.common.constant.ErrorMessage;

public class UserCharacterItemNotUsableException extends RuntimeException {

    public UserCharacterItemNotUsableException() {
        super(ErrorMessage.ITEM_NOT_USABLE.getMessage());
    }
}
