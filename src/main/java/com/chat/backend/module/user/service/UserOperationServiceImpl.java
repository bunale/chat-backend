package com.chat.backend.module.user.service;

import com.chat.backend.exception.BaseException;
import com.chat.backend.exception.ErrorCode;
import com.chat.backend.module.file.FileManagementService;
import com.chat.backend.module.user.domain.entity.UserDO;
import com.chat.backend.module.user.domain.entity.UserRoleDO;
import com.chat.backend.module.user.domain.param.UserLoginParam;
import com.chat.backend.module.user.domain.param.UserRegisterParam;
import com.chat.backend.module.user.domain.vo.UserLoginVO;
import com.chat.backend.util.IdGenerator;
import com.chat.backend.util.ImageGeneratorUtil;
import com.chat.backend.util.jwt.JwtUtils;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户操作服务实现类
 *
 * @author bunale
 * @since 2024/11/27
 */
@Service
@RequiredArgsConstructor
public class UserOperationServiceImpl implements UserOperationService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRoleService userRoleService;
    private final FileManagementService fileManagementService;

    /**
     * 使用用户名和密码登录
     *
     * @param loginParam 登录参数
     * @return {@link String }
     * @author bunale
     */
    @Override
    public UserLoginVO loginByUsernameAndPwd(UserLoginParam loginParam) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginParam.getUsername(), loginParam.getPassword()));
        } catch (AuthenticationException e) {
            throw new BaseException(ErrorCode.USERNAME_OR_PASSWORD_ERROR);
        }

        UserDO userDO = userService.getByUsername(loginParam.getUsername());
        String token = jwtUtils.generateToken(userDO.getUserId(), userDO.getName());

        UserLoginVO resp = new UserLoginVO();
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
     * 用户注册，注册成功后自动登录
     *
     * @param userRegisterParam user register param
     * @param response          response
     * @return {@link UserLoginVO }
     * @author bunale
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserLoginVO register(UserRegisterParam userRegisterParam, HttpServletResponse response) {
        String verificationCode = userRegisterParam.getVerificationCode();
        String code = "1234";
        if (!verificationCode.equals(code)) {
            throw new BaseException(ErrorCode.REGISTER_CODE_ERROR);
        }

        List<UserDO> list = userService.list(QueryWrapper.create().eq(UserDO::getEmail, userRegisterParam.getEmail()));
        if (!list.isEmpty()) {
            throw new BaseException(ErrorCode.USER_ALREADY_EXIST);
        }

        // 生成头像
        String userId = IdGenerator.generateFixedLengthSnowflake(32);
        byte[] bytes = ImageGeneratorUtil.generateImage(userRegisterParam.getEmail());
        String avatarUrl = fileManagementService.uploadAvatar(bytes, userId);

        UserDO userDO = new UserDO();
        userDO.setUserId(userId);
        userDO.setEmail(userRegisterParam.getEmail());
        userDO.setName(userRegisterParam.getEmail());
        userDO.setPassword(userRegisterParam.getPassword());
        userDO.setAvatar(avatarUrl);
        userDO.setCreateTime(LocalDateTime.now());
        userDO.setStatus(1);
        userService.save(userDO);

        String baseRoleKey = "ROLE_USER";
        UserRoleDO userRoleDO = new UserRoleDO();
        userRoleDO.setUserId(userDO.getUserId());
        userRoleDO.setRoleKey(baseRoleKey);
        userRoleDO.setCreatedTime(LocalDateTime.now());
        userRoleService.save(userRoleDO);

        // 注册成功，执行登录
        UserLoginParam loginParam = new UserLoginParam();
        loginParam.setUsername(userDO.getName());
        loginParam.setPassword(userRegisterParam.getPassword());
        return loginByUsernameAndPwd(loginParam);
    }
}
