package com.example.config.controller;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

/**
 * 使用Environment接口读取配置
 */
@RestController
public class EnvironmentController {

    @Autowired
    private Environment environment;

    /**
     * 通过Environment读取配置
     * GET /api/config/env?key=app.name
     */
    @GetMapping("/config/env")
    public Map<String, Object> getConfigByKey(@RequestParam String key) {
        Map<String, Object> result = new HashMap<>();
        result.put("key", key);
        result.put("value", environment.getProperty(key));
        result.put("exists", environment.containsProperty(key));
        return result;
    }

    /**
     * 读取配置（带默认值）
     * GET /api/config/env-with-default?key=app.unknown&default=默认值
     */
    @GetMapping("/config/env-with-default")
    public Map<String, Object> getConfigWithDefault(
            @RequestParam String key,
            @RequestParam(defaultValue = "默认值") String defaultVal) {
        Map<String, Object> result = new HashMap<>();
        result.put("key", key);
        result.put("value", environment.getProperty(key, defaultVal));
        return result;
    }

    /**
     * 获取所有属性源
     * GET /api/config/property-sources
     */
    @GetMapping("/config/property-sources")
    public Map<String, Object> getPropertySources() {
        Map<String, Object> result = new HashMap<>();
        String[] sources = new String[0];
        if (environment instanceof ConfigurableEnvironment configurableEnvironment) {
            sources = StreamSupport
                    .stream(configurableEnvironment.getPropertySources().spliterator(), false)
                    .map(source -> source.getName())
                    .toArray(String[]::new);
        }
        result.put("propertySources", sources);
        result.put("count", sources.length);
        return result;
    }

    /**
     * 获取活跃的环境Profile
     * GET /api/config/active-profiles
     */
    @GetMapping("/config/active-profiles")
    public Map<String, Object> getActiveProfiles() {
        Map<String, Object> result = new HashMap<>();
        result.put("activeProfiles", Arrays.asList(environment.getActiveProfiles()));
        result.put("defaultProfiles", Arrays.asList(environment.getDefaultProfiles()));
        return result;
    }
}