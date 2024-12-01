package com.chat.backend.module.user.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;

import java.io.Serial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息附件表 实体类。
 *
 * @author 14110
 * @since 2024-12-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("chat_message_attachment")
public class MessageAttachmentDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 附件ID
     */
    @Id(keyType = KeyType.Auto)
    private Long attachmentId;

    /**
     * 消息ID
     */
    private Long messageId;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件路径或URL
     */
    private String filePath;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * MIME类型
     */
    private String mimeType;

}
