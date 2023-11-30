package com.auction.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseHeader {
    private final boolean success;
    private final ResponseCode code;
    private final String message;

    public ResponseHeader() {
        this.success = false;
        this.code = null;
        this.message = null;
    }

    public static ResponseHeader success() {
        return new ResponseHeader(true, ResponseCode.SUCCESS, "");
    }

    public static ResponseHeader created() {
        return new ResponseHeader(true, ResponseCode.CREATED, "");
    }

    public static ResponseHeader noContent() {
        return new ResponseHeader(true, ResponseCode.NO_CONTENT, "");
    }

    public static ResponseHeader fail(ResponseCode errorCode, String errorMessage) {
        return new ResponseHeader(false, errorCode, errorMessage);
    }
}
