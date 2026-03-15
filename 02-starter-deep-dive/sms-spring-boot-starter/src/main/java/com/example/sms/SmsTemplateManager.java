package com.example.sms;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

/**
 * 短信模板管理器
 */
@Slf4j
public class SmsTemplateManager {

    private final Map<String, String> templates = new HashMap<>();

    public SmsTemplateManager() {
        // 初始化默认模板
        templates.put("verify_code", "您的验证码是：${code}，${minute}分钟内有效");
        templates.put("notify", "【${sign}】${content}");
    }

    private void registerDefaultTemplates() {
        templates.put("verify_code", "您的验证码是：${code}，${minute}分钟内有效");
        templates.put("notify", "【${sign}】${content}");
        templates.put("marketing", "【${sign}】${title}，${content} 退订回T");
        templates.put("logistics", "【${sign}】您的快递已${status}，单号：${trackingNo}");
    }

    public String render(String templateName, Map<String, Object> params) {
        String template = templates.get(templateName);
        if (template == null) {
            throw new IllegalArgumentException("模板不存在：" + templateName);
        }

        String result = template;
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                result = result.replace("${" + entry.getKey() + "}", String.valueOf(entry.getValue()));
            }
        }

        return result;
    }

    public void registerTemplate(String name, String content) {
        templates.put(name, content);
    }

    public Set<String> getTemplateNames() {
        return templates.keySet();
    }

    public boolean hasTemplate(String templateName) {
        return templates.containsKey(templateName);
    }
}