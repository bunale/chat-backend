package com.chat.backend.module.user.domain.vo;

import lombok.Data;

/**
 * 分页查询用户响应类
 *
 * @author liujie
 * @since 2024/12/2
 */
@Data
public class UserPageVO {

    /**
     * 用户唯一标识
     */
    private String userId;

    /**
     * 用户名
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 是否已添加当前登录用户为好友
     */
    private boolean friendFlag;

}
