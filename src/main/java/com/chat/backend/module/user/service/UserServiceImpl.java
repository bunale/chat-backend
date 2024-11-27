package com.chat.backend.module.user.service;

import com.chat.backend.exception.BaseException;
import com.chat.backend.exception.ErrorCode;
import com.chat.backend.module.user.domain.entity.UserDO;
import com.chat.backend.module.user.domain.param.UserLoginParam;
import com.chat.backend.module.user.domain.param.UserRegisterParam;
import com.chat.backend.module.user.domain.resp.UserLoginResp;
import com.chat.backend.module.user.mapper.UserMapper;
import com.chat.backend.util.IdGenerator;
import com.chat.backend.util.jwt.JwtUtils;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：bunale
 * @since ：Created in 2024/11/17 16:03
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    /**
     * 使用用户名和密码登录
     *
     * @param loginParam 登录参数
     * @return {@link String }
     * @author bunale
     */
    @Override
    public UserLoginResp loginByUsernameAndPwd(UserLoginParam loginParam) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginParam.getUsername(), loginParam.getPassword()));
        } catch (AuthenticationException e) {
            throw new BaseException(ErrorCode.USERNAME_OR_PASSWORD_ERROR);
        }

        UserDO userDO = getByUsername(loginParam.getUsername());
        String token = jwtUtils.generateToken(loginParam.getUsername(), userDO.getName());

        UserLoginResp resp = new UserLoginResp();
        resp.setToken(token);
        resp.setUsername(userDO.getName());
        resp.setUserId(userDO.getUserId());
        resp.setEmail(userDO.getEmail());
        resp.setAvatar(userDO.getAvatar());
        resp.setCreateTime(userDO.getCreateTime());
        resp.setRoles(List.of());
        return resp;
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return {@link UserDO }
     * @author liujie
     */
    @Override
    public UserDO getByUsername(String username) {
        return userMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq(UserDO::getName, username)
        );
    }

    /**
     * 用户注册，注册成功后自动登录
     *
     * @param userRegisterParam user register param
     * @param response          response
     * @return {@link UserLoginResp }
     * @author bunale
     */
    @Transactional(rollbackFor = Exception.class)
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
        userDO.setPassword(userRegisterParam.getPassword());
        userDO.setStatus(1);
        save(userDO);

        UserLoginParam loginParam = new UserLoginParam();
        loginParam.setUsername(userDO.getName());
        loginParam.setPassword(userRegisterParam.getPassword());
        return loginByUsernameAndPwd(loginParam);
    }

}
