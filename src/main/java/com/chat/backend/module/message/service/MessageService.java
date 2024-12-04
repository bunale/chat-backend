package com.chat.backend.module.message.service;

import com.chat.backend.module.message.domain.param.AddConversationParam;
import com.chat.backend.module.message.domain.param.GetMessageParam;
import com.chat.backend.module.message.domain.vo.ConversationVO;
import com.chat.backend.module.message.domain.vo.MessageVO;
import com.mybatisflex.core.paginate.Page;
import jakarta.validation.Valid;

/**
 * 消息服务接口
 *
 * @author liujie
 * @since 2024/12/4
 */
public interface MessageService {
    /**
     * 分页查询消息
     *
     * @param param 查询参数
     * @return {@link Page }<{@link MessageVO }>
     */
    Page<MessageVO> page(GetMessageParam param);

    /**
     * 新增会话
     *
     * @param param 新增会话参数
     * @return {@link ConversationVO }
     */
    ConversationVO add(@Valid AddConversationParam param);
}
