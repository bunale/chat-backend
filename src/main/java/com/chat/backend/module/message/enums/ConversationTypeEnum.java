package com.chat.backend.module.message.enums;

import lombok.Getter;

/**
 * 会话类型枚举
 *
 * @author bunale
 * @since 2025/1/12
 */
@Getter
public enum ConversationTypeEnum {

    ONE_TO_ONE(1, "一对一"),
    GROUP(2, "群聊"),
    ;

    private final Integer code;
    private final String description;

    ConversationTypeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static ConversationTypeEnum getEnumByCode(Integer code) {
        for (ConversationTypeEnum conversationTypeEnum : values()) {
            if (conversationTypeEnum.getCode().equals(code)) {
                return conversationTypeEnum;
            }
        }
        return null;
    }
}
