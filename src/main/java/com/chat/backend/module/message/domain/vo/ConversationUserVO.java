package com.chat.backend.module.message.domain.vo;

import lombok.Data;

/**
 * 会话-人员信息VO类。
 *
 * @author bunale
 * @since 2024-12-01
 */
@Data
public class ConversationUserVO {

    private String userId;

    private String username;

    private String avatar;

    private Boolean creatorFlag;

    private Boolean adminFlag;
}
