package com.chat.backend.module.user.controller;

import com.chat.backend.base.BaseController;
import com.chat.backend.base.R;
import com.chat.backend.module.user.domain.param.UserLoginParam;
import com.chat.backend.module.user.domain.param.UserRegisterParam;
import com.chat.backend.module.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping("/register")
    public R<?> register(@RequestBody UserRegisterParam userRegisterParam) {
        return R.ok(userService.register(userRegisterParam, response));
    }

    @PostMapping("/login")
    public R<?> login(@RequestBody UserLoginParam loginParam) {
        log.info("loginParam: {}", loginParam);
        return R.ok(userService.loginByUsernameAndPwd(loginParam));
    }

}
