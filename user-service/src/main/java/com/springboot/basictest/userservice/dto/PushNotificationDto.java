package com.springboot.basictest.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class PushNotificationDto {

    @NotNull
    @Pattern(regexp = "^false$|^true$", message = "Invalid value for field push_notification, rejected value: ${validatedValue}")
    @JsonProperty("push_notification")
    private String pushNotification;

    public String getPushNotification() {
        return pushNotification;
    }

    public void setPushNotification(String pushNotification) {
        this.pushNotification = pushNotification;
    }

    public PushNotificationDto(String pushNotification) {
        this.pushNotification = pushNotification;
    }

    public PushNotificationDto() {
    }
}
