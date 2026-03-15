package com.example.sms;

/**
 * 短信发送结果
 */
public class SendResult {

    private boolean success;
    private String messageId;
    private String phone;
    private String errorMessage;

    public SendResult() {}

    public SendResult(boolean success, String phone) {
        this.success = success;
        this.phone = phone;
    }

    public SendResult(boolean success, String messageId, String phone, String errorMessage) {
        this.success = success;
        this.messageId = messageId;
        this.phone = phone;
        this.errorMessage = errorMessage;
    }

    // ========== Getter 和 Setter ==========

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "SendResult{" +
                "success=" + success +
                ", messageId='" + messageId + '\'' +
                ", phone='" + phone + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}