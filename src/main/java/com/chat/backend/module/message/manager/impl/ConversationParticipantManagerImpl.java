package com.chat.backend.module.message.manager.impl;

import com.chat.backend.module.message.domain.entity.ConversationParticipantDO;
import com.chat.backend.module.message.domain.vo.ConversationUserVO;
import com.chat.backend.module.message.manager.ConversationParticipantManager;
import com.chat.backend.module.message.mapper.ConversationParticipantMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会话参与者表 服务层实现。
 *
 * @author 14110
 * @since 2024-12-01
 */
@Service
@RequiredArgsConstructor
public class ConversationParticipantManagerImpl extends ServiceImpl<ConversationParticipantMapper, ConversationParticipantDO> implements ConversationParticipantManager {

    private final ConversationParticipantMapper conversationParticipantMapper;

    /**
     * 根据会话id查询参与者信息
     *
     * @param conversationId conversation id
     * @return {@link List }<{@link ConversationUserVO }>
     * @author bunale
     */
    @Override
    public List<ConversationUserVO> getConversationParticipant(Long conversationId) {
        return conversationParticipantMapper.getConversationParticipant(conversationId);
    }
}
