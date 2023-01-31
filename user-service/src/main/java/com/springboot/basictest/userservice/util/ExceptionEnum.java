package com.springboot.basictest.userservice.util;

public enum ExceptionEnum {

    NOT_FOUND("NOT_FOUND", 30000),
    ALREADY_EXIST("ALREADY_EXIST", 30001),
    BAD_REQUEST("BAD_REQUEST", 30002),
    SYSTEM_ERROR("SYSTEM_ERROR", 80000);

    private String description;
    private Integer code;
    ExceptionEnum(String description, Integer code) {
        this.description = description;
        this.code = code;
    }

    public String getDescription(){
        return description;
    }

    public Integer getCode(){
        return code;
    }
}
