package com.chat.backend.config;

import cn.hutool.json.JSONUtil;
import com.chat.backend.common.R;
import com.chat.backend.exception.ErrorCode;
import com.chat.backend.module.user.domain.entity.UserDO;
import com.chat.backend.module.user.domain.entity.UserRoleDO;
import com.chat.backend.module.user.mapper.UserMapper;
import com.chat.backend.module.user.service.UserRoleService;
import com.chat.backend.module.user.service.UserService;
import com.chat.backend.util.jwt.JwtUtils;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Spring Security 相关的配置
 *
 * @author bunale
 * @since 2024/11/23
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    private UserMapper userMapper;
    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private UserService userService;
    @Resource
    private UserRoleService userRoleService;

    /**
     * 配置安全过滤链
     *
     * @param http HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        // forward和error类型请求不需要登录
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                        // 放开登录，注册等请求
                        .requestMatchers("/user/operation/login", "/user/operation/register").permitAll()
                        // 允许Swagger相关的路径匿名访问
                        .requestMatchers("/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**", "/webjars/**").permitAll()
                        .requestMatchers("/test/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                // 添加自定义的JWT过滤器
                .addFilterAfter(new JwtAuthenticationFilter(jwtUtils, userService, userRoleService), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling((exceptionHandling) -> {
                    // 确保在未登录的情况下访问需要认证授权的接口返回401，而不是返回默认的403，且返回统一的响应结构
                    exceptionHandling.authenticationEntryPoint(authenticationEntryPoint())
                            // 确保在未授权的情况下访问需要权限的接口返回统一的响应结构
                            .accessDeniedHandler(accessDeniedHandler());
                });

        return http.build();
    }

    /**
     * 跨域配置
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // 允许的源
        config.setAllowedOrigins(List.of("*"));
        // 允许的 HTTP 方法
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        // 允许的请求头
        config.setAllowedHeaders(List.of("*"));
        // 是否允许携带凭证
        // config.setAllowCredentials(true);
        // 预检请求的缓存时间
        config.setMaxAge(3600L);
        // 应用 CORS 配置到指定路径
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    /**
     * 配置认证入口点
     *
     * @return AuthenticationEntryPoint
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            R<Object> failure = R.failure(ErrorCode.AUTHENTICATION_EXPIRE_ERROR);
            response.setStatus(200);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONUtil.toJsonStr(failure));
            response.flushBuffer();
        };
    }

    /**
     * 配置访问拒绝处理器，返回统一的响应结构
     *
     * @return AccessDeniedHandler
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, authException) -> {
            R<Object> failure = R.failure(ErrorCode.AUTHORIZATION_MISS_ERROR);
            response.setStatus(200);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONUtil.toJsonStr(failure));
            response.flushBuffer();
        };
    }

    /**
     * 自定义 AuthenticationManager
     *
     * @return AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(authenticationProvider);
    }

    /**
     * 配置用户详情服务
     *
     * @return UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            UserDO userDO = userMapper.selectOneByQuery(
                    QueryWrapper.create()
                            .eq(UserDO::getName, username)
            );
            if (userDO == null) {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }

            // 查询用户的角色
            List<UserRoleDO> userRoles = userRoleService.list(
                    QueryWrapper.create()
                            .eq(UserRoleDO::getUserId, userDO.getUserId())
            );
            List<String> roleKeys = userRoles.stream().map(UserRoleDO::getRoleKey).toList();

            return User.builder()
                    .passwordEncoder(password -> passwordEncoder().encode(password))
                    .username(userDO.getName())
                    .password(userDO.getPassword())
                    .authorities(roleKeys.toArray(new String[0]))
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
