package com.chat.backend.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ：liujie
 * @since ：Created in 2024/11/17 11:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {
    private final ErrorCode code;
    private final String message;

    public BaseException(ErrorCode code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}