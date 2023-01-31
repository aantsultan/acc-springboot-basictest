package com.springboot.basictest.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class UserRequestDto {
    @NotNull
    private String ssn;
    @Size(min = 3, max = 100)
    @Pattern(regexp = "[^A-Za-z]")
    @NotNull
    @JsonProperty("first_name")
    private String firstName;
    @Size(min = 3, max = 100)
    @Pattern(regexp = "[^A-Za-z]")
    @NotNull
    @JsonProperty("last_name")
    private String lastName;
    @Past
    @JsonProperty("birth_date")
    private LocalDate birthDate;

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public UserRequestDto(String ssn, String firstName, String lastName, LocalDate birthDate) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public UserRequestDto() {
    }
}
