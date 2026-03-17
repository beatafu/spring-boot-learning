package com.example.config.controller;

import com.example.config.properties.ValidatedProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置校验测试
 */
@RestController
public class ValidationController {

    @Autowired
    private ValidatedProperties validatedProperties;

    /**
     * 获取安全配置
     * GET /api/validation/security
     */
    @GetMapping("/validation/security")
    public Map<String, Object> getSecurityConfig() {
        Map<String, Object> result = new HashMap<>();
        result.put("adminEmail", validatedProperties.getAdminEmail());
        result.put("sessionTimeout", validatedProperties.getSessionTimeout());
        result.put("maxLoginAttempts", validatedProperties.getMaxLoginAttempts());
        return result;
    }
}