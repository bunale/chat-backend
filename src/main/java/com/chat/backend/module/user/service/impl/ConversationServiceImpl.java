package com.chat.backend.module.user.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.chat.backend.module.user.entity.ConversationDO;
import com.chat.backend.module.user.mapper.ConversationMapper;
import com.chat.backend.module.user.service.ConversationService;
import org.springframework.stereotype.Service;

/**
 * 会话表 服务层实现。
 *
 * @author 14110
 * @since 2024-12-01
 */
@Service
public class ConversationServiceImpl extends ServiceImpl<ConversationMapper, ConversationDO>  implements ConversationService{

}
