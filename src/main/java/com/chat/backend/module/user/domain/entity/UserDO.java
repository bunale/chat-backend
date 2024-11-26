package com.chat.backend.module.user.domain.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ：bunale
 * @since ：Created in 2024/11/16 17:48
 */
@Data
@Table("chat_user")
public class UserDO {

    /**
     * 主键ID
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 用户唯一标识
     */
    private String userId;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

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
    private String createUser_id;

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", status=" + status +
                ", createUser_id='" + createUser_id + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateUserId='" + lastUpdateUserId + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}
