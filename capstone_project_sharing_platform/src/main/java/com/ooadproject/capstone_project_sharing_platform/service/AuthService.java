package com.ooadproject.capstone_project_sharing_platform.service;

import com.ooadproject.capstone_project_sharing_platform.dto.request.LoginRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.request.RegisterRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.LoginResponse;

public interface AuthService {

    LoginResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}