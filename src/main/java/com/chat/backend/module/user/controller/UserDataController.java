package com.chat.backend.module.user.controller;

import com.chat.backend.common.R;
import com.chat.backend.module.user.domain.param.QueryUserDataParam;
import com.chat.backend.module.user.service.UserService;
import com.chat.backend.util.UserContextHolder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户数据 Controller
 *
 * @author liujie
 * @since 2024/12/2
 */
@Tag(name = "用户数据 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user/data")
public class UserDataController {

    private final UserService userService;

    @Operation(summary = "分页查询用户数据")
    @GetMapping("/page")
    public R<?> page(QueryUserDataParam param) {
        param.setUserId(UserContextHolder.getCurrentUserId());
        return R.ok(userService.page(param));
    }


}
