package com.sermaluc.challenge.service;

import com.sermaluc.challenge.entity.User;
import com.sermaluc.challenge.vo.UserVO;

import java.util.List;

public interface UserService {

    List<User> findAllUser();
    User saveUser(UserVO userVO);
}
