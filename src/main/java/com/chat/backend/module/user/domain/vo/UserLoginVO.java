package com.chat.backend.module.user.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户登录响应类
 *
 * @author bunale
 * @since 2024/11/17
 */
@Data
public class UserLoginVO {

    private String token;

    private String userId;

    private String name;

    private String avatar;

    private String email;

    private List<String> roles;

    private LocalDateTime createTime;
}
