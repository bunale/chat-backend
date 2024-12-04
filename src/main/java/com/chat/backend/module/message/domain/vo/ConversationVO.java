package com.chat.backend.module.message.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 会话表 实体类。
 *
 * @author 14110
 * @since 2024-12-01
 */
@Data
public class ConversationVO {

    private Long conversationId;

    /**
     * 会话类型，1：一对一，2：多人聊天
     */
    private Integer conversationType;

    /**
     * 最后一条消息的ID
     */
    private Long lastMessageId;

    /**
     * 最后一条消息的时间
     */
    private LocalDateTime lastMessageTime;

    /**
     * 会话标题
     */
    private String title;

    /**
     * 会话创建者ID
     */
    private String createdUserId;

    /**
     * 会话创建时间
     */
    private LocalDateTime createdTime;

}
