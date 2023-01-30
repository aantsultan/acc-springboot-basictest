package com.springboot.basictest.userservice.service;

import com.springboot.basictest.userservice.entity.UserEntity;
import com.springboot.basictest.userservice.entity.UserSettingEntity;

public interface UserSettingService {
    UserEntity update(int id, UserSettingEntity userSettingEntity);
}
