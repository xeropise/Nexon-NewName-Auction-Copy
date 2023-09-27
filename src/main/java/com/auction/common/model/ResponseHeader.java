package com.auction.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseHeader {
    private final boolean isSuccess;
    private final ResponseCode code;
    private final String message;

    public static ResponseHeader success() {
        return new ResponseHeader(true, ResponseCode.SUCCESS, "");
    }

    public static ResponseHeader fail(ResponseCode errorCode, String errorMessage) {
        return new ResponseHeader(false, errorCode, errorMessage);
    }
}
