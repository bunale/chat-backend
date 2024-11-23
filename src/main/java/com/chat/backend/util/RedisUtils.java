package com.chat.backend.util;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * redis utils
 *
 * @author bunale
 * @since 2024/11/23
 */
@Component
public class RedisUtils {

    private final RedissonClient redissonClient;

    /**
     * 构造函数，用于注入RedissonClient。
     *
     * @param redissonClient Redisson客户端实例
     */
    @Autowired
    public RedisUtils(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * 设置键值对，有过期时间。
     *
     * @param key 键
     * @param value 值
     * @param duration 过期时间
     */
    public void set(String key, Object value, Duration duration) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        bucket.set(value, duration);
    }

    /**
     * 获取键对应的值。
     *
     * @param key 键
     * @return 对应的值
     */
    public Object get(String key) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        return bucket.get();
    }

    /**
     * 获取键对应的值，并转换为字符串。
     *
     * @param key key
     * @return {@link String }
     * @author bunale
     */
    public String getAsString(String key) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        return bucket.get() == null? null : bucket.get().toString();
    }

    /**
     * 删除键。
     *
     * @param key 键
     * @return 如果键被成功删除，返回true；否则返回false
     */
    public boolean delete(String key) {
        return redissonClient.getKeys().delete(key) == 1;
    }

    /**
     * 检查键是否存在。
     *
     * @param key 键
     * @return 如果键存在，返回true；否则返回false
     */
    public boolean exists(String key) {
        return redissonClient.getKeys().countExists(key) == 1;
    }

    /**
     * 设置键值对，没有过期时间。
     *
     * @param key 键
     * @param value 值
     */
    public void set(String key, Object value) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        bucket.set(value);
    }

    /**
     * 增加计数器的值。
     *
     * @param key 键
     * @param delta 增加的值
     * @return 增加后的计数器值
     */
    public long increment(String key, long delta) {
        return redissonClient.getAtomicLong(key).addAndGet(delta);
    }

    /**
     * 减少计数器的值。
     *
     * @param key 键
     * @param delta 减少的值
     * @return 减少后的计数器值
     */
    public long decrement(String key, long delta) {
        return redissonClient.getAtomicLong(key).addAndGet(-delta);
    }
}