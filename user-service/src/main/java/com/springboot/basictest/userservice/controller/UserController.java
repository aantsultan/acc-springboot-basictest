package com.springboot.basictest.userservice.controller;

import com.springboot.basictest.userservice.entity.UserEntity;
import com.springboot.basictest.userservice.entity.UserSettingEntity;
import com.springboot.basictest.userservice.service.UserService;
import com.springboot.basictest.userservice.service.UserSettingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserSettingService userSettingService;

    @GetMapping("/v1/users")
    public List<UserEntity> retrieveAllUser(){
        return userService.findAll();
    }

    @GetMapping("/v1/users/{id}")
    public UserEntity retrieveUser(@PathVariable int id){
        return userService.findById(id);
    }

    @PostMapping("/v1/users")
    public UserEntity createUser(@Valid @RequestBody UserEntity userEntity){
        return userService.save(userEntity);
    }

    @PutMapping("/v1/users/{id}")
    public UserEntity updateUser(@PathVariable int id, @RequestBody UserEntity userEntity){
        return userService.update(id, userEntity);
    }

    @PutMapping("/v1/users/{id}/settings")
    public UserEntity updateUserSetting(@PathVariable int id, @RequestBody UserSettingEntity userSettingEntity){
        return userSettingService.update(id, userSettingEntity);
    }

    @DeleteMapping("/v1/users/{id}")
    public void deleteById(@PathVariable int id){
        userService.deleteById(id);
    }

    @PutMapping("/v1/users/{id}/refresh")
    public UserEntity refresh(@PathVariable int id){
        return userService.refresh(id);
    }
}
