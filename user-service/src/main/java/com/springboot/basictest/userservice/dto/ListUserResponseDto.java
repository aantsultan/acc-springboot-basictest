package com.springboot.basictest.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ListUserResponseDto {

    @JsonProperty("user_data")
    private List<UserDataDto> userData;
    @JsonProperty("max_records")
    private Integer maxRecords;
    private Integer offset;

    public List<UserDataDto> getUserData() {
        return userData;
    }

    public void setUserData(List<UserDataDto> userData) {
        this.userData = userData;
    }

    public Integer getMaxRecords() {
        return maxRecords;
    }

    public void setMaxRecords(Integer maxRecords) {
        this.maxRecords = maxRecords;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public ListUserResponseDto(List<UserDataDto> userData, Integer maxRecords, Integer offset) {
        this.userData = userData;
        this.maxRecords = maxRecords;
        this.offset = offset;
    }

    public ListUserResponseDto() {
    }
}
