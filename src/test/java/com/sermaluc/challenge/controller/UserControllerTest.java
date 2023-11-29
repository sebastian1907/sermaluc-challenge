package com.sermaluc.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sermaluc.challenge.entity.Phone;
import com.sermaluc.challenge.entity.User;
import com.sermaluc.challenge.service.UserService;
import com.sermaluc.challenge.vo.PhoneVO;
import com.sermaluc.challenge.vo.UserVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    static final String NAME 		= "Sebastian Contreras";
    static final String EMAIL 		= "sebas123@gmail.com";
    static final String PASSWORD 	= "password123";
    static final String TOKEN 		= "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhdXRoZW50aWNhdGlvbiIsIm5hbWUiOiJDZXNhciIsImVtYWlsIjoiYWJjMTFAaG90bWFpbC5jb20iLCJleHAiOjE3MDEyNDU1MDR9.eE6XJbd5rWU6c26F43lZJXytP3IcFumM4dungkml1dE";
    static final Collection<Phone> PHONES = Collections.emptyList();

    static final String NUMBER = "976433032";
    static final String CITY_CODE = "10";
    static final String COUNTRY_CODE = "+51";

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void createUser_success() throws Exception {

        PhoneVO phoneVO = new PhoneVO(NUMBER, CITY_CODE, COUNTRY_CODE);

        UserVO userVO = new UserVO();
        userVO.setEmail(EMAIL);
        userVO.setName(NAME);
        userVO.setPassword(PASSWORD);
        userVO.setPhones(Arrays.asList(phoneVO));

        User user = new User(NAME, EMAIL, PASSWORD, TOKEN, PHONES);

        given(userService.saveUser(userVO)).willReturn(user);

        this.mockMvc
                .perform(
                        post("/api/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(userVO))
                )
                .andExpect(status().isCreated());
    }

    @Test
    void getAllUser_success() throws Exception {

        PhoneVO phoneVO = new PhoneVO(NUMBER, CITY_CODE, COUNTRY_CODE);

        UserVO userVO = new UserVO();
        userVO.setEmail(EMAIL);
        userVO.setName(NAME);
        userVO.setPassword(PASSWORD);
        userVO.setPhones(Arrays.asList(phoneVO));

        User user = new User(NAME, EMAIL, PASSWORD, TOKEN, PHONES);

        given(userService.saveUser(userVO)).willReturn(user);

        given(userService.findAllUser()).willReturn(Arrays.asList(user));

        this.mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllUser_empty_success() throws Exception {

        given(userService.findAllUser()).willReturn(new ArrayList<>());

        this.mockMvc.perform(get("/api/users"))
                .andExpect(status().isNoContent());
    }
}
