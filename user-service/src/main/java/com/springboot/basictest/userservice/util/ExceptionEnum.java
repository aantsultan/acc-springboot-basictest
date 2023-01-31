package com.springboot.basictest.userservice.util;

public enum ExceptionEnum {

    NOT_FOUND("NOT_FOUND", 30000, "User not found"),
    ALREADY_EXIST("ALREADY_EXIST", 30001, "User already exist"),
    BAD_REQUEST("BAD_REQUEST", 30002, "Invalid input data"),
    SYSTEM_ERROR("SYSTEM_ERROR", 80000, "Internal server error");

    private String description;
    private Integer code;
    private String message;
    ExceptionEnum(String description, Integer code, String message) {
        this.description = description;
        this.code = code;
        this.message = message;
    }

    public String getDescription(){
        return description;
    }

    public Integer getCode(){
        return code;
    }
    public String getMessage(){
        return message;
    }
}
