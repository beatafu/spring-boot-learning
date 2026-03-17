package com.example.config.controller;

import com.example.config.properties.AppProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置文件读取演示 - @Value方式
 * Spring Boot 4.x 支持更灵活的SpEL表达式
 */
@RestController
public class ConfigController {

    private final AppProperties appProperties;

    public ConfigController(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    /**
     * 读取简单配置项
     * 语法：${配置键}
     */
    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Value("${app.description:默认描述}")
    private String appDescription;

    @Value("${app.unknown:默认值}")
    private String unknownConfig;

    @Value("${server.port}")
    private Integer serverPort;

    /**
     * 测试@Value读取配置
     * GET /api/config/value
     */
    @GetMapping("/config/value")
    public Map<String, Object> getConfigByValue() {
        Map<String, Object> result = new HashMap<>();
        result.put("appName", appName);
        result.put("appVersion", appVersion);
        result.put("appDescription", appDescription);
        result.put("unknownConfig", unknownConfig);
        result.put("serverPort", serverPort);
        result.put("features", appProperties.getFeatures());
        return result;
    }

    /**
     * 读取SpEL表达式
     * Spring Boot 4.x 增强SpEL支持
     */
    @Value("#{T(java.lang.Math).random() * 100}")
    private Double randomValue;

    @Value("#{systemProperties['user.name']}")
    private String systemUser;

    @GetMapping("/config/random")
    public Map<String, Object> getRandomValue() {
        Map<String, Object> result = new HashMap<>();
        result.put("randomValue", randomValue);
        result.put("systemUser", systemUser);
        return result;
    }
}