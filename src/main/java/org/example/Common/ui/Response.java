package org.example.Common.ui;

import org.example.Common.domain.exception.ErrorCode;

public record Response<T>(Integer code, String msg, T value) {

    public static <T> Response<T> ok(T value) {
        return new Response<T>(0, "OK", value);
    }

    public static <T> Response<T> error(ErrorCode errorCode) {
        return new Response<>(errorCode.getCode(), errorCode.getMessage(), null);
    }
}
