package com.chat.backend.module.user.service;

import com.chat.backend.module.user.domain.param.UserLoginParam;
import com.chat.backend.module.user.domain.param.UserRegisterParam;
import com.chat.backend.module.user.domain.vo.UserLoginVO;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 用户操作服务
 *
 * @author bunale
 * @since 2024/11/27
 */
public interface UserOperationService {

    /**
     * 使用用户名和密码登录
     *
     * @param loginParam 登录参数
     * @return {@link String }
     * @author bunale
     */
    UserLoginVO loginByUsernameAndPwd(UserLoginParam loginParam);


    /**
     * 用户注册，注册成功后自动登录
     *
     * @param userRegisterParam user register param
     * @param response          response
     * @return {@link Object }
     * @author bunale
     */
    UserLoginVO register(UserRegisterParam userRegisterParam, HttpServletResponse response);

}
