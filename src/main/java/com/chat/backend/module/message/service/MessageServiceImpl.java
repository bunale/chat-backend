package com.chat.backend.module.message.service;

import com.chat.backend.module.message.domain.entity.ConversationDO;
import com.chat.backend.module.message.domain.entity.MessageDO;
import com.chat.backend.module.message.domain.param.GetMessageParam;
import com.chat.backend.module.message.domain.param.SendMessageParam;
import com.chat.backend.module.message.domain.vo.MessageVO;
import com.chat.backend.module.message.manager.MessageManager;
import com.chat.backend.module.message.mapper.MessageMapper;
import com.chat.backend.util.PageUtils;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 消息服务接口
 *
 * @author liujie
 * @since 2024/12/4
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl extends ServiceImpl<MessageMapper, MessageDO> implements MessageService{

    private final MessageManager messageManager;
    private final ConversationService conversationService;

    /**
     * 发送消息到指定会话
     *
     * @param param param
     * @author bunale
     */
    @Override
    public void send(SendMessageParam param) {
        MessageDO messageDO = new MessageDO();
        messageDO.setConversationId(param.getConversationId());
        messageDO.setContent(param.getContent());
        messageDO.setSenderId((param).getCurrentUser().getUserId());
        messageDO.setType(param.getType());
        messageDO.setSentAt(LocalDateTime.now());
        messageDO.setReadFlag(false);
        messageDO.setDeleteFlag(false);
        messageManager.save(messageDO);

        // 更新会话的最后一条消息
        ConversationDO conversationDO = new ConversationDO();
        conversationDO.setConversationId(param.getConversationId());
        conversationDO.setLastMessageId(messageDO.getMessageId());
        conversationDO.setLastMessageTime(LocalDateTime.now());
        conversationDO.setLastMessageContent(messageDO.getContent());
        conversationDO.setOrderTime(LocalDateTime.now());
        conversationService.updateById(conversationDO);
    }

    /**
     * 分页查询消息
     *
     * @param param 查询参数
     * @return {@link Page }<{@link MessageVO }>
     */
    @Override
    public Page<MessageVO> page(GetMessageParam param) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq(MessageDO::getConversationId, param.getConversationId())
                .eq(MessageDO::getDeleteFlag, false);
        queryWrapper.orderBy(MessageDO::getSentAt).desc();
        Page<MessageDO> page = messageManager.page(Page.of(param.getPageNum(), param.getPageSize()), queryWrapper);

        List<MessageVO> vos = page.getRecords().stream().map(this::toVo).toList();
        return PageUtils.of(page, vos);
    }

    private MessageVO toVo(MessageDO messageDO) {
        MessageVO messageVO = new MessageVO();
        messageVO.setMessageId(messageDO.getMessageId());
        messageVO.setConversationId(messageDO.getConversationId());
        messageVO.setSenderId(messageDO.getSenderId());
        messageVO.setType(messageDO.getType());
        messageVO.setContent(messageDO.getContent());
        messageVO.setReadFlag(messageDO.getReadFlag());
        messageVO.setSentAt(messageDO.getSentAt());
        messageVO.setMetadata(messageDO.getMetadata());

        return messageVO;
    }
}
