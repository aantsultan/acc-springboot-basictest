package com.springboot.basictest.userservice.repository;

import com.springboot.basictest.userservice.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    void findUserBySsnTest(){
        UserEntity user = repository.findUserBySsn("123");
        assertNull(user);
    }
}
