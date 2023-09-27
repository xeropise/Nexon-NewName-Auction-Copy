package com.auction.common.model;


import lombok.Data;
import lombok.Getter;

import java.util.HashMap;

@Data
@Getter
public class ApiResponse<T> {
    private final ResponseHeader summary;
    private final T body;

    public static <T> ApiResponse success(T body) {
        return new ApiResponse<>(ResponseHeader.success(), body);
    }

    public static <T> ApiResponse fail(ResponseCode errorCode, String errorMessage) {
        return new ApiResponse(ResponseHeader.fail(errorCode, errorMessage), new HashMap<Object, Object>());
    }

    public static <T> ApiResponse fail(ResponseCode errorCode, String errorMessage, T body) {
        return new ApiResponse(ResponseHeader.fail(errorCode, errorMessage), body);
    }
}
