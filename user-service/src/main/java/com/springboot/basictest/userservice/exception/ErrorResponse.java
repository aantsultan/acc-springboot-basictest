package com.springboot.basictest.userservice.exception;

import java.util.List;

public class ErrorResponse {

    private String status;
    private Integer code;
    private List<String> message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public ErrorResponse(String status, Integer code, List<String> message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public ErrorResponse() {
    }
}
