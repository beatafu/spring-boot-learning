package com.example.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 默认短信服务实现
 * 实际项目中可替换为阿里云、腾讯云等真实SDK
 */
public class DefaultSmsService implements SmsService {

    private static final Logger log = LoggerFactory.getLogger(DefaultSmsService.class);

    private final SmsProperties properties;

    public DefaultSmsService(SmsProperties properties) {
        this.properties = properties;
        log.info("初始化短信服务，提供商：{}", properties.getProvider());
    }

    @Override
    public SendResult send(String phone, String message) {
        // 模拟发送逻辑
        log.info("发送短信到：{}, 内容：{}", phone, message);
        
        // 模拟成功
        String messageId = UUID.randomUUID().toString();
        SendResult result = new SendResult(true, messageId, phone, null);
        
        log.info("短信发送成功，消息ID：{}", messageId);
        return result;
    }

    @Override
    public List<SendResult> batchSend(List<String> phones, String message) {
        log.info("【批量短信】开始批量发送，总数：{}", phones.size());
        List<SendResult> results = new ArrayList<>();
        for (String phone : phones) {
            results.add(send(phone, message));
        }
        log.info("【批量短信】发送完成，成功：{}/{}",
                results.stream().filter(SendResult::isSuccess).count(),
                results.size());
        return results;
    }
}