package com.chat.backend.exception;

import lombok.Getter;

/**
 * 错误码枚举类
 * @author ：bunale
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
     * 二级宏观错误码，用户注册错误
     */
    USER_REGISTER_ERROR("A0100", "User registered error"),
    /**
     * 用户已存在
     */
    USER_ALREADY_EXIST("A0101", "User already exist"),
    /**
     * 注册验证码错误
     */
    REGISTER_CODE_ERROR("A0102", "Register code error"),
    /**
     * 二级宏观错误码，用户登录错误
     */
    USER_LOGIN_ERROR("A0200", "User login error"),
    /**
     * 二级宏观错误码，参数错误
     */
    PARAMETER_ERROR("A0300", "Parameter error"),



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
