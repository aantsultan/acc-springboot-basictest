package com.springboot.basictest.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SmsNotificationDto {

    @JsonProperty("sms_notification")
    private String smsNotification;

    public String getSmsNotification() {
        return smsNotification;
    }

    public void setSmsNotification(String smsNotification) {
        this.smsNotification = smsNotification;
    }

    public SmsNotificationDto(String smsNotification) {
        this.smsNotification = smsNotification;
    }

    public SmsNotificationDto() {
    }
}
