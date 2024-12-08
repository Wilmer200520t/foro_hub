package com.forohub.user.controllers;

import com.forohub.user.dto.UserDtoMainData;
import com.forohub.user.dto.UserDtoUpdate;
import com.forohub.user.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDtoMainData> getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDtoMainData> addUser(@PathVariable long id, @RequestBody UserDtoUpdate data) {
        return userService.updateUser(id, data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }

    @DeleteMapping("/block/{id}")
    @Transactional
    public ResponseEntity<UserDtoMainData> blockUser(@PathVariable long id) {
        return userService.blockUser(id);
    }

}
