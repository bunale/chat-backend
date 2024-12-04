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
public class SendMessageParam extends PageParam {

    @NotNull
    @Schema(description = "会话id")
    private Long conversationId;
    @NotNull
    @Schema(description = "消息类型，1：文本，2：图片，3：语音，4：通话记录，5：位置，6：链接，7：文件")
    private Integer type;

    @Schema(description = "消息内容")
    private String content;

    @Hidden
    private UserContext currentUser;
}
