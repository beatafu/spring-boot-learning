package com.example.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 应用配置属性类
 *
 * Spring Boot 4.x 增强：
 * 1. 更好的IDE提示支持
 * 2. 编译期空安全检查（配合JSpecify）
 * 3. 配置绑定性能提升（AOT优化）
 */
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    /**
     * 应用名称
     */
    private String name;

    /**
     * 应用版本
     */
    private String version;

    /**
     * 应用描述
     */
    private String description;

    /**
     * 应用所有者
     */
    private String owner;

    /**
     * 联系信息
     */
    private Contact contact = new Contact();

    /**
     * 功能列表
     */
    private List<String> features = new ArrayList<>();

    /**
     * 数据库配置
     */
    private Database database = new Database();

    // ========== 内部类：联系信息 ==========

    public static class Contact {
        private String email;
        private String phone;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
    }

    // ========== 内部类：数据库配置 ==========

    public static class Database {
        private String host;
        private Integer port;
        private String name;

        public String getHost() { return host; }
        public void setHost(String host) { this.host = host; }

        public Integer getPort() { return port; }
        public void setPort(Integer port) { this.port = port; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

    // ========== Getter 和 Setter ==========

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }

    public Contact getContact() { return contact; }
    public void setContact(Contact contact) { this.contact = contact; }

    public List<String> getFeatures() { return features; }
    public void setFeatures(List<String> features) { this.features = features; }

    public Database getDatabase() { return database; }
    public void setDatabase(Database database) { this.database = database; }

    @Override
    public String toString() {
        return "AppProperties{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", description='" + description + '\'' +
                ", owner='" + owner + '\'' +
                ", contact=" + contact +
                ", features=" + features +
                ", database=" + database +
                '}';
    }
}