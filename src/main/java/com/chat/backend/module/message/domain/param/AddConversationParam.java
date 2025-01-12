package com.chat.backend.module.message.domain.param;

import com.chat.backend.common.UserContext;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 * 会话表 实体类。
 *
 * @author 14110
 * @since 2024-12-01
 */
@Data
public class AddConversationParam {

    @NotEmpty
    @Schema(description = "邀请参与会话的用户ID")
    private List<String> userIds;

    /**
     * 当前用户
     */
    private UserContext currentUser;

}
