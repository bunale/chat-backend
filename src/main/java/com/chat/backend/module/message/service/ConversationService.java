package com.chat.backend.module.message.service;

import com.chat.backend.module.message.domain.entity.ConversationDO;
import com.chat.backend.module.message.domain.param.AddConversationParam;
import com.chat.backend.module.message.domain.param.GetConversationParam;
import com.chat.backend.module.message.domain.vo.ConversationVO;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;
import jakarta.validation.Valid;

/**
 * 会话服务接口
 *
 * @author bunale
 * @since 2025/01/12
 */
public interface ConversationService extends IService<ConversationDO> {

    /**
     * 开始会话
     *
     * @param param 新增会话参数
     * @return {@link ConversationVO }
     */
    ConversationVO start(@Valid AddConversationParam param);

    /**
     * 根据会话id获取会话信息
     *
     * @param conversationId conversation id
     * @return {@link ConversationVO }
     * @author bunale
     */
    ConversationVO getById(Long conversationId);

    /**
     * 分页查询指定用户的会话列表
     *
     * @param pageParam page param
     * @return {@link Page }<{@link ConversationVO }>
     * @author bunale
     */
    Page<ConversationVO> getConversationPage(GetConversationParam pageParam);
}
