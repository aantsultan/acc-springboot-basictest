package com.springboot.basictest.userservice.repository;

import com.springboot.basictest.userservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query(
            value = "SELECT * FROM user_details u WHERE u.ssn = ?1",
            nativeQuery = true
    )
    UserEntity findUserBySsn(String ssn);
}
