package com.sermaluc.challenge.repository;

import com.sermaluc.challenge.entity.Phone;
import com.sermaluc.challenge.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    static final String NAME 		= "Sebastian Contreras";
    static final String EMAIL 		= "sebas123@gmail.com";
    static final String PASSWORD 	= "password123";
    static final String TOKEN 		= "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhdXRoZW50aWNhdGlvbiIsIm5hbWUiOiJDZXNhciIsImVtYWlsIjoiYWJjMTFAaG90bWFpbC5jb20iLCJleHAiOjE3MDEyNDU1MDR9.eE6XJbd5rWU6c26F43lZJXytP3IcFumM4dungkml1dE";
    static final Collection<Phone> PHONES = Collections.emptyList();


    @Test
    void createNewUser() {
        User user = new User(NAME, EMAIL, PASSWORD, TOKEN, PHONES);
        user  = userRepository.save(user);

        Assertions.assertNotNull(user.getId());
        Assertions.assertNotNull(user.getCreated());
        Assertions.assertNull(user.getModified());
        Assertions.assertNotNull(user.getToken());
        Assertions.assertTrue(user.getIsActive());

    }

    @Test
    void disableUser() {
        User user = new User(NAME, EMAIL, PASSWORD, TOKEN, PHONES);
        user  = userRepository.save(user);

        user.setIsActive(Boolean.FALSE);
        user  = userRepository.saveAndFlush(user);

        Assertions.assertNotNull(user.getModified());
        Assertions.assertFalse(user.getIsActive());
    }

    @Test
    void updateLastLogin() {
        User user = new User(NAME, EMAIL, PASSWORD, TOKEN, PHONES);
        user  = userRepository.save(user);

        user.setLast_login(LocalDateTime.now());
        user  = userRepository.save(user);

        Assertions.assertNotNull(user.getLast_login());
    }
}
