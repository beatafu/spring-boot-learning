package com.example.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置文件优先级测试
 */
@RestController
public class PriorityController {

    @Value("${test.priority:未设置}")
    private String testPriority;

    @Autowired
    private Environment environment;

    /**
     * 查看当前生效的配置
     * GET /api/priority/test
     */
    @GetMapping("/priority/test")
    public Map<String, Object> testPriority() {
        Map<String, Object> result = new HashMap<>();
        result.put("testPriority", testPriority);
        result.put("serverPort", environment.getProperty("server.port"));
        result.put("activeProfiles", environment.getActiveProfiles());
        return result;
    }
}