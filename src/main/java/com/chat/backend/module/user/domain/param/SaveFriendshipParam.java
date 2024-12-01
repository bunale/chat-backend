package com.chat.backend.module.user.domain.param;

import lombok.Data;

/**
 * 新增好友请求参数
 *
 * @author bunale
 * @since 2024/11/27
 */
@Data
public class SaveFriendshipParam {

    private String receiver;

    private String remark;
    
}
