package com.ooadproject.capstone_project_sharing_platform.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ooadproject.capstone_project_sharing_platform.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}