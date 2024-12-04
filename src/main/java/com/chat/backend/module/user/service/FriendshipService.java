package com.chat.backend.module.user.service;

import com.chat.backend.module.user.domain.entity.FriendshipDO;
import com.chat.backend.module.user.domain.param.AddFriendshipParam;
import com.chat.backend.module.user.domain.param.GetMyFriendListParam;
import com.chat.backend.module.user.domain.param.GetMyFriendshipRequestParam;
import com.chat.backend.module.user.domain.param.HandleFriendshipParam;
import com.chat.backend.module.user.domain.vo.FriendshipRequestVO;
import com.chat.backend.module.user.domain.vo.UserVO;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;

/**
 * 服务层。
 *
 * @author 14110
 * @since 2024-11-27
 */
public interface FriendshipService extends IService<FriendshipDO> {

    /**
     * 添加好友关系数据
     *
     * @param param 参数
     */
    void addFriendship(AddFriendshipParam param);

    /**
     * 处理好友关系请求
     *
     * @param param 参数
     */
    void handle(HandleFriendshipParam param);

    /**
     * 分页查询当前用户好友申请
     *
     * @param param 参数
     * @return {@link Page }<{@link FriendshipDO }>
     */
    Page<FriendshipRequestVO> myFriendshipRequest(GetMyFriendshipRequestParam param);

    /**
     * 分页查询当前用户的好友列表
     *
     * @param param 参数
     * @return {@link Page }<{@link UserVO }>
     */
    Page<UserVO> getMyFriendList(GetMyFriendListParam param);
}
