package com.forohub.user.controllers;

import com.forohub.user.dto.UserDtoMainData;
import com.forohub.user.dto.UserDtoUpdate;
import com.forohub.user.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "bearer-key")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<UserDtoMainData> getUserById() {
        return userService.getUserById(userService.getUserIdAuthenticated());
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<UserDtoMainData> addUser(@RequestBody UserDtoUpdate data) {
        return userService.updateUser(userService.getUserIdAuthenticated(), data);
    }

    @DeleteMapping("/delete")
    @Transactional
    public ResponseEntity<?> deleteUser() {
        return userService.deleteUser(userService.getUserIdAuthenticated());
    }

    @DeleteMapping("/block")
    @Transactional
    public ResponseEntity<UserDtoMainData> blockUser() {
        return userService.blockUser(userService.getUserIdAuthenticated());
    }

}
