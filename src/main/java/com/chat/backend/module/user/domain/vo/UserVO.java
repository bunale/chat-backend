package com.chat.backend.module.user.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 分页查询用户响应类
 *
 * @author liujie
 * @since 2024/12/2
 */
@Data
public class UserVO {

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
     * 用户状态，1：正常，2：禁用
     */
    private Integer status;

    /**
     * 创建人
     */
    private String createUserId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 上一次修改人
     */
    private String lastUpdateUserId;

    /**
     * 上一次修改时间
     */
    private LocalDateTime lastUpdateTime;
}
