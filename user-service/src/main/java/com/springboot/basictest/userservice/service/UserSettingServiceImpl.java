package com.springboot.basictest.userservice.service;

import com.springboot.basictest.userservice.entity.UserEntity;
import com.springboot.basictest.userservice.entity.UserSettingEntity;
import org.springframework.stereotype.Service;

@Service
public class UserSettingServiceImpl implements UserSettingService {
    @Override
    public UserEntity update(int id, UserSettingEntity userSettingEntity) {
        return null;
    }
}
