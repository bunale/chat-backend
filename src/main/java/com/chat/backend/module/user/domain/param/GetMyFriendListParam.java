package com.chat.backend.module.user.domain.param;

import com.chat.backend.common.PageParam;
import com.chat.backend.common.UserContext;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 处理好友关系申请接口参数
 *
 * @author liujie
 * @since 2024/12/4
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GetMyFriendListParam extends PageParam {

    @Schema(description = "用户名称")
    private String username;

    @Hidden
    private UserContext currentUser;
}
