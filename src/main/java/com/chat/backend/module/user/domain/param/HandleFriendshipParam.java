package com.chat.backend.module.user.domain.param;

import com.chat.backend.common.UserContext;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 处理好友关系申请接口参数
 *
 * @author liujie
 * @since 2024/12/4
 */
@Data
public class HandleFriendshipParam {

    @NotNull
    @Schema(description = "好友关系ID")
    private Long friendshipId;

    @NotNull
    @Schema(description = "状态：1：接受，2：拒绝")
    private Integer status;

    @Hidden
    private UserContext currentUser;
}
