package com.chat.backend.module.user.domain.param;

import com.chat.backend.common.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 分页查询用户数据参数实体类
 *
 * @author liujie
 * @since 2024/12/2
 */
@Data
public class QueryUserDataParam extends PageParam {

    @Schema(description = "用户名")
    private String username;

}
