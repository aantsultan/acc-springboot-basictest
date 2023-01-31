package com.springboot.basictest.userservice.service;

import com.springboot.basictest.userservice.dto.UserResponseDto;
import com.springboot.basictest.userservice.dto.UserSettingRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserSettingService {
    ResponseEntity<UserResponseDto> update(int id, List<UserSettingRequestDto> userSettingRequestDtoList);
}
