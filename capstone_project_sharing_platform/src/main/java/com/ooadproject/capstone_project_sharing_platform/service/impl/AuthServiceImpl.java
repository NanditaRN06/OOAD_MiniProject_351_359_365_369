package com.ooadproject.capstone_project_sharing_platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ooadproject.capstone_project_sharing_platform.dto.request.LoginRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.request.RegisterRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.LoginResponse;
import com.ooadproject.capstone_project_sharing_platform.dto.response.UserResponse;
import com.ooadproject.capstone_project_sharing_platform.entity.User;
import com.ooadproject.capstone_project_sharing_platform.repository.UserRepository;
import com.ooadproject.capstone_project_sharing_platform.service.AuthService;
import com.ooadproject.capstone_project_sharing_platform.util.UserFactory;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    public LoginResponse register(RegisterRequest request) {

        User user = UserFactory.createUser(request);
        userRepository.save(user);

        return LoginResponse.builder()
                .message("User registered successfully")
                .user(mapToResponse(user))
                .build();
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return LoginResponse.builder()
                .message("Login successful")
                .user(mapToResponse(user))
                .build();
    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}