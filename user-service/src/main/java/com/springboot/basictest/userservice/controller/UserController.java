package com.springboot.basictest.userservice.controller;

import com.springboot.basictest.userservice.dto.ListUserResponseDto;
import com.springboot.basictest.userservice.dto.UserRequestDto;
import com.springboot.basictest.userservice.dto.UserResponseDto;
import com.springboot.basictest.userservice.dto.UserSettingRequestDto;
import com.springboot.basictest.userservice.service.UserService;
import com.springboot.basictest.userservice.service.UserSettingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserSettingService userSettingService;

    @GetMapping("/v1/users")
    public ResponseEntity<ListUserResponseDto> retrieveAllUser(){
        return userService.findAll();
    }

    @GetMapping("/v1/users/{id}")
    public ResponseEntity<UserResponseDto> retrieveUser(@PathVariable int id){
        return userService.findById(id);
    }

    @PostMapping("/v1/users")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto){
        return userService.save(userRequestDto);
    }

    @PutMapping("/v1/users/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable int id, @RequestBody UserRequestDto userRequestDto){
        return userService.update(id, userRequestDto);
    }

    @PutMapping("/v1/users/{id}/settings")
    public ResponseEntity<UserResponseDto> updateUserSetting(@PathVariable int id, @RequestBody List<UserSettingRequestDto> userSettingRequestDtoList){
        return userSettingService.update(id, userSettingRequestDtoList);
    }

    @DeleteMapping("/v1/users/{id}")
    public void deleteById(@PathVariable int id){
        userService.deleteById(id);
    }

    @PutMapping("/v1/users/{id}/refresh")
    public ResponseEntity<UserResponseDto> refresh(@PathVariable int id){
        return userService.refresh(id);
    }
}
