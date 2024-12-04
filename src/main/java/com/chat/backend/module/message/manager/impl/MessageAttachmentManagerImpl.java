package com.chat.backend.module.message.manager.impl;

import com.chat.backend.module.message.domain.entity.MessageAttachmentDO;
import com.chat.backend.module.message.manager.MessageAttachmentManager;
import com.chat.backend.module.message.mapper.MessageAttachmentMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 消息附件表 服务层实现。
 *
 * @author 14110
 * @since 2024-12-01
 */
@Service
public class MessageAttachmentManagerImpl extends ServiceImpl<MessageAttachmentMapper, MessageAttachmentDO> implements MessageAttachmentManager {

}
