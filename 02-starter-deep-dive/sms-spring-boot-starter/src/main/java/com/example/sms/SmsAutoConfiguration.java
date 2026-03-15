package com.example.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 短信服务自动配置类
 * 
 * 核心注解说明：
 * 1. @Configuration：标识为配置类
 * 2. @EnableConfigurationProperties：启用配置属性绑定
 * 3. @ConditionalOnProperty：根据配置属性决定是否加载
 * 4. @ConditionalOnMissingBean：避免覆盖用户自定义的Bean
 */
@Configuration
@ConditionalOnClass(SmsService.class)  // 类路径存在SmsService类时才加载
@EnableConfigurationProperties(SmsProperties.class)  // 启用SmsProperties配置
@ConditionalOnProperty(prefix = "sms", name = "enabled", havingValue = "true", matchIfMissing = true)
public class SmsAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SmsAutoConfiguration.class);

    /**
     * 创建SmsService Bean
     * 
     * @ConditionalOnMissingBean：如果用户自己定义了SmsService Bean，则不使用这个
     * 这是Starter的最佳实践，给用户留出自定义的空间
     */
    @Bean
    @ConditionalOnMissingBean(SmsService.class)
    public SmsService smsService(SmsProperties properties) {
        log.info("===========================================");
        log.info("【自动配置】正在创建 SmsService Bean");
        log.info("配置信息：{}", properties);
        log.info("===========================================");
        return new DefaultSmsService(properties);
    }

    /**
     * 可选：创建短信模板管理器Bean
     */
    @Bean
    @ConditionalOnMissingBean(name = "smsTemplateManager")
    public SmsTemplateManager smsTemplateManager() {
        log.info("【自动配置】正在创建 SmsTemplateManager Bean");
        return new SmsTemplateManager();
    }
}