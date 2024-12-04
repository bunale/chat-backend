package com.chat.backend.module.user.domain.param;

import com.chat.backend.common.UserContext;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 添加好友关系接口参数
 *
 * @author liujie
 * @since 2024/12/4
 */
@Data
public class AddFriendshipParam {

    @NotBlank
    @Schema(description = "目标用户id")
    private String receiver;

    @Schema(description = "备注")
    private String remark;

    @Hidden
    private UserContext currentUser;
}
