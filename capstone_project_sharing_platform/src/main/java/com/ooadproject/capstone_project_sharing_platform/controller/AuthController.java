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
            @RequestParam String password,
            org.springframework.ui.Model model) {

        com.ooadproject.capstone_project_sharing_platform.entity.User user = userService.loginUser(email, password);

        if (user.getRole() == com.ooadproject.capstone_project_sharing_platform.entity.UserRole.FACULTY) {
            return "redirect:/faculty/dashboard?email=" + email;
        }

        return "success";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/auth/login";
    }
}