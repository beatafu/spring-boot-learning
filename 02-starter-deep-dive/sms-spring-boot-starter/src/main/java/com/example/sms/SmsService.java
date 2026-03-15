package com.example.sms;

import java.util.List;

/**
 * 短信服务接口
 */
public interface SmsService {

    /**
     * 发送短信
     * @param phone 手机号
     * @param message 消息内容
     * @return 发送结果
     */
    SendResult send(String phone, String message);

    /**
     * 批量发送短信
     * @param phones 手机号列表
     * @param message 消息内容
     * @return 发送结果列表
     */
    java.util.List<SendResult> batchSend(List<String> phones, String message);
}