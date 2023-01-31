package com.springboot.basictest.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class BiometricLoginDto {

    @NotNull
    @Pattern(regexp = "^false$|^true$", message = "Invalid value for field biometric_login, rejected value: ${validatedValue}")
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
