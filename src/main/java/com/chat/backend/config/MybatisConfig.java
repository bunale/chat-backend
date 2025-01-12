package com.chat.backend.config;

import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author bunale
 * @since 2025/1/12
 */
@Configuration
public class MybatisConfig {

    /**
     * 配置分页插件，在使用 flex-mybatis 时，需要配置该插件，否则PageHelper无法正常工作
     */
    @Bean
    public PageInterceptor pageInterceptor(){
        return new PageInterceptor();
    }

}
