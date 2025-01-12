package com.chat.backend.module.message.manager;

import com.chat.backend.module.message.domain.entity.ConversationDO;
import com.chat.backend.module.message.domain.param.GetConversationParam;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;

import java.util.List;

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
    List<ConversationDO> getConversationPage(GetConversationParam pageParam);

    /**
     * 根据用户id和会话key获取会话
     *
     * @param userIdKey userIdKey
     * @return {@link ConversationDO }
     * @author bunale
     */
    ConversationDO getByUserIdKey(String userIdKey);
}
