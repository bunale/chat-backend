package com.chat.backend.module.user.domain.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 用户登录参数类
 *
 * @author bunale
 * @since 2024/11/17
 */
@Data
public class UserLoginParam {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
