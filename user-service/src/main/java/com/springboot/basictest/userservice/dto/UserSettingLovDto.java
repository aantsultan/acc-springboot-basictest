package com.springboot.basictest.userservice.dto;

public class UserSettingLovDto {

    private String biometricLogin;
    private String pushNotification;
    private String smsNotification;
    private String showOnboarding;
    private String widgetOrder;

    public UserSettingLovDto getInstance(){
        UserSettingLovDto userSettingLovDto = new UserSettingLovDto();
        userSettingLovDto.setBiometricLogin("false");
        userSettingLovDto.setPushNotification("false");
        userSettingLovDto.setSmsNotification("false");
        userSettingLovDto.setShowOnboarding("false");
        userSettingLovDto.setWidgetOrder("1,2,3,4,5");
        return userSettingLovDto;
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
