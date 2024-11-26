package com.chat.backend.exception;

import com.chat.backend.base.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ：bunale
 * @since ：Created in 2024/11/17 11:30
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {BaseException.class})
    public R<?> handleBaseException(BaseException e) {
        log.error("error: {}, message: {}", e.getClass(), e.getMessage());
        return R.failure(e.getCode());
    }

    @ExceptionHandler(value = {Exception.class})
    public R<?> handleException(Exception e) {
        log.error("unexpected error: {}, message: {}", e.getClass(), e.getMessage());
        if (e.getCause() != null) {
            log.error("cause: {}", e.getCause().getMessage());
        }

        return R.failure(ErrorCode.SYSTEM_ERROR, e.getMessage() == null? ErrorCode.SYSTEM_ERROR.getMessage() : e.getMessage());
    }
}
