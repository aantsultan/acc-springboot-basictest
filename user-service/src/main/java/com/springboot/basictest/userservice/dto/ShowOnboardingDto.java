package com.springboot.basictest.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShowOnboardingDto {

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
