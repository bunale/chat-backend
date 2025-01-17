package com.chat.backend;

import lombok.extern.slf4j.Slf4j;
import org.dromara.x.file.storage.spring.EnableFileStorage;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用启动类
 *
 * @author bunale
 * @since Created in 2024/11/17
 */
@Slf4j
@EnableFileStorage
@MapperScan("com.chat.backend.**.mapper")
@SpringBootApplication
public class ChatBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatBackendApplication.class, args);
    }

}
