package com.springboot.basictest.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserResponseDto {

    @JsonProperty("user_data")
    private UserDataDto userData;
    @JsonProperty("user_settings")
    private List<Object> userSetting;

    public UserDataDto getUserData() {
        return userData;
    }

    public void setUserData(UserDataDto userData) {
        this.userData = userData;
    }

    public List<Object> getUserSetting() {
        return userSetting;
    }

    public void setUserSetting(List<Object> userSetting) {
        this.userSetting = userSetting;
    }

    public UserResponseDto(UserDataDto userData, List<Object> userSetting) {
        this.userData = userData;
        this.userSetting = userSetting;
    }

    public UserResponseDto() {
    }
}
