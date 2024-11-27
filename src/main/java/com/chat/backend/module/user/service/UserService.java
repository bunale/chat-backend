package com.chat.backend.module.user.service;

import com.chat.backend.module.user.domain.entity.UserDO;
import com.mybatisflex.core.service.IService;

/**
 * @author ：bunale
 * @since ：Created in 2024/11/17 16:03
 */
public interface UserService extends IService<UserDO> {

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return {@link UserDO }
     * @author liujie
     */
    UserDO getByUsername(String username);



    /**
     * 根据用户id获取用户信息
     *
     * @param userId user id
     * @return {@link UserDO }
     * @author bunale
     */
    UserDO getByUserId(String userId);
}
