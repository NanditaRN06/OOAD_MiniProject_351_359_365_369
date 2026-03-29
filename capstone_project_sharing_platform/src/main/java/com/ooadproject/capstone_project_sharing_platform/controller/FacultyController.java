package com.ooadproject.capstone_project_sharing_platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ooadproject.capstone_project_sharing_platform.service.FacultyService;
import com.ooadproject.capstone_project_sharing_platform.dto.request.FacultyRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.FacultyResponse;

/* 
 * GRASP Principle: Controller
 * This class acts as the Controller in the GRASP (General Responsibility Assignment Software Patterns).
 * Responsibility: It is the first object beyond the UI layer that receives and coordinates system operations.
 * It delegates complex business logic (like updating database) to the FacultyService (Information Expert) 
 * and handles the redirection/navigation flow.
 */
@Controller
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired // Singleton instance managed by Spring
    private FacultyService facultyService;

    @GetMapping("/dashboard")
    public String dashboard(@RequestParam String email, Model model) {
        FacultyResponse faculty = facultyService.getFacultyDetails(email);
        
        if (faculty == null) {
            return "redirect:/auth/login";
        }
        
        model.addAttribute("faculty", faculty);
        return "faculty-dashboard";
    }

    @PostMapping("/update")
    public String updateDetails(@RequestParam String email,
                                @RequestParam String designation,
                                @RequestParam String department) {
                                
        FacultyRequest request = FacultyRequest.builder()
                .designation(designation)
                .department(department)
                .build();
                
        facultyService.updateFacultyDetails(email, request);
        
        return "redirect:/faculty/dashboard?email=" + email + "&success=true";
    }

    /* Operations placeholders */
    @GetMapping("/view-projects")
    public String viewProjects() {
        // Coordination logic for system operation
        return "projects-list"; 
    }

    @PostMapping("/comment")
    public String commentOnProjects() {
        // Coordination logic for system operation
        return "redirect:/faculty/dashboard";
    }
}
