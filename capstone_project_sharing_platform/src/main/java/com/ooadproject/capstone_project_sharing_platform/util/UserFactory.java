package com.ooadproject.capstone_project_sharing_platform.util;

import com.ooadproject.capstone_project_sharing_platform.dto.request.RegisterRequest;
import com.ooadproject.capstone_project_sharing_platform.entity.User;
import com.ooadproject.capstone_project_sharing_platform.entity.UserRole;

public class UserFactory {

    public static User createUser(RegisterRequest request) {
        UserRole role = UserRole.valueOf(request.getRole().toUpperCase());
        
        User user;
        if (role == UserRole.FACULTY) {
            user = new com.ooadproject.capstone_project_sharing_platform.entity.Faculty();
        } else {
            user = new User();
        }

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(role);

        return user;
    }
}