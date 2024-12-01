package com.chat.backend.module.user.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

import java.io.Serial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 会话表 实体类。
 *
 * @author 14110
 * @since 2024-12-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("chat_conversation")
public class ConversationDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
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
