package com.chat.backend.module.user.domain.resp;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户登录响应类
 * @author bunale
 * @since 2024/11/17
 */
@Data
public class UserLoginResp {

    private String token;

    private String userId;

    private String username;

    private String avatar;

    private String email;

    private List<String> roles;

    private LocalDateTime createTime;
}
