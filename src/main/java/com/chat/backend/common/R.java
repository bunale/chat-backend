package com.chat.backend.common;

import com.chat.backend.exception.ErrorCode;
import lombok.Getter;

/**
 * @author ：bunale
 * @since ：Created in 2024/11/17 11:29
 */
@Getter
public class R<T> {
    private final String code;
    private final String message;
    private final T data;

    // 私有构造函数，防止外部直接实例化
    private R(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> R<T> ok(T data) {
        return new R<>(ErrorCode.SUCCESS.getCode(), "成功", data);
    }

    public static R<Void> ok() {
        return new R<>(ErrorCode.SUCCESS.getCode(), "成功", null);
    }

    public static <T> R<T> failure(ErrorCode errorCode, String message) {
        return new R<>(errorCode.getCode(), message, null);
    }

    public static <T> R<T> failure(ErrorCode errorCode) {
        return new R<>(errorCode.getCode(), errorCode.getMessage(), null);
    }
}
