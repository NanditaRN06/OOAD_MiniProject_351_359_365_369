package com.ooadproject.capstone_project_sharing_platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ooadproject.capstone_project_sharing_platform.dto.request.RegisterRequest;
import com.ooadproject.capstone_project_sharing_platform.entity.User;
import com.ooadproject.capstone_project_sharing_platform.entity.UserRole;
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

    /*
     * GRASP Principle: Controller
     * This method handles login request and delegates authentication logic
     * to UserService (Information Expert).
     * It also handles navigation flow based on user role.
     */
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        org.springframework.ui.Model model) {

        try {
            User user = userService.loginUser(email, password);

            if (user.getRole() == UserRole.FACULTY) {
                return "redirect:/faculty/dashboard?email=" + email;
            } else if (user.getRole() == UserRole.STUDENT) {
                return "redirect:/student/dashboard?email=" + email;
            }

            return "success";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "login";  // ✅ stays on login page
        }
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/auth/login";
    }

    @Autowired
    private com.ooadproject.capstone_project_sharing_platform.repository.UserRepository userRepository;

    @GetMapping("/dashboard")
    public String dashboard(@RequestParam String email) {
        return userRepository.findByEmail(email).map(user -> {
            if (user.getRole() == UserRole.FACULTY) {
                return "redirect:/faculty/dashboard?email=" + email;
            }
            return "redirect:/student/dashboard?email=" + email;
        }).orElse("redirect:/auth/login");
    }
}