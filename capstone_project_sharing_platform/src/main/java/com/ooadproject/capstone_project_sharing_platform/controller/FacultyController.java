package com.ooadproject.capstone_project_sharing_platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ooadproject.capstone_project_sharing_platform.service.FacultyService;
import com.ooadproject.capstone_project_sharing_platform.service.ProjectService;
import com.ooadproject.capstone_project_sharing_platform.dto.request.FacultyRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.FacultyResponse;
import com.ooadproject.capstone_project_sharing_platform.dto.request.CommentRequest;
import com.ooadproject.capstone_project_sharing_platform.service.CommentService;
import com.ooadproject.capstone_project_sharing_platform.service.FeedbackService;

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

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CommentService commentService;

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

    @GetMapping("/view-projects")
    public String viewProjects(@RequestParam String email, Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        model.addAttribute("email", email);
        return "projects-list";
    }

    @PostMapping("/comment")
    public String comment(@RequestParam String email,
                          @RequestParam Long projectId,
                          @RequestParam String content) {

        CommentRequest request = new CommentRequest();
        request.setEmail(email);
        request.setProjectId(projectId);
        request.setContent(content);

        commentService.addComment(request);

        return "redirect:/faculty/view-projects?email=" + email;
    }

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/feedback")
    public String viewFeedback(@RequestParam String email, 
                               @RequestParam(required = false) Long subjectId, 
                               Model model) {
        if (subjectId != null) {
            model.addAttribute("feedbacks", feedbackService.viewSubjectFeedback(subjectId));
        } else {
            model.addAttribute("feedbacks", feedbackService.viewAllSubjectFeedback());
        }
        model.addAttribute("email", email);
        return "view-feedback";
    }
}