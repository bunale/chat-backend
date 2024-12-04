package com.chat.backend.module.message.service;

import com.chat.backend.module.message.domain.param.AddConversationParam;
import com.chat.backend.module.message.domain.param.GetConversationParam;
import com.chat.backend.module.message.domain.param.GetMessageParam;
import com.chat.backend.module.message.domain.param.SendMessageParam;
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

    /**
     * 根据会话id获取会话信息
     *
     * @param conversationId conversation id
     * @return {@link ConversationVO }
     * @author bunale
     */
    ConversationVO getById(Long conversationId);

    /**
     * 分页查询指定用户的会话列表
     *
     * @param pageParam page param
     * @return {@link Page }<{@link ConversationVO }>
     * @author bunale
     */
    Page<ConversationVO> getConversationPage(GetConversationParam pageParam);

    /**
     * 发送消息到指定会话
     *
     * @param param param
     * @author bunale
     */
    void send(SendMessageParam param);
}
