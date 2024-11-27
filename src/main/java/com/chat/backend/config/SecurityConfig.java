package com.chat.backend.config;

import com.chat.backend.module.user.domain.entity.UserDO;
import com.chat.backend.module.user.mapper.UserMapper;
import com.chat.backend.util.jwt.JwtUtils;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

/**
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

    @Value("${admin.emails}")
    private List<String> adminEmails;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        // forward和error请求不需要登录
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                        .requestMatchers("/user/login", "/user/register").permitAll()
                        .requestMatchers("/test/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                // 添加自定义的JWT过滤器
                .addFilterAfter(new JwtAuthenticationFilter(jwtUtils, userDetailsService()), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * 自定义AuthenticationManager
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

            List<String> roles = new ArrayList<>();
            roles.add("ROLE_USER");
            if (adminEmails.contains(userDO.getEmail())) {
                roles.add("ROLE_ADMIN");
            }
            return User.builder()
                    .passwordEncoder(password -> passwordEncoder().encode(password))
                    .username(userDO.getName())
                    .password(userDO.getPassword())
                    .authorities(roles.toArray(new String[0]))
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
