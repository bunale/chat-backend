package com.chat.backend.module.message.manager;

import com.chat.backend.module.message.domain.entity.ConversationParticipantDO;
import com.chat.backend.module.message.domain.vo.ConversationUserVO;
import com.mybatisflex.core.service.IService;

import java.util.List;

/**
 * 会话参与者表 服务层。
 *
 * @author 14110
 * @since 2024-12-01
 */
public interface ConversationParticipantManager extends IService<ConversationParticipantDO> {

    /**
     * 根据会话id查询参与者信息
     *
     * @param conversationId conversation id
     * @return {@link List }<{@link ConversationUserVO }>
     * @author bunale
     */
    List<ConversationUserVO> getConversationParticipant(Long conversationId);
}
