package com.chat.backend.util.jwt;

/**
 * JWT工具类
 *
 * @author liujie
 * @since 2024/11/27
 */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author bunale
 * @since 2024/11/27
 */
@Slf4j
@RequiredArgsConstructor
@Component
@EnableConfigurationProperties(JwtProperties.class)
public class JwtUtils {

    private final JwtProperties jwtProperties;

    /**
     * 生成带有用户信息的token。
     *
     * @param userId   用户ID
     * @param username 用户名
     * @return 生成的JWT token
     */
    public String generateToken(String userId, String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("username", username);
        return createToken(claims);
    }

    /**
     * 从token中获取用户ID。
     *
     * @param token JWT token
     * @return 用户ID
     */
    public String getUserIdFromToken(String token) {
        return (String) getAllClaimsFromToken(token).get("userId");
    }

    /**
     * 从token中获取用户名。
     *
     * @param token JWT token
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        return (String) getAllClaimsFromToken(token).get("username");
    }

    /**
     * 检查token是否有效。
     *
     * @param token JWT token
     * @return 如果token有效则返回true，否则返回false
     */
    public boolean validateToken(String token) {
        Date expiration;
        try {
            expiration = getClaimFromToken(token, Claims::getExpiration);
        } catch (Exception e) {
            log.error("parse token error: {}", e.getMessage());
            return false;
        }

        // token过期时间 > 当前时间
        return expiration.after(new Date());
    }

    /**
     * 创建token。
     *
     * @param claims 自定义声明
     * @return 生成的JWT token
     */
    private String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (jwtProperties.getExpiration())))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    /**
     * 从token中获取所有声明。
     *
     * @param token JWT token
     * @return 所有声明
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 从token中获取指定的claim。
     *
     * @param token          JWT token
     * @param claimsResolver 解析函数
     * @param <T>            claim类型
     * @return 指定的claim值
     */
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
}
