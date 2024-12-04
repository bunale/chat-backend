package com.chat.backend.module.message.manager.impl;

import com.chat.backend.module.message.domain.entity.ConversationDO;
import com.chat.backend.module.message.manager.ConversationManager;
import com.chat.backend.module.message.mapper.ConversationMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 会话表 服务层实现。
 *
 * @author 14110
 * @since 2024-12-01
 */
@Service
public class ConversationManagerImpl extends ServiceImpl<ConversationMapper, ConversationDO> implements ConversationManager {

}
