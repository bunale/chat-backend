package com.chat.backend.module.message.mapper;

import com.chat.backend.module.message.domain.entity.ConversationDO;
import com.chat.backend.module.message.domain.param.GetConversationParam;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;

import java.util.List;

/**
 * 会话表 映射层。
 *
 * @author 14110
 * @since 2024-12-01
 */
public interface ConversationMapper extends BaseMapper<ConversationDO> {

    /**
     * 分页查询指定用户的会话列表
     *
     * @param pageParam page param
     * @return {@link Page }<{@link ConversationDO }>
     * @author bunale
     */
    List<ConversationDO> getConversationPage(GetConversationParam pageParam);
}
