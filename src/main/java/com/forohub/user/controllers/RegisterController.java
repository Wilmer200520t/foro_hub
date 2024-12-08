package com.forohub.user.controllers;

import com.forohub.user.dto.UserDtoCreate;
import com.forohub.user.dto.UserDtoMainData;
import com.forohub.user.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<UserDtoMainData> register(@Validated @RequestBody UserDtoCreate data) {
        return userService.createUser(data);
    }
}
