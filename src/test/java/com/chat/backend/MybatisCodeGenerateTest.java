package com.chat.backend;

import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.dialect.JdbcTypeMapping;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

/**
 * mybatis代码生成
 *
 * @author liujie
 * @since 2024/11/27
 */
@SpringBootTest
public class MybatisCodeGenerateTest extends BaseTest {

    @Resource
    private DataSource dataSource;

    public static GlobalConfig createGlobalConfigUseStyle1() {
        JdbcTypeMapping.registerMapping("java.sql.LocalDateTime", "java.time.LocalDateTime");
        JdbcTypeMapping.registerMapping("java.sql.LocalDate", "java.time.LocalDate");
        JdbcTypeMapping.registerMapping("java.sql.Timestamp", "java.time.LocalDateTime");

        //创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();

        //设置根包
        globalConfig.setBasePackage("com.chat.backend.module.user");

        //设置表前缀和只生成哪些表
        globalConfig.setTablePrefix("chat_");
        globalConfig.setGenerateTable("chat_conversation", "chat_conversation_participant", "chat_message", "chat_message_attachment");

        //设置生成 entity 并启用 Lombok
        globalConfig.setEntityWithLombok(true);
        globalConfig.setEntityGenerateEnable(true);

        //设置生成 controller、service、mapper,mapper xml
//        globalConfig.setControllerGenerateEnable(true);
        globalConfig.setServiceGenerateEnable(true);
        globalConfig.setServiceImplGenerateEnable(true);
        globalConfig.setMapperGenerateEnable(true);
        globalConfig.setMapperXmlGenerateEnable(true);
        globalConfig.setEnable

        //设置生成的 entity 类名后缀
        globalConfig.setEntityClassSuffix("DO");

        //设置项目的JDK版本，项目的JDK为14及以上时建议设置该项，小于14则可以不设置
        globalConfig.setEntityJdkVersion(17);

        return globalConfig;
    }

    @Test
    public void generateCode() {
        //创建配置内容，两种风格都可以。
        GlobalConfig globalConfig = createGlobalConfigUseStyle1();

        //通过 datasource 和 globalConfig 创建代码生成器
        Generator generator = new Generator(dataSource, globalConfig);

        //生成代码
        generator.generate();
    }

}
