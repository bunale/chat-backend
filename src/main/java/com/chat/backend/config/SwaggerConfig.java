package com.chat.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger配置类
 *
 * @author liujie
 * @since 2024/11/28
 */

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Chat-backend API Documentation")
                        .version("1.0.0")
                        .description("This is a sample Spring Boot application with Swagger"));
    }
}
