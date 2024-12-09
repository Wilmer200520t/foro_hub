package com.forohub.user.service;

import com.forohub.general.infra.errors.ValidationException;
import com.forohub.user.dto.UserDtoCreate;
import com.forohub.user.dto.UserDtoMainData;
import com.forohub.user.dto.UserDtoUpdate;
import com.forohub.user.models.User;
import com.forohub.user.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRespository userRespository;

    private final PasswordEncoder passwordEncoder;

    public UserService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public ResponseEntity<UserDtoMainData> getUserById(long id) {
        Optional<User> userOptional = userRespository.findById(id);
        return userOptional.map(user -> ResponseEntity.ok(new UserDtoMainData(user))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<UserDtoMainData> updateUser(long id, UserDtoUpdate data) {
        Optional<User> userOptional = userRespository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.update(data);
            user.setPassword(encryptPassword(user.getPassword()));
            return ResponseEntity.ok(new UserDtoMainData(user));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> deleteUser(long id) {
        Optional<User> userOptional = userRespository.findById(id);
        if (userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        userRespository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<UserDtoMainData> blockUser(long id) {
        Optional<User> userOptional = userRespository.findById(id);
        if (userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        User user = userOptional.get();
        user.block();
        return ResponseEntity.ok(new UserDtoMainData(user));
    }

    public ResponseEntity<UserDtoMainData> createUser(UserDtoCreate data) {
        User user = new User(data);
        user.setPassword(encryptPassword(user.getPassword()));
        userRespository.save(user);
        return ResponseEntity.ok(new UserDtoMainData(user));
    }

    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public Long getUserIdAuthenticated(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();

            return user.getId();
        } catch (NumberFormatException e) {
            throw new ValidationException("Error processing registered user.");
        }
    }
}
