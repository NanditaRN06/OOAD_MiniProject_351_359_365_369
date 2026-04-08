package com.ooadproject.capstone_project_sharing_platform.controller;

import com.ooadproject.capstone_project_sharing_platform.dto.request.ProjectRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.ProjectResponse;
import com.ooadproject.capstone_project_sharing_platform.service.CommentService;
import com.ooadproject.capstone_project_sharing_platform.service.ProjectService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
	private final CommentService commentService;
    // ============================
    // 🌐 UI METHODS (Thymeleaf)
    // ============================

    // ✅ Show form to create project
    @GetMapping("/create")
    public String showCreateForm(@RequestParam String email, Model model) {
        model.addAttribute("project", new ProjectRequest());
        model.addAttribute("email", email); // ✅ IMPORTANT
        return "create-project";
    }

    // ✅ Handle form submission
    @PostMapping("/create")
    public String createProjectFromForm(@ModelAttribute ProjectRequest request) {
        projectService.createProject(request);

        // ✅ redirect WITH email
        return "redirect:/projects/view?email=" + request.getEmail();
    }

    // ✅ View all projects (UI)
    @GetMapping("/view")
    public String viewProjects(@RequestParam String email, Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        model.addAttribute("email", email); // ✅ REQUIRED for comments
        return "projects-list";
    }
	@GetMapping("/{id}")
public String viewProjectDetails(@PathVariable Long id,
                                @RequestParam String email,
                                Model model) {

    ProjectResponse project = projectService.getProjectById(id);
    model.addAttribute("project", project);

    model.addAttribute("comments", commentService.getCommentsByProject(id));
    model.addAttribute("email", email);

    return "project-details";
}

    // ============================
    // 🔗 REST API METHODS (Optional)
    // ============================

    @PostMapping("/api")
    @ResponseBody
    public ProjectResponse createProject(@RequestBody ProjectRequest request) {
        return projectService.createProject(request);
    }

    @GetMapping("/api")
    @ResponseBody
    public List<ProjectResponse> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ProjectResponse getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public ProjectResponse updateProject(@PathVariable Long id,
                                         @RequestBody ProjectRequest request) {
        return projectService.updateProject(id, request);
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}