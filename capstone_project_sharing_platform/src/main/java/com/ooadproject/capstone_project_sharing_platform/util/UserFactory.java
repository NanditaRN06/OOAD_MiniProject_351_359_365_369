package com.ooadproject.capstone_project_sharing_platform.util;

import com.ooadproject.capstone_project_sharing_platform.dto.request.RegisterRequest;
import com.ooadproject.capstone_project_sharing_platform.entity.User;
import com.ooadproject.capstone_project_sharing_platform.entity.UserRole;

public class UserFactory {

    public static User createUser(RegisterRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(UserRole.valueOf(request.getRole().toUpperCase()));

        return user;
    }
}