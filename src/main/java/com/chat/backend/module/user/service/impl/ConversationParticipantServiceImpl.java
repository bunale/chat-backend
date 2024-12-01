package com.chat.backend.module.user.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.chat.backend.module.user.entity.ConversationParticipantDO;
import com.chat.backend.module.user.mapper.ConversationParticipantMapper;
import com.chat.backend.module.user.service.ConversationParticipantService;
import org.springframework.stereotype.Service;

/**
 * 会话参与者表 服务层实现。
 *
 * @author 14110
 * @since 2024-12-01
 */
@Service
public class ConversationParticipantServiceImpl extends ServiceImpl<ConversationParticipantMapper, ConversationParticipantDO>  implements ConversationParticipantService{

}
