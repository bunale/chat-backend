package com.chat.backend.exception;

import lombok.Getter;

/**
 * 错误码枚举类
 * @author ：liujie
 * @since ：Created in 2024/11/17 11:22
 */
@Getter
public enum ErrorCode {
    /**
     * 成功
     */
    SUCCESS("00000", "Success"),

    /**
     * 用户客户端错误
     */
    USER_CLIENT_ERROR("A0001", "User client error"),

    /**
     * 系统内部错误
     */
    SYSTEM_ERROR("B0001", "System error"),

    /**
     * 第三方服务错误
     */
    THIRD_PARTY_ERROR("C0001", "Third party error");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
