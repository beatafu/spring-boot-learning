package com.example.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 加密配置测试
 */
@RestController
public class EncryptController {

    @Value("${spring.datasource.password:未配置}")
    private String dbPassword;

    /**
     * 查看数据库密码（生产环境应脱敏）
     * GET /api/encrypt/db-password
     */
    @GetMapping("/encrypt/db-password")
    public Map<String, Object> getDbPassword() {
        Map<String, Object> result = new HashMap<>();
        // 生产环境不应返回完整密码
        if (dbPassword != null && dbPassword.length() > 4) {
            result.put("password", dbPassword.substring(0, 2) + "****");
        } else {
            result.put("password", "已配置");
        }
        result.put("dbPassword", dbPassword);
        result.put("configured", dbPassword != null && !dbPassword.contains("未配置"));
        return result;
    }
}