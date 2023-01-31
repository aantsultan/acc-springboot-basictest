package com.springboot.basictest.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UserSettingRequestDto {

    @NotNull
    @Pattern(regexp = "^false$|^true$", message = "Invalid value for field biometric_login, rejected value: ${validatedValue}")
    @JsonProperty("biometric_login")
    private String biometricLogin;
    @NotNull
    @Pattern(regexp = "^false$|^true$", message = "Invalid value for field push_notification, rejected value: ${validatedValue}")
    @JsonProperty("push_notification")
    private String pushNotification;
    @NotNull
    @Pattern(regexp = "^false$|^true$", message = "Invalid value for field sms_notification, rejected value: ${validatedValue}")
    @JsonProperty("sms_notification")
    private String smsNotification;
    @NotNull
    @Pattern(regexp = "^false$|^true$", message = "Invalid value for field show_onboarding, rejected value: ${validatedValue}")
    @JsonProperty("show_onboarding")
    private String showOnboarding;
    @NotNull
    @Pattern(regexp = "^[0-5],[0-5],[0-5],[0-5],[0-5]$", message = "Invalid value for field widget_order, rejected value: ${validatedValue}")
    @JsonProperty("widget_order")
    private String widgetOrder;

    public UserSettingRequestDto(String biometricLogin, String pushNotification, String smsNotification, String showOnboarding, String widgetOrder) {
        this.biometricLogin = biometricLogin;
        this.pushNotification = pushNotification;
        this.smsNotification = smsNotification;
        this.showOnboarding = showOnboarding;
        this.widgetOrder = widgetOrder;
    }

    public UserSettingRequestDto() {
    }

    public String getBiometricLogin() {
        return biometricLogin;
    }

    public void setBiometricLogin(String biometricLogin) {
        this.biometricLogin = biometricLogin;
    }

    public String getPushNotification() {
        return pushNotification;
    }

    public void setPushNotification(String pushNotification) {
        this.pushNotification = pushNotification;
    }

    public String getSmsNotification() {
        return smsNotification;
    }

    public void setSmsNotification(String smsNotification) {
        this.smsNotification = smsNotification;
    }

    public String getShowOnboarding() {
        return showOnboarding;
    }

    public void setShowOnboarding(String showOnboarding) {
        this.showOnboarding = showOnboarding;
    }

    public String getWidgetOrder() {
        return widgetOrder;
    }

    public void setWidgetOrder(String widgetOrder) {
        this.widgetOrder = widgetOrder;
    }
}
