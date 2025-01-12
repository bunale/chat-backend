package com.chat.backend.module.message.service;

import com.chat.backend.common.UserContext;
import com.chat.backend.module.message.domain.entity.ConversationDO;
import com.chat.backend.module.message.domain.entity.ConversationParticipantDO;
import com.chat.backend.module.message.domain.param.AddConversationParam;
import com.chat.backend.module.message.domain.param.GetConversationParam;
import com.chat.backend.module.message.domain.vo.ConversationUserVO;
import com.chat.backend.module.message.domain.vo.ConversationVO;
import com.chat.backend.module.message.enums.ConversationTypeEnum;
import com.chat.backend.module.message.manager.ConversationManager;
import com.chat.backend.module.message.manager.ConversationParticipantManager;
import com.chat.backend.module.message.mapper.ConversationMapper;
import com.chat.backend.util.PageUtils;
import com.github.pagehelper.PageHelper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 会话服务接口
 *
 * @author bunale
 * @since 2025/01/12
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConversationServiceImpl extends ServiceImpl<ConversationMapper, ConversationDO> implements ConversationService {

    private final ConversationManager conversationManager;
    private final ConversationParticipantManager conversationParticipantManager;


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
     * 开始会话
     *
     * @param param 新增会话参数
     * @return {@link ConversationVO }
     */
    @Override
    public ConversationVO start(AddConversationParam param) {
        List<String> targetUserIds = param.getUserIds();
        UserContext currentUser = param.getCurrentUser();
        LocalDateTime now = LocalDateTime.now();
        if (targetUserIds.size() == 1) {
            // 单聊
            // 检测是否已经存在会话，存在则直接返回
            ConversationDO conversationDO = conversationManager.getByUserIdKey(param.getCurrentUser().getUserId() + "_" + targetUserIds.get(0));
            if (conversationDO == null) {
                conversationDO = conversationManager.getByUserIdKey(targetUserIds.get(0) + "_" + param.getCurrentUser().getUserId());
            }
            if (conversationDO != null) {
                return toConversationVo(conversationDO);
            }

            conversationDO = new ConversationDO();
            conversationDO.setConversationType(ConversationTypeEnum.ONE_TO_ONE.getCode());
            conversationDO.setUserIdKey(param.getCurrentUser().getUserId() + "_" + targetUserIds.get(0));
            conversationDO.setLastOpenTime(now);
            conversationDO.setOrderTime(now);
            conversationDO.setCreatedTime(now);
            conversationDO.setCreatedUserId(currentUser.getUserId());
            conversationManager.save(conversationDO);

            saveConversationParticipants(conversationDO.getConversationId(), List.of(currentUser.getUserId(), targetUserIds.get(0)), currentUser);

            return toConversationVo(conversationDO);
        } else {
            // 群聊
            String title = "群聊";
            ConversationDO conversationDO = new ConversationDO();
            conversationDO.setTitle(title);
            conversationDO.setConversationType(ConversationTypeEnum.GROUP.getCode());
            conversationDO.setLastOpenTime(now);
            conversationDO.setOrderTime(now);
            conversationDO.setCreatedTime(now);
            conversationDO.setCreatedUserId(currentUser.getUserId());
            conversationManager.save(conversationDO);

            saveConversationParticipants(conversationDO.getConversationId(), targetUserIds, currentUser);
            return toConversationVo(conversationDO);
        }
    }

    private void saveConversationParticipants(long conversationId, List<String> targetUserIds, UserContext currentUser) {
        List<ConversationParticipantDO> participants = targetUserIds.stream()
                .map(userId -> {
                    ConversationParticipantDO conversationParticipantDO = new ConversationParticipantDO();
                    conversationParticipantDO.setConversationId(conversationId);
                    conversationParticipantDO.setUserId(userId);
                    if (userId.equals(currentUser.getUserId())) {
                        conversationParticipantDO.setConversationRole("2");
                    } else {
                        conversationParticipantDO.setConversationRole("3");
                    }
                    conversationParticipantDO.setJoinedAt(LocalDateTime.now());
                    return conversationParticipantDO;
                }).toList();
        conversationParticipantManager.saveBatch(participants);
    }

    private ConversationVO toConversationVo(ConversationDO conversationDO) {
        List<ConversationUserVO> conversationUsers = conversationParticipantManager.getConversationParticipant(conversationDO.getConversationId());

        ConversationVO conversationVO = new ConversationVO();
        conversationVO.setConversationId(conversationDO.getConversationId());
        conversationVO.setConversationType(conversationDO.getConversationType());
        conversationVO.setLastMessageId(conversationDO.getLastMessageId());
        conversationVO.setLastMessageTime(conversationDO.getLastMessageTime());
        conversationVO.setLastMessageContent(conversationDO.getLastMessageContent());
        conversationVO.setTitle(conversationDO.getTitle());
        conversationVO.setCreatedUserId(conversationDO.getCreatedUserId());
        conversationVO.setCreatedTime(conversationDO.getCreatedTime());
        conversationVO.setUsers(conversationUsers);
        return conversationVO;
    }

}
