package com.springboot.basictest.userservice.service;

import com.springboot.basictest.userservice.entity.UserEntity;
import com.springboot.basictest.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity findById(int id) {
        return null;
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return null;
    }

    @Override
    public UserEntity update(int id, UserEntity userEntity) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public UserEntity refresh(int id) {
        return null;
    }
}
