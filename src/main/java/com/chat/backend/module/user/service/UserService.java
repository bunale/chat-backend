package com.chat.backend.module.user.service;

import com.chat.backend.module.user.domain.param.UserRegisterParam;
import com.chat.backend.module.user.domain.entity.UserDO;
import com.chat.backend.module.user.domain.resp.UserLoginResp;
import com.mybatisflex.core.service.IService;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author ：bunale
 * @since ：Created in 2024/11/17 16:03
 */
public interface UserService extends IService<UserDO> {
    /**
     * 用户注册，注册成功后自动登录
     *
     * @param userRegisterParam user register param
     * @param response response
     * @return {@link Object }
     * @author bunale
     */
    UserLoginResp register(UserRegisterParam userRegisterParam, HttpServletResponse response);

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return {@link UserDO }
     * @author liujie
     */
    UserDO getByUsername(String username);
}
