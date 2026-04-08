package com.ooadproject.capstone_project_sharing_platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ooadproject.capstone_project_sharing_platform.dto.request.CommentRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.request.StudentRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.StudentResponse;
import com.ooadproject.capstone_project_sharing_platform.service.CommentService;
import com.ooadproject.capstone_project_sharing_platform.service.ProjectService;
import com.ooadproject.capstone_project_sharing_platform.service.StudentService;
import com.ooadproject.capstone_project_sharing_platform.service.FeedbackService;
import com.ooadproject.capstone_project_sharing_platform.service.SubjectService;
import com.ooadproject.capstone_project_sharing_platform.dto.request.FeedbackRequest;
import com.ooadproject.capstone_project_sharing_platform.entity.DifficultyLevel;
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
    @Autowired
    private ProjectService projectService;
    @GetMapping("/view-projects")
    public String viewProjects(@RequestParam String email, Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        model.addAttribute("email", email);
        return "projects-list";
    }

    @Autowired
    private CommentService commentService;
    @PostMapping("/comment")
    public String addComment(@RequestParam String email,
                         @RequestParam Long projectId,
                         @RequestParam String content) {

    CommentRequest request = new CommentRequest();
    request.setEmail(email);
    request.setProjectId(projectId);
    request.setContent(content);

    commentService.addComment(request);

    return "redirect:/projects/" + projectId + "?email=" + email;
}

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/feedback/submit")
    public String feedbackForm(@RequestParam String email, Model model) {
        model.addAttribute("email", email);
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "submit-feedback";
    }

    @PostMapping("/feedback")
    public String submitFeedback(@RequestParam String email,
                                 @RequestParam Long subjectId,
                                 @RequestParam int relevance,
                                 @RequestParam String gradingPattern,
                                 @RequestParam String resources,
                                 @RequestParam DifficultyLevel difficulty) {
        FeedbackRequest request = FeedbackRequest.builder()
                .email(email)
                .subjectId(subjectId)
                .relevance(relevance)
                .gradingPattern(gradingPattern)
                .resources(resources)
                .difficulty(difficulty)
                .build();
        feedbackService.submitFeedback(request);
        return "redirect:/student/dashboard?email=" + email + "&success=true";
    }
}