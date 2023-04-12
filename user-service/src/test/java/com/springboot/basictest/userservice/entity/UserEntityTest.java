package com.springboot.basictest.userservice.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UserEntityTest {

    @Test
    void createEntity() {
        UserEntity user = new UserEntity();
        user.setFamilyName("Aant");
        user.setFirstName("Sultan");
        assertNotNull(user);
    }
}
