package com.springboot.basictest.userservice.service;

import com.springboot.basictest.userservice.dto.ListUserResponseDto;
import com.springboot.basictest.userservice.dto.UserRequestDto;
import com.springboot.basictest.userservice.dto.UserResponseDto;
import com.springboot.basictest.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public ListUserResponseDto findAll() {
        return null;
    }

    @Override
    public UserResponseDto findById(int id) {
        return null;
    }

    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public UserResponseDto update(int id, UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public UserResponseDto refresh(int id) {
        return null;
    }
}
