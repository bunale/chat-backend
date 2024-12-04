package com.chat.backend.module.message.manager.impl;

import com.chat.backend.module.message.domain.entity.ConversationParticipantDO;
import com.chat.backend.module.message.manager.ConversationParticipantManager;
import com.chat.backend.module.message.mapper.ConversationParticipantMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 会话参与者表 服务层实现。
 *
 * @author 14110
 * @since 2024-12-01
 */
@Service
public class ConversationParticipantManagerImpl extends ServiceImpl<ConversationParticipantMapper, ConversationParticipantDO> implements ConversationParticipantManager {

}
