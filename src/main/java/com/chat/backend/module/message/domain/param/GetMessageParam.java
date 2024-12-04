package com.chat.backend.module.message.domain.param;

import com.chat.backend.common.PageParam;
import com.chat.backend.common.UserContext;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 分页查询消息参数实体类
 *
 * @author liujie
 * @since 2024/12/4
 */
@Data
public class GetMessageParam extends PageParam {

    @NotNull
    @Schema(description = "会话id")
    private Long conversationId;

    @Hidden
    private UserContext currentUser;
}
