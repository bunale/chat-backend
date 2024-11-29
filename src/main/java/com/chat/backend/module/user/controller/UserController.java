package com.chat.backend.module.user.controller;

import com.chat.backend.common.BaseController;
import com.chat.backend.common.R;
import com.chat.backend.module.user.domain.param.UserLoginParam;
import com.chat.backend.module.user.domain.param.UserRegisterParam;
import com.chat.backend.module.user.service.UserOperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录，注册 Controller
 *
 * @author ：bunale
 * @since 2024/11/29
 */
@Tag(name = "用户登录，注册相关API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user/operation")
public class UserController extends BaseController {

    private final UserOperationService userOperationService;

    @Operation(summary = "注册")
    @PostMapping("/register")
    public R<?> register(@RequestBody UserRegisterParam userRegisterParam) {
        return R.ok(userOperationService.register(userRegisterParam, response));
    }

    @Operation(summary = "登录")
    @PostMapping("/login")
    public R<?> login(@RequestBody UserLoginParam loginParam) {
        log.info("loginParam: {}", loginParam);
        return R.ok(userOperationService.loginByUsernameAndPwd(loginParam));
    }

}
