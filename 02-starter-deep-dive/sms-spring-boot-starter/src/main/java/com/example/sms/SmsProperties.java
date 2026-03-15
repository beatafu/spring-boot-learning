package com.example.sms;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 短信服务配置属性
 * 可通过 application.properties 或 application.yml 配置
 */
@ConfigurationProperties(prefix = "sms")
public class SmsProperties {

    private String provider = "aliyun";
    private String apiKey = "";
    private String apiSecret = "";
    private String signName = "我的应用";
    private boolean enabled = true;

    // Getter 和 Setter
    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

    public String getApiKey() { return apiKey; }
    public void setApiKey(String apiKey) { this.apiKey = apiKey; }

    public String getApiSecret() { return apiSecret; }
    public void setApiSecret(String apiSecret) { this.apiSecret = apiSecret; }

    public String getSignName() { return signName; }
    public void setSignName(String signName) { this.signName = signName; }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    @Override
    public String toString() {
        return "SmsProperties{" +
                "provider='" + provider + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", apiSecret='***'" +
                ", signName='" + signName + '\'' +
                ", enabled=" + enabled +
                '}';
    }

}