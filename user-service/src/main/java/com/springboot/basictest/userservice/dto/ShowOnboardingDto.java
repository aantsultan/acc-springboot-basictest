package com.springboot.basictest.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ShowOnboardingDto {

    @NotNull
    @Pattern(regexp = "^false$|^true$", message = "Invalid value for field show_onboarding, rejected value: ${validatedValue}")
    @JsonProperty("show_onboarding")
    private String showOnboarding;

    public String getShowOnboarding() {
        return showOnboarding;
    }

    public void setShowOnboarding(String showOnboarding) {
        this.showOnboarding = showOnboarding;
    }

    public ShowOnboardingDto(String showOnboarding) {
        this.showOnboarding = showOnboarding;
    }

    public ShowOnboardingDto() {
    }
}
