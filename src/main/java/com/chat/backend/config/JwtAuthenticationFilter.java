package com.chat.backend.config;

import com.chat.backend.common.UserContext;
import com.chat.backend.module.user.domain.entity.UserDO;
import com.chat.backend.module.user.service.UserService;
import com.chat.backend.util.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JWT token认证过滤器
 * 1. 取出Authorization头中的JWT Token，验证Token合法性，不合法则抛出异常
 * 2. 从 Token 中解析出用户ID，并查询用户权限信息
 * 3. 将用户信息封装为Authentication对象，并设置到SecurityContextHolder中，以便Spring Security进行权限验证。
 *
 * @author liujie
 * @since 2024/11/27
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserService userService;

    public JwtAuthenticationFilter(JwtUtils jwtUtils, UserService userService) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String jwt = authorizationHeader.substring(7);
        if (!jwtUtils.validateToken(jwt)) {
            chain.doFilter(request, response);
            return;
        }

        String userId = jwtUtils.getUserIdFromToken(jwt);
        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // 从数据库中查询用户信息
            UserDO userDO = userService.getByUserId(userId);
            UserContext userContext = new UserContext();
            userContext.setUserId(userId);
            userContext.setUsername(userDO.getName());
            userContext.setAvatar(userDO.getAvatar());
            userContext.setEmail(userDO.getEmail());

            // 设置用户角色
            List<String> roles = new ArrayList<>();
            roles.add("ROLE_USER");
            String adminEmail = "1411068461@qq.com";
            if (adminEmail.equals(userDO.getEmail())) {
                roles.add("ROLE_ADMIN");
            }
            userContext.setRole(roles);

            // 组装用户凭证，并存放到 SecurityContextHolder 中，以便于 Spring Security 进行权限验证
            List<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).toList();
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userContext, null, authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}