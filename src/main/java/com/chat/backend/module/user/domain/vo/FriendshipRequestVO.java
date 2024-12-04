package com.chat.backend.module.user.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 好友请求响应类
 *
 * @author 14110
 * @since 2024-11-27
 */
@Data
public class FriendshipRequestVO {

    private Long id;

    private String initiator;
    private String initiatorUsername;
    private String initiatorAvatar;

    private String receiver;

    /**
     * 关系状态 0: 待确认, 1: 已接受, 2: 已拒绝
     */
    private Integer status;

    private String remark;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

}
