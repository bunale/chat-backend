package com.chat.backend.module.user.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.chat.backend.module.user.entity.MessageDO;
import com.chat.backend.module.user.mapper.MessageMapper;
import com.chat.backend.module.user.service.MessageService;
import org.springframework.stereotype.Service;

/**
 * 会话消息表 服务层实现。
 *
 * @author 14110
 * @since 2024-12-01
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, MessageDO>  implements MessageService{

}
