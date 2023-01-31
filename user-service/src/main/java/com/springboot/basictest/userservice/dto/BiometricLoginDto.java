package com.springboot.basictest.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BiometricLoginDto {

    @JsonProperty("biometric_login")
    private String biometricLogin;

    public String getBiometricLogin() {
        return biometricLogin;
    }

    public void setBiometricLogin(String biometricLogin) {
        this.biometricLogin = biometricLogin;
    }

    public BiometricLoginDto(String biometricLogin) {
        this.biometricLogin = biometricLogin;
    }

    public BiometricLoginDto() {
    }
}
