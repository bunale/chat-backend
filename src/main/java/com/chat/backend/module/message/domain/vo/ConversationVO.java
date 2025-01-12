package com.chat.backend.module.message.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 会话表 实体类。
 *
 * @author bunale
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
     * 一对一会话时，双方的用户ID组成的Key，便于查询
     */
    private String userIdKey;

    /**
     * 最后一条消息的ID
     */
    private Long lastMessageId;

    /**
     * 最后一条消息的时间
     */
    private LocalDateTime lastMessageTime;

    /**
     * 最后一条消息的内容
     */
    private String lastMessageContent;

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

    /**
     * 参与者列表
     */
    private List<ConversationUserVO> users;
}
