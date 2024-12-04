package com.chat.backend.module.user.domain.param;

import com.chat.backend.common.PageParam;
import com.chat.backend.common.UserContext;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 处理好友关系申请接口参数
 *
 * @author liujie
 * @since 2024/12/4
 */
@Data
@Tag(name = "获取好友申请列表参数")
@EqualsAndHashCode(callSuper = true)
public class GetMyFriendshipRequestParam extends PageParam {

    @Schema(description = "好友状态：对应枚举-FriendshipStatus")
    private Integer status;

    @Hidden
    private UserContext currentUser;
}
