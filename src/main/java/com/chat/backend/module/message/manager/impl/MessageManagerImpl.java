package com.chat.backend.module.message.manager.impl;

import com.chat.backend.module.message.domain.entity.MessageDO;
import com.chat.backend.module.message.manager.MessageManager;
import com.chat.backend.module.message.mapper.MessageMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 会话消息表 服务层实现。
 *
 * @author 14110
 * @since 2024-12-01
 */
@Service
public class MessageManagerImpl extends ServiceImpl<MessageMapper, MessageDO> implements MessageManager {

}
