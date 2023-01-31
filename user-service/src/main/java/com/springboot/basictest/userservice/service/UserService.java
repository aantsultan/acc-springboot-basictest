package com.springboot.basictest.userservice.service;

import com.springboot.basictest.userservice.dto.ListUserResponseDto;
import com.springboot.basictest.userservice.dto.UserRequestDto;
import com.springboot.basictest.userservice.dto.UserResponseDto;
import com.springboot.basictest.userservice.entity.UserEntity;

import java.util.List;

public interface UserService {
    ListUserResponseDto findAll();
    UserResponseDto findById(int id);
    UserResponseDto save(UserRequestDto userRequestDto);
    UserResponseDto update(int id, UserRequestDto userRequestDto);
    void deleteById(int id);
    UserResponseDto refresh(int id);
}
