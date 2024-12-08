package com.forohub.user.repository;

import com.forohub.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User, Long> {
}
