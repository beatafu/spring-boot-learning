package com.example.config.controller;

import com.example.config.properties.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用@ConfigurationProperties读取配置
 */
@RestController
public class AppPropertiesController {

    @Autowired
    private AppProperties appProperties;

    /**
     * 获取所有应用配置
     * GET /api/config/properties
     */
    @GetMapping("/config/properties")
    public Map<String, Object> getAppProperties() {
        Map<String, Object> result = new HashMap<>();
        result.put("name", appProperties.getName());
        result.put("version", appProperties.getVersion());
        result.put("description", appProperties.getDescription());
        result.put("owner", appProperties.getOwner());
        result.put("contact", appProperties.getContact());
        result.put("features", appProperties.getFeatures());
        result.put("database", appProperties.getDatabase());
        return result;
    }

    /**
     * 获取联系信息
     * GET /api/config/contact
     */
    @GetMapping("/config/contact")
    public Map<String, String> getContact() {
        Map<String, String> result = new HashMap<>();
        result.put("email", appProperties.getContact().getEmail());
        result.put("phone", appProperties.getContact().getPhone());
        return result;
    }

    /**
     * 获取数据库配置
     * GET /api/config/database
     */
    @GetMapping("/config/database")
    public Map<String, Object> getDatabase() {
        Map<String, Object> result = new HashMap<>();
        result.put("host", appProperties.getDatabase().getHost());
        result.put("port", appProperties.getDatabase().getPort());
        result.put("name", appProperties.getDatabase().getName());
        return result;
    }
}