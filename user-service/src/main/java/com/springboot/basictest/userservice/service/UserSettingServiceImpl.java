package com.springboot.basictest.userservice.service;

import com.springboot.basictest.userservice.dto.UserResponseDto;
import com.springboot.basictest.userservice.dto.UserSettingRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSettingServiceImpl implements UserSettingService {


    @Override
    public ResponseEntity<UserResponseDto> update(int id, List<UserSettingRequestDto> userSettingRequestDtoList) {
        return null;
    }
}
