package com.forohub.user.repository;

import com.forohub.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRespository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String username);
}
