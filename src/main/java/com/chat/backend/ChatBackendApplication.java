package com.chat.backend;

import com.chat.backend.user.entity.User;
import com.chat.backend.user.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@MapperScan("com.chat.backend.*.mapper")
@SpringBootApplication
public class ChatBackendApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ChatBackendApplication.class, args);

        UserMapper userMapper = applicationContext.getBean(UserMapper.class);
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

}
