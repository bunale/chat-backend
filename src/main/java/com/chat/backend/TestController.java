package com.chat.backend;

import com.chat.backend.common.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bunale
 * @since 2024/11/17
 */
@Tag(name = "测试 API")
@RestController
@RequestMapping("/test")
public class TestController {


    @Operation(summary = "hello world")
    @GetMapping("/hello")
    public R<?> hello() {
        return R.ok("Hello World!");
    }
}
