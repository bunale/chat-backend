package com.chat.backend.module.user.domain.param;

import lombok.Data;

/**
 * 用户注册参数类
 *
 * @author bunale
 * @since 2024/11/17
 */
@Data
public class UserRegisterParam {

    private String email;

    private String password;

    private String verificationCode;

}
