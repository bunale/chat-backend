package com.chat.backend.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * Jackson序列化配置
 *
 * @author liujie
 * @since 2024/12/4
 */
@Configuration
public class JacksonConfig {

    /**
     * 配置Jackson序列化规则
     *
     * @param builder Jackson2ObjectMapperBuilder
     * @return ObjectMapper
     */
    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        // Long 转 String
        builder.serializerByType(Long.class, longToStringSerializer());

        // 统一时间类型数据格式
        JavaTimeModule module = new JavaTimeModule();
        // LocalDateTime
        String localDateTimeFormat = "yyyy-MM-dd HH:mm:ss";
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(localDateTimeFormat)));
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(localDateTimeFormat)));
        // LocalDate
        String localDateFormat = "yyyy-MM-dd";
        module.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(localDateFormat)));
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(localDateFormat)));
        // 应用JavaTimeModule模块
        builder.failOnEmptyBeans(false)
                // 设置时区为东八区
                .timeZone(TimeZone.getTimeZone("GMT+8"))
                // 禁用日期作为时间戳输出
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .modules(module);

        return builder.build();
    }

    /**
     * long 型数据统一转换为字符串返回
     */
    @Bean
    public JsonSerializer<Long> longToStringSerializer() {
        return new JsonSerializer<>() {
            @Override
            public void serialize(Long value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
                if (value == null) {
                    gen.writeNull();
                } else {
                    gen.writeString(value.toString());
                }
            }
        };
    }
}
