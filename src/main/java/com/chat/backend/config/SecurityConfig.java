package com.chat.backend.config;

import com.chat.backend.module.user.domain.entity.UserDO;
import com.chat.backend.module.user.mapper.UserMapper;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.DeferredSecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextRepository;

/**
 * @author bunale
 * @since 2024/11/23
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((authorize) -> authorize
                    // forward和error请求不需要登录
                    .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                    .requestMatchers("/user/login", "/user/register").permitAll()
                    .requestMatchers("/test/**").hasAuthority("ADMIN")
                    .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())
                .securityContext(context -> {

                })
        return http.build();
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new SecurityContextRepository() {
            /**
             * Defers loading the {@link SecurityContext} using the {@link HttpServletRequest}
             * until it is needed by the application.
             *
             * @param request the {@link HttpServletRequest} to load the {@link SecurityContext}
             *                from
             * @return a {@link DeferredSecurityContext} that returns the {@link SecurityContext}
             * which cannot be null
             * @since 5.8
             */
            @Override
            public DeferredSecurityContext loadDeferredContext(HttpServletRequest request) {
                return SecurityContextRepository.super.loadDeferredContext(request);
            }

            /**
             * Stores the security context on completion of a request.
             *
             * @param context  the non-null context which was obtained from the holder.
             * @param request
             * @param response
             */
            @Override
            public void saveContext(org.springframework.security.core.context.SecurityContext context, HttpServletRequest request, HttpServletResponse response) {

            }

            /**
             * Allows the repository to be queried as to whether it contains a security context
             * for the current request.
             *
             * @param request the current request
             * @return true if a context is found for the request, false otherwise
             */
            @Override
            public boolean containsContext(HttpServletRequest request) {
                return false;
            }
        };
    }


    /**
     * 自定义AuthenticationManager
     * @return AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(UserMapper userMapper) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService(userMapper));
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public UserDetailsService userDetailsService(UserMapper userMapper) {
        return username -> {
            UserDO userDO = userMapper.selectOneByQueryAs(QueryWrapper.create().eq(UserDO::getName, username), UserDO.class);
            if (userDO == null) {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }

            return User.builder()
                    .passwordEncoder(password -> passwordEncoder().encode(password))
                    .username(userDO.getName())
                    .password(userDO.getPassword())
                    .roles(new String[]{})
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
