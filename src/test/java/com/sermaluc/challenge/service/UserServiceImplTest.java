package com.sermaluc.challenge.service;

import com.sermaluc.challenge.entity.Phone;
import com.sermaluc.challenge.entity.User;
import com.sermaluc.challenge.repository.PhoneRepository;
import com.sermaluc.challenge.repository.UserRepository;
import com.sermaluc.challenge.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class UserServiceImplTest {

    static final String NAME 		= "Sebastian Contreras";
    static final String EMAIL 		= "sebas123@gmail.com";
    static final String PASSWORD 	= "password123";
    static final String TOKEN 		= "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhdXRoZW50aWNhdGlvbiIsIm5hbWUiOiJDZXNhciIsImVtYWlsIjoiYWJjMTFAaG90bWFpbC5jb20iLCJleHAiOjE3MDEyNDU1MDR9.eE6XJbd5rWU6c26F43lZJXytP3IcFumM4dungkml1dE";
    static final Collection<Phone> PHONES = Collections.emptyList();

    static final String NUMBER = "976433032";
    static final String CITY_CODE = "10";
    static final String COUNTRY_CODE = "+51";

    @Autowired
    UserRepository userRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @Test
    void getAllUsersEmpty() throws Exception {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        PhoneRepository phoneRepository = Mockito.mock(PhoneRepository.class);

        Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>());

        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.setPhoneRepository(phoneRepository);
        userServiceImpl.setUserRepository(userRepository);

        List<User> users = userServiceImpl.findAllUser();

        Assertions.assertTrue(users.isEmpty());
    }

    @Test
    void getAllUsers() throws Exception {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        PhoneRepository phoneRepository = Mockito.mock(PhoneRepository.class);

        User user = new User(NAME, EMAIL, PASSWORD, TOKEN, PHONES);

        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.setPhoneRepository(phoneRepository);
        userServiceImpl.setUserRepository(userRepository);

        List<User> users = userServiceImpl.findAllUser();

        Assertions.assertFalse(users.isEmpty());
    }
    
}
