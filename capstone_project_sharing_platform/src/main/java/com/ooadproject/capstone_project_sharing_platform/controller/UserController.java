package com.ooadproject.capstone_project_sharing_platform.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/test")
    public String test() {
        return "User Controller Working";
    }
}