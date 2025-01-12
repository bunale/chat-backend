package com.chat.backend.module.message.domain.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

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

    @Id(keyType = KeyType.Auto)
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
     * 最后打开会话的时间
     */
    private LocalDateTime lastOpenTime;

    /**
     * 会话排序时间
     */
    private LocalDateTime orderTime;

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
