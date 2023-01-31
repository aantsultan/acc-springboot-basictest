package com.springboot.basictest.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class SmsNotificationDto {

    @NotNull
    @Pattern(regexp = "^false$|^true$", message = "Invalid value for field sms_notification, rejected value: ${validatedValue}")
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
