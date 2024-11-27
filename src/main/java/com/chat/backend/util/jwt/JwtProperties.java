package com.chat.backend.util.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * JWT配置类
 *
 * @author liujie
 * @since 2024/11/27
 */
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {

    private String secret;

    private long expiration;
}
