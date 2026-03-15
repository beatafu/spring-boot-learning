package com.example.democonsumer.consumer.controller;

import com.example.sms.SendResult;
import com.example.sms.SmsService;
import com.example.sms.SmsTemplateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private SmsTemplateManager templateManager;

    @GetMapping("/send")
    public SendResult sendSms(@RequestParam("phone") String phone,
                              @RequestParam("message") String message) {
        return smsService.send(phone, message);
    }

    @PostMapping("/batch")
    public List<SendResult> batchSend(@RequestBody BatchSendRequest request) {
        return smsService.batchSend(request.getPhones(), request.getMessage());
    }

    @GetMapping("/template/verify")
    public SendResult sendVerifyCode(@RequestParam("phone") String phone,
                                     @RequestParam("code") String code) {
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("minute", 5);
        
        String message = templateManager.render("verify_code", params);
        return smsService.send(phone, message);
    }

    @GetMapping("/templates")
    public List<String> getTemplates() {
        return templateManager.getTemplateNames().stream().toList();
    }

    public static class BatchSendRequest {
        private List<String> phones;
        private String message;

        public List<String> getPhones() { return phones; }
        public void setPhones(List<String> phones) { this.phones = phones; }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
}