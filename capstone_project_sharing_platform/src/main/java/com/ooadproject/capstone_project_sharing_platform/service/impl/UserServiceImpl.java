package com.ooadproject.capstone_project_sharing_platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ooadproject.capstone_project_sharing_platform.dto.request.RegisterRequest;
import com.ooadproject.capstone_project_sharing_platform.entity.User;
import com.ooadproject.capstone_project_sharing_platform.util.UserFactory;
import com.ooadproject.capstone_project_sharing_platform.repository.UserRepository;
import com.ooadproject.capstone_project_sharing_platform.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
@Override
public User registerUser(RegisterRequest request) {

    User user = UserFactory.createUser(request);

    return userRepository.save(user);
}

    @Override
    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}