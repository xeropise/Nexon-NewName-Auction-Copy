package com.auction.character.exception;

import com.auction.common.constant.ErrorMessage;

public class CharacterItemNotUsableException extends RuntimeException {

    public CharacterItemNotUsableException() {
        super(ErrorMessage.ITEM_NOT_USABLE.getMessage());
    }
}
