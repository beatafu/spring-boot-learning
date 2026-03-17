package com.example.config;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.testng.annotations.Test;


/**
 * Jasypt加密测试工具
 */
public class JasyptTest {

    @Test
    public void testEncrypt() {
        // 创建加密器
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        
        // 设置密钥（生产环境应从环境变量读取）
        config.setPassword("my-secret-key");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.NoIvGenerator");
        config.setStringOutputType("base64");
        
        encryptor.setConfig(config);

        // 加密密码
        String plainPassword = "123456";
        String encryptedPassword = encryptor.encrypt(plainPassword);
        
        System.out.println("原始密码: " + plainPassword);
        System.out.println("加密后: ENC(" + encryptedPassword + ")");
        
        // 验证解密
        String decryptedPassword = encryptor.decrypt(encryptedPassword);
        System.out.println("解密后: " + decryptedPassword);
    }
}