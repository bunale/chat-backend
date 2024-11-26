package com.chat.backend;

import com.chat.backend.module.user.domain.entity.UserDO;
import com.chat.backend.module.user.mapper.UserMapper;
import com.chat.backend.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

/**
 * 应用启动类
 * @author bunale
 * @since Created in 2024/11/17
 */
@Slf4j
@MapperScan("com.chat.backend.**.mapper")
@SpringBootApplication
public class ChatBackendApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ChatBackendApplication.class, args);

        UserMapper userMapper = applicationContext.getBean(UserMapper.class);
        List<UserDO> users = userMapper.selectAll();
        for (UserDO user : users) {
            log.info("user: {}", user.toString());
        }

        RedisUtils redisUtils = applicationContext.getBean(RedisUtils.class);
        redisUtils.set("test", "test");
        String value = redisUtils.getAsString("test");
        log.info("redis value: {}", value);
    }


}
