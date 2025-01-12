package com.chat.backend.module.message.mapper;

import com.chat.backend.module.message.domain.entity.ConversationParticipantDO;
import com.chat.backend.module.message.domain.vo.ConversationUserVO;
import com.mybatisflex.core.BaseMapper;

import java.util.List;

/**
 * 会话参与者表 映射层。
 *
 * @author 14110
 * @since 2024-12-01
 */
public interface ConversationParticipantMapper extends BaseMapper<ConversationParticipantDO> {

    /**
     * 根据会话id获取参与者列表
     *
     * @param conversationId conversation id
     * @return {@link List }<{@link ConversationUserVO }>
     * @author bunale
     */
    List<ConversationUserVO> getConversationParticipant(Long conversationId);
}
