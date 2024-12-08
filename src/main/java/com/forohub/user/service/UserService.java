package com.forohub.user.service;

import com.forohub.user.dto.UserDtoCreate;
import com.forohub.user.dto.UserDtoMainData;
import com.forohub.user.dto.UserDtoUpdate;
import com.forohub.user.models.User;
import com.forohub.user.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRespository userRespository;

    public ResponseEntity<UserDtoMainData> getUserById(long id) {
        Optional<User> userOptional = userRespository.findById(id);
        return userOptional.map(user -> ResponseEntity.ok(new UserDtoMainData(user))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<UserDtoMainData> updateUser(long id, UserDtoUpdate data) {
        Optional<User> userOptional = userRespository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.update(data);
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
        userRespository.save(user);
        return ResponseEntity.ok(new UserDtoMainData(user));
    }
}
