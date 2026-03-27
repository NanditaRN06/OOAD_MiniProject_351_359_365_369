package com.ooadproject.capstone_project_sharing_platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.ooadproject.capstone_project_sharing_platform.dto.request.RegisterRequest;

import com.ooadproject.capstone_project_sharing_platform.service.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

   @PostMapping("/register")
public String register(@RequestParam String name,
                       @RequestParam String email,
                       @RequestParam String password,
                       @RequestParam String role) {

    RegisterRequest request = new RegisterRequest();
    request.setName(name);
    request.setEmail(email);
    request.setPassword(password);
    request.setRole(role);

    userService.registerUser(request);

    return "redirect:/auth/login";
}

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password) {

        userService.loginUser(email, password);

        return "success";
    }
}