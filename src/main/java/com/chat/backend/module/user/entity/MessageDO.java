package com.chat.backend.module.user.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

import java.io.Serial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 会话消息表 实体类。
 *
 * @author 14110
 * @since 2024-12-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("chat_message")
public class MessageDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 消息ID
     */
    @Id(keyType = KeyType.Auto)
    private Long messageId;

    /**
     * 会话ID
     */
    private Long conversationId;

    /**
     * 发送者ID
     */
    private String senderId;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息类型，1：文本，2：图片，3：语音，4：通话记录，5：位置，6：链接，7：文件
     */
    private Integer type;

    /**
     * 是否已读
     */
    private Boolean isRead;

    /**
     * 发送时间
     */
    private LocalDateTime sentAt;

    /**
     * 额外信息
     */
    private String metadata;

    /**
     * 是否删除
     */
    private Boolean deleteFlag;

}
