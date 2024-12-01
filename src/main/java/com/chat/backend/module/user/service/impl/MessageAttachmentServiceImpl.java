package com.chat.backend.module.user.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.chat.backend.module.user.entity.MessageAttachmentDO;
import com.chat.backend.module.user.mapper.MessageAttachmentMapper;
import com.chat.backend.module.user.service.MessageAttachmentService;
import org.springframework.stereotype.Service;

/**
 * 消息附件表 服务层实现。
 *
 * @author 14110
 * @since 2024-12-01
 */
@Service
public class MessageAttachmentServiceImpl extends ServiceImpl<MessageAttachmentMapper, MessageAttachmentDO>  implements MessageAttachmentService{

}
