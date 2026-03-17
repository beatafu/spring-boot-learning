package com.example.config.properties;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * 带校验的配置属性类
 * Spring Boot 4.x 基于 Jakarta EE 11
 */
@Component
@ConfigurationProperties(prefix = "app.security")
@Validated
public class ValidatedProperties {

    /**
     * 管理员邮箱（必须填写，且是有效邮箱）
     */
    @NotBlank(message = "管理员邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String adminEmail;

    /**
     * 会话超时时间（分钟）
     */
    @NotNull(message = "会话超时时间不能为空")
    @Min(value = 5, message = "会话超时时间最小5分钟")
    @Max(value = 1440, message = "会话超时时间最大1440分钟")
    private Integer sessionTimeout;

    /**
     * 最大登录尝试次数
     */
    @NotNull(message = "最大登录尝试次数不能为空")
    @Min(value = 1, message = "最大登录尝试次数最小1次")
    @Max(value = 10, message = "最大登录尝试次数最大10次")
    private Integer maxLoginAttempts;

    // ========== Getter 和 Setter ==========

    public String getAdminEmail() { return adminEmail; }
    public void setAdminEmail(String adminEmail) { this.adminEmail = adminEmail; }

    public Integer getSessionTimeout() { return sessionTimeout; }
    public void setSessionTimeout(Integer sessionTimeout) { this.sessionTimeout = sessionTimeout; }

    public Integer getMaxLoginAttempts() { return maxLoginAttempts; }
    public void setMaxLoginAttempts(Integer maxLoginAttempts) { this.maxLoginAttempts = maxLoginAttempts; }

    @Override
    public String toString() {
        return "ValidatedProperties{" +
                "adminEmail='" + adminEmail + '\'' +
                ", sessionTimeout=" + sessionTimeout +
                ", maxLoginAttempts=" + maxLoginAttempts +
                '}';
    }
}