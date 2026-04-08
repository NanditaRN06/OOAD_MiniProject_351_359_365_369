package com.ooadproject.capstone_project_sharing_platform.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String message;
    private UserResponse user;
}