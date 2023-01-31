package com.springboot.basictest.userservice.service;

import com.springboot.basictest.userservice.dto.ListUserResponseDto;
import com.springboot.basictest.userservice.dto.UserRequestDto;
import com.springboot.basictest.userservice.dto.UserResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<ListUserResponseDto> findAll();
    ResponseEntity<UserResponseDto> findById(int id);
    ResponseEntity<UserResponseDto> save(UserRequestDto userRequestDto);
    ResponseEntity<UserResponseDto> update(int id, UserRequestDto userRequestDto);
    void deleteById(int id);
    ResponseEntity<UserResponseDto> refresh(int id);
}
