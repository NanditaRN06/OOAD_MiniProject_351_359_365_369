package com.ooadproject.capstone_project_sharing_platform.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}