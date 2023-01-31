package com.springboot.basictest.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "user_setting")
public class UserSettingEntity {

    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 3, max = 100)
    @NotNull
    private String keyName;
    @Size(min = 3, max = 100)
    @NotNull
    private String valueName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserEntity user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UserSettingEntity(){

    }
    public UserSettingEntity(String keyName, String valueName, UserEntity user) {
        this.keyName = keyName;
        this.valueName = valueName;
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserSettingEntity{" +
                "id=" + id +
                ", keyName='" + keyName + '\'' +
                ", valueName='" + valueName + '\'' +
                ", user=" + user +
                '}';
    }
}
