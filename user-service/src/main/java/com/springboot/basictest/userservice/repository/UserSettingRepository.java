package com.springboot.basictest.userservice.repository;

import com.springboot.basictest.userservice.entity.UserSettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSettingRepository extends JpaRepository<UserSettingEntity, Integer> {

}
