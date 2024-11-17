package com.chat.backend;

import com.chat.backend.base.R;
import com.chat.backend.exception.BaseException;
import com.chat.backend.exception.ErrorCode;
import com.chat.backend.user.entity.User;
import com.chat.backend.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 应用启动类
 * @author liujie
 * @since Created in 2024/11/17
 */
@Slf4j
@RestController
@RequestMapping("/api")
@MapperScan("com.chat.backend.*.mapper")
@SpringBootApplication
public class ChatBackendApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ChatBackendApplication.class, args);

        UserMapper userMapper = applicationContext.getBean(UserMapper.class);
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            log.info("user: {}", user.toString());
        }
    }

    @GetMapping("/hello/{flag}")
    public R<String> hello(@PathVariable Integer flag) {
        if (flag == 1) {
            throw new BaseException(ErrorCode.SYSTEM_ERROR, "参数不正确！");
        } else {
            int i = 1 / 0;
        }
        return R.ok("Hello, Chat Backend!");
    }
}
