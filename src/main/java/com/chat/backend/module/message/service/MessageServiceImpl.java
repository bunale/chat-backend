package com.chat.backend.module.message.service;

import cn.hutool.core.collection.CollUtil;
import com.chat.backend.common.UserContext;
import com.chat.backend.module.message.domain.entity.ConversationDO;
import com.chat.backend.module.message.domain.entity.ConversationParticipantDO;
import com.chat.backend.module.message.domain.entity.MessageDO;
import com.chat.backend.module.message.domain.param.AddConversationParam;
import com.chat.backend.module.message.domain.param.GetConversationParam;
import com.chat.backend.module.message.domain.param.GetMessageParam;
import com.chat.backend.module.message.domain.param.SendMessageParam;
import com.chat.backend.module.message.domain.vo.ConversationVO;
import com.chat.backend.module.message.domain.vo.MessageVO;
import com.chat.backend.module.message.manager.ConversationManager;
import com.chat.backend.module.message.manager.ConversationParticipantManager;
import com.chat.backend.module.message.manager.MessageAttachmentManager;
import com.chat.backend.module.message.manager.MessageManager;
import com.chat.backend.module.user.domain.entity.UserDO;
import com.chat.backend.module.user.service.UserService;
import com.chat.backend.util.PageUtils;
import com.github.pagehelper.PageHelper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
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
public class MessageServiceImpl implements MessageService {

    private final MessageManager messageManager;
    private final MessageAttachmentManager messageAttachmentManager;
    private final ConversationManager conversationManager;
    private final ConversationParticipantManager conversationParticipantManager;
    private final UserService userService;

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
    }

    /**
     * 分页查询指定用户的会话列表
     *
     * @param pageParam page param
     * @return {@link Page }<{@link ConversationVO }>
     * @author bunale
     */
    @Override
    public Page<ConversationVO> getConversationPage(GetConversationParam pageParam) {
        com.github.pagehelper.Page<Object> page = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<ConversationDO> list = conversationManager.getConversationPage(pageParam);
        List<ConversationVO> vos = list.stream().map(this::toConversationVo).toList();
        return PageUtils.of(page, vos);
    }

    /**
     * 根据会话id获取会话信息
     *
     * @param conversationId conversation id
     * @return {@link ConversationVO }
     * @author bunale
     */
    @Override
    public ConversationVO getById(Long conversationId) {
        ConversationDO conversationDO = conversationManager.getById(conversationId);
        return toConversationVo(conversationDO);
    }

    /**
     * 新增会话
     *
     * @param param 新增会话参数
     * @return {@link ConversationVO }
     */
    @Override
    public ConversationVO add(AddConversationParam param) {
        UserContext currentUser = param.getCurrentUser();
        List<UserDO> users = userService.getByUserIds(param.getUserIds());
        List<String> invitedUsernames = users.stream().map(UserDO::getName).toList();
        String title = String.join(", ", CollUtil.union(List.of(currentUser.getUsername()), invitedUsernames));

        ConversationDO conversationDO = new ConversationDO();
        conversationDO.setTitle(title);
        conversationDO.setConversationType(param.getConversationType());
        conversationDO.setCreatedTime(LocalDateTime.now());
        conversationDO.setCreatedUserId(currentUser.getUserId());
        conversationManager.save(conversationDO);

        List<ConversationParticipantDO> participants = users.stream().map(user -> {
            ConversationParticipantDO conversationParticipantDO = new ConversationParticipantDO();
            conversationParticipantDO.setConversationId(conversationDO.getConversationId());
            conversationParticipantDO.setUserId(user.getUserId());
            if (user.getUserId().equals(currentUser.getUserId())) {
                conversationParticipantDO.setConversationRole("2");
            } else {
                conversationParticipantDO.setConversationRole("3");
            }
            conversationParticipantDO.setJoinedAt(LocalDateTime.now());
            return conversationParticipantDO;
        }).toList();
        conversationParticipantManager.saveBatch(participants);

        return toConversationVo(conversationDO);
    }

    private ConversationVO toConversationVo(ConversationDO conversationDO) {
        ConversationVO conversationVO = new ConversationVO();
        conversationVO.setConversationId(conversationDO.getConversationId());
        conversationVO.setConversationType(conversationDO.getConversationType());
        conversationVO.setLastMessageId(conversationDO.getLastMessageId());
        conversationVO.setLastMessageTime(conversationDO.getLastMessageTime());
        conversationVO.setTitle(conversationDO.getTitle());
        conversationVO.setCreatedUserId(conversationDO.getCreatedUserId());
        conversationVO.setCreatedTime(conversationDO.getCreatedTime());
        return conversationVO;
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
        messageVO.setContent(messageDO.getContent());
        messageVO.setType(messageDO.getType());
        messageVO.setReadFlag(messageDO.getReadFlag());
        messageVO.setSentAt(messageDO.getSentAt());
        messageVO.setMetadata(messageDO.getMetadata());
        return messageVO;
    }
}
