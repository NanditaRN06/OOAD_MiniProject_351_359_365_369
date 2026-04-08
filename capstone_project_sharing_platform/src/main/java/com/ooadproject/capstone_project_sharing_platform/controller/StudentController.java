package com.ooadproject.capstone_project_sharing_platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ooadproject.capstone_project_sharing_platform.dto.request.StudentRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.StudentResponse;
import com.ooadproject.capstone_project_sharing_platform.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/dashboard")
    public String dashboard(@RequestParam String email, Model model) {

        StudentResponse student = studentService.getStudentDetails(email);

        if (student == null) {
            return "redirect:/auth/login";
        }

        model.addAttribute("student", student);
        return "student-dashboard";
    }

    @PostMapping("/update")
    public String updateDetails(@RequestParam String email,
                                @RequestParam String usn,
                                @RequestParam String branch) {
        /*
 * Builder Pattern Usage:
 * Creating StudentRequest object using builder for better readability
 * and maintainability.
 */
        StudentRequest request = StudentRequest.builder()
                .usn(usn)
                .branch(branch)
                .build();

        studentService.updateStudentDetails(email, request);

        return "redirect:/student/dashboard?email=" + email + "&success=true";
    }

    @GetMapping("/view-projects")
    public String viewProjects() {
        return "projects-list";
    }
}