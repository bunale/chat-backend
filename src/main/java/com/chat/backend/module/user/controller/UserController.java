package com.chat.backend.module.user.controller;

import com.chat.backend.base.BaseController;
import com.chat.backend.base.R;
import com.chat.backend.exception.BaseException;
import com.chat.backend.exception.ErrorCode;
import com.chat.backend.module.user.domain.entity.UserDO;
import com.chat.backend.module.user.domain.param.UserLoginParam;
import com.chat.backend.module.user.domain.param.UserRegisterParam;
import com.chat.backend.module.user.service.UserService;
import com.chat.backend.util.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：bunale
 * @date 2024/11/17
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public R<?> register(@RequestBody UserRegisterParam userRegisterParam) {
        return R.ok(userService.register(userRegisterParam, response));
    }

    @PostMapping("/login")
    public R<?> login(@RequestBody UserLoginParam loginParam) {
        log.info("loginParam: {}", loginParam);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginParam.getUsername(), loginParam.getPassword()));
        } catch (AuthenticationException e) {
            throw new BaseException(ErrorCode.USERNAME_OR_PASSWORD_ERROR);
        }

        UserDO userDO = userService.getByUsername(loginParam.getUsername());
        return R.ok(jwtUtils.generateToken(loginParam.getUsername(), userDO.getName()));
    }
}
