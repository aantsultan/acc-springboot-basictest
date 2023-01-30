package com.springboot.basictest.userservice.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "user_details")
public class UserEntity {

    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    private String ssn;
    @Size(min = 3, max = 100)
    @Pattern(regexp = "[^A-Za-z]")
    @NotNull
    @JsonProperty("first_name")
    private String firstName;
    @Size(min = 3, max = 100)
    @Pattern(regexp = "[^A-Za-z]")
    @Null
    @JsonProperty("middle_name")
    private String middleName;
    @Size(min = 3, max = 100)
    @Pattern(regexp = "[^A-Za-z]")
    @Null
    @JsonProperty("family_name")
    private String familyName;
    @Past
    @JsonProperty("birth_date")
    private LocalDate birthDate;
    @JsonProperty("created_time")
    private LocalDateTime createdTime;
    @JsonProperty("updated_time")
    private LocalDateTime updatedTime;
    @NotNull
    @Size(max = 100)
    @JsonProperty("created_by")
    private String createdBy;
    @NotNull
    @Size(max = 100)
    @JsonProperty("updated_by")
    private String updatedBy;
    @NotNull
    @JsonProperty("is_active")
    private Boolean isActive;
    @Null
    @JsonProperty("deleted_time")
    private LocalDateTime deletedTime;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserSettingEntity> userSettings;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(LocalDateTime deletedTime) {
        this.deletedTime = deletedTime;
    }

    public List<UserSettingEntity> getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(List<UserSettingEntity> userSettings) {
        this.userSettings = userSettings;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", ssn='" + ssn + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", birthDate=" + birthDate +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", createdBy='" + createdBy + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", isActive=" + isActive +
                ", deletedTime=" + deletedTime +
                '}';
    }
}
