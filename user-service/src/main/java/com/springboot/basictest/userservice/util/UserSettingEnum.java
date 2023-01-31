package com.springboot.basictest.userservice.util;

public enum UserSettingEnum {

    BIOMETRIC_LOGIN("biometric_login"),
    PUSH_NOTIFICATION("push_notification"),
    SMS_NOTIFICATION("sms_notification"),
    SHOW_ONBOARDING("show_onboarding"),
    WIDGET_ORDER("widget_order");

    private String key;
    UserSettingEnum(String key){
        this.key = key;
    }

    public String getKey(){
        return key;
    }
}
