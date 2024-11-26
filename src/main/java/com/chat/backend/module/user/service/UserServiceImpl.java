package com.chat.backend.module.user.service;

import com.chat.backend.exception.BaseException;
import com.chat.backend.exception.ErrorCode;
import com.chat.backend.module.user.domain.entity.UserDO;
import com.chat.backend.module.user.domain.param.UserRegisterParam;
import com.chat.backend.module.user.domain.resp.UserLoginResp;
import com.chat.backend.module.user.mapper.UserMapper;
import com.chat.backend.util.IdGenerator;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：bunale
 * @since ：Created in 2024/11/17 16:03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService{

    /**
     * 用户注册，注册成功后自动登录
     *
     * @param userRegisterParam user register param
     * @param response response
     * @return {@link UserLoginResp }
     * @author bunale
     */
    @Override
    public UserLoginResp register(UserRegisterParam userRegisterParam, HttpServletResponse response) {
        String verificationCode = userRegisterParam.getVerificationCode();
        String code = "1234";
        if (!verificationCode.equals(code)) {
            throw new BaseException(ErrorCode.REGISTER_CODE_ERROR);
        }

        List<UserDO> list = list(QueryWrapper.create().eq(UserDO::getEmail, userRegisterParam.getEmail()));
        if (!list.isEmpty()) {
            throw new BaseException(ErrorCode.USER_ALREADY_EXIST);
        }

        UserDO userDO = new UserDO();
        userDO.setUserId(IdGenerator.generateFixedLengthSnowflake(32));
        userDO.setEmail(userRegisterParam.getEmail());
        userDO.setName(userRegisterParam.getEmail());
        userDO.setStatus(1);
        save(userDO);

        // TODO: 自动登录


        return null;
    }

}
