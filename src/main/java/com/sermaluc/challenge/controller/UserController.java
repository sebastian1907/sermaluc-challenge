package com.sermaluc.challenge.controller;

import com.sermaluc.challenge.entity.User;
import com.sermaluc.challenge.service.UserService;
import com.sermaluc.challenge.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userService.findAllUser();
        if (!users.isEmpty()){
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody UserVO userVO) {
        User user = userService.saveUser(userVO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
