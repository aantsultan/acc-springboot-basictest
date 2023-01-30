package com.springboot.basictest.userservice.service;

import com.springboot.basictest.userservice.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> findAll();
    UserEntity findById(int id);
    UserEntity save(UserEntity userEntity);
    UserEntity update(int id, UserEntity userEntity);
    void deleteById(int id);
    UserEntity refresh(int id);
}
