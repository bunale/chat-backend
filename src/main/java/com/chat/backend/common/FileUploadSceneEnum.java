package com.chat.backend.common;

import lombok.Getter;

/**
 * 文件上传场景枚举
 *
 * @author bunale
 * @since 2024/12/1
 */
@Getter
public enum FileUploadSceneEnum {
    /**
     * 根路径
     */
    ROOT(""),
    /**
     * 用户头像存储路径
     */
    USER_AVATAR("user/%s/"),
    /**
     * 聊天消息附件存储路径
     */
    CONVERSATION_MESSAGE("conversation/%s/"),
    ;

    public final String basePath;

    FileUploadSceneEnum(String basePath) {
        this.basePath = basePath;
    }

    /**
     * 根据名称获取枚举
     */
    public static FileUploadSceneEnum getByName(String name) {
        for (FileUploadSceneEnum scene : values()) {
            if (scene.name().equals(name)) {
                return scene;
            }
        }
        return ROOT;
    }

}
