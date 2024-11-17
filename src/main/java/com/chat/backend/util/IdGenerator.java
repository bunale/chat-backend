package com.chat.backend.util;

import com.mybatisflex.core.keygen.impl.SnowFlakeIDKeyGenerator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author bunale
 * @since 2024/11/17
 */
public class IdGenerator {
    private static final SnowFlakeIDKeyGenerator ID_WORKER = new SnowFlakeIDKeyGenerator(1, 1);

    /**
     * 生成雪花算法ID
     * @return long
     */
    public static long generateSnowflake() {
        return ID_WORKER.nextId();
    }

    /**
     * 生成固定长度的雪花算法ID
     *
     * @param length length
     * @return {@link String }
     * @author bunale
     */
    public static String generateFixedLengthSnowflake(int length) {
        long snowflakeId = ID_WORKER.nextId();
        String snowflakeStr = Long.toUnsignedString(snowflakeId, 36); // 转换为36进制字符串

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(snowflakeStr.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            String hashedSnowflake = hexString.toString();
            // 截取到指定长度
            if (hashedSnowflake.length() > length) {
                return hashedSnowflake.substring(0, length);
            } else {
                return hashedSnowflake;
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating hash", e);
        }
    }

    public static void main(String[] args) {
        System.out.println(generateSnowflake());
        System.out.println(generateFixedLengthSnowflake(20));
    }
}
