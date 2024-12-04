package com.chat.backend.module.message.manager;

import com.chat.backend.module.message.domain.entity.ConversationDO;
import com.chat.backend.module.message.domain.param.GetConversationParam;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;

/**
 * 会话表 服务层。
 *
 * @author 14110
 * @since 2024-12-01
 */
public interface ConversationManager extends IService<ConversationDO> {

    /**
     * 分页查询指定用户的会话列表
     *
     * @param pageParam page param
     * @return {@link Page }<{@link ConversationDO }>
     * @author bunale
     */
    Page<ConversationDO> getConversationPage(GetConversationParam pageParam);
}
