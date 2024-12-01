package com.chat.backend;

import org.springframework.test.context.TestPropertySource;

/**
 * 通用测试用例基类，用于添加一些通用的配置
 * @author bunale
 * @since 2024/12/1
 */
@TestPropertySource(properties = {
        // 关闭 Swagger API 文档
        "springdoc.api-docs.enabled=false"
})
public class BaseTest {
}
