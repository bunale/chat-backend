package com.chat.backend.module.user.domain.entity;

import lombok.Getter;

/**
 * 好友关系状态枚举
 *
 * @author bunale
 * @since 2024/12/04
 */
@Getter
public enum FriendshipStatus {
    /**
     * 好友关系状态
     */
    PENDING(0, "待确认"),
    ACCEPTED(1, "已接受"),
    REJECTED(2, "已拒绝");

    private final int code;
    private final String description;

    FriendshipStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static FriendshipStatus fromCode(int code) {
        for (FriendshipStatus status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
