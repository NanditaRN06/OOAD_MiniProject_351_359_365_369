//Grasp-Inforation Expert
package com.ooadproject.capstone_project_sharing_platform.service;

import com.ooadproject.capstone_project_sharing_platform.entity.User;
import com.ooadproject.capstone_project_sharing_platform.dto.request.RegisterRequest;

public interface UserService {
   User registerUser(RegisterRequest request);
    User loginUser(String email, String password);
}