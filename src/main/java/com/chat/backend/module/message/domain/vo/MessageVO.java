package com.chat.backend.module.message.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 会话消息表 实体类。
 *
 * @author 14110
 * @since 2024-12-01
 */
@Data
public class MessageVO {

    /**
     * 消息ID
     */
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
    private Boolean readFlag;

    /**
     * 发送时间
     */
    private LocalDateTime sentAt;

    /**
     * 额外信息
     */
    private String metadata;

}
