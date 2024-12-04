package com.chat.backend.module.message.domain.param;

import com.chat.backend.common.PageParam;
import com.chat.backend.common.UserContext;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会话表 实体类。
 *
 * @author 14110
 * @since 2024-12-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GetConversationParam extends PageParam {

    @Schema(description = "会话类型，1：一对一，2：多人聊天")
    private Integer conversationType;

    /**
     * 当前用户
     */
    private UserContext currentUser;

}
