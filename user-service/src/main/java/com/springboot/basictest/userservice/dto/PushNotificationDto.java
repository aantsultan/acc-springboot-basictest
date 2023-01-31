package com.springboot.basictest.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PushNotificationDto {

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
