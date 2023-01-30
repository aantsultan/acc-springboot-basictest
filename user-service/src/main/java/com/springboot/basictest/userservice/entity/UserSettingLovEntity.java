package com.springboot.basictest.userservice.entity;

public class UserSettingLovEntity {

    private String biometricLogin;
    private String pushNotification;
    private String smsNotification;
    private String showOnboarding;
    private String widgetOrder;

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

    @Override
    public String toString() {
        return "UserSettingLovEntity{" +
                "biometricLogin='" + biometricLogin + '\'' +
                ", pushNotification='" + pushNotification + '\'' +
                ", smsNotification='" + smsNotification + '\'' +
                ", showOnboarding='" + showOnboarding + '\'' +
                ", widgetOrder='" + widgetOrder + '\'' +
                '}';
    }
}
