package com.example.config;

import com.example.config.properties.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 应用启动类
 *
 * Spring Boot 4.x 变化：
 * 1. 配置类如有@Component，可省略@EnableConfigurationProperties
 * 2. 但显式声明更清晰，推荐保留
 */
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class ConfigAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigAppApplication.class, args);
    }

}
