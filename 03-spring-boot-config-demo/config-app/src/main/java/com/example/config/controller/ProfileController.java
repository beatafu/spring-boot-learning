package com.example.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

/**
 * Profile环境测试
 */
@RestController
public class ProfileController {

    @Value("${app.name:未知}")
    private String appName;

    @Value("${app.debug:false}")
    private boolean debug;

    @Value("${server.port:8080}")
    private Integer serverPort;

    @Autowired
    private Environment environment;

    /**
     * 查看当前环境信息
     * GET /api/profile/info
     */
    @GetMapping("/profile/info")
    public Map<String, Object> getProfileInfo() {
        Map<String, Object> result = new HashMap<>();
        result.put("appName", appName);
        result.put("debug", debug);
        result.put("serverPort", serverPort);
        result.put("activeProfiles", Arrays.asList(environment.getActiveProfiles()));
        result.put("defaultProfiles", Arrays.asList(environment.getDefaultProfiles()));
        return result;
    }

    /**
     * 查看数据库配置（不同环境不同）
     * GET /api/profile/database
     */
    @GetMapping("/profile/database")
    public Map<String, Object> getDatabaseConfig() {
        Map<String, Object> result = new HashMap<>();
        result.put("url", environment.getProperty("spring.datasource.url"));
        result.put("username", environment.getProperty("spring.datasource.username"));
        return result;
    }

    /**
     * 查看外部API配置
     * GET /api/profile/external-api
     */
    @GetMapping("/profile/external-api")
    public Map<String, Object> getExternalApiConfig() {
        Map<String, Object> result = new HashMap<>();
        result.put("url", environment.getProperty("external.api.url"));
        result.put("timeout", environment.getProperty("external.api.timeout"));
        return result;
    }
}