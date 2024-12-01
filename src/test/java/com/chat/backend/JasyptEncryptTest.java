package com.chat.backend;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ：bunale
 * @since ：Created in 2024/11/17 1:10
 */
@SpringBootTest
public class JasyptEncryptTest extends BaseTest {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void testEncrypt() {
        String rawText = "123456";
        String encryptedText = stringEncryptor.encrypt(rawText);
        System.out.println("rawText: " + rawText);
        System.out.println("encryptText: " + encryptedText);
        System.out.println("decryptText: " + stringEncryptor.decrypt(encryptedText));
    }
}
