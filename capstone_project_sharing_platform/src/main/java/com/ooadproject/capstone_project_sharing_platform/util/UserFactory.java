package com.ooadproject.capstone_project_sharing_platform.util;

import com.ooadproject.capstone_project_sharing_platform.dto.request.RegisterRequest;
import com.ooadproject.capstone_project_sharing_platform.entity.*;

public class UserFactory {

    public static User createUser(RegisterRequest request) {

        UserRole role = UserRole.valueOf(request.getRole().toUpperCase());

        User user;

        if (role == UserRole.FACULTY) {
            user = new Faculty();
        } 
        else if (role == UserRole.STUDENT) {
            user = new Student();   // 🔥 FIXED LINE
        } 
        else {
            throw new RuntimeException("Invalid role");
        }

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(role);

        return user;
    }
}