package com.chat.backend.module.user.domain.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 用户注册参数类
 *
 * @author bunale
 * @since 2024/11/17
 */
@Data
public class UserRegisterParam {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String verificationCode;

}
