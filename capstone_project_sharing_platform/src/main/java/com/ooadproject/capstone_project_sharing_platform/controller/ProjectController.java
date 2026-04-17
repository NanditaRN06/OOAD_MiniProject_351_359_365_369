package com.ooadproject.capstone_project_sharing_platform.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ooadproject.capstone_project_sharing_platform.dto.request.ProjectRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.ProjectResponse;
import com.ooadproject.capstone_project_sharing_platform.service.CommentService;
import com.ooadproject.capstone_project_sharing_platform.service.ProjectService;
import com.ooadproject.capstone_project_sharing_platform.service.ProjectService.ProjectOperation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * ProjectController with Command Pattern Support (Behavioral)
 * 
 * This controller implements the Command Pattern for project operations:
 * - Each project operation (Create, Update, Delete) can be executed as a command
 * - Commands are tracked in operation history for audit trail
 * - Supports operation monitoring and logging
 * 
 * Design Pattern: Command Pattern (Behavioral)
 * Benefits:
 * - Encapsulates operations as objects
 * - Enables operation tracking and audit trail
 * - Decouples caller from operation implementation
 * - Supports operation history and monitoring
 */
@Slf4j
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
    
    /* ===== COMMAND PATTERN: Operation Methods ===== */
    
    /**
     * Create project using Command Pattern
     * Encapsulates creation as a command operation
     */
    @PostMapping("/command/create")
    @ResponseBody
    public ProjectResponse createProjectCommand(@RequestBody ProjectRequest request) {
        log.info("Executing CREATE PROJECT command");
        ProjectOperation createCommand = () -> projectService.createProject(request);
        return projectService.executeOperation(createCommand);
    }
    
    /**
     * Update project using Command Pattern
     * Encapsulates update as a command operation with operation tracking
     */
    @PutMapping("/command/{id}")
    @ResponseBody
    public ProjectResponse updateProjectCommand(@PathVariable Long id,
                                               @RequestBody ProjectRequest request) {
        log.info("Executing UPDATE PROJECT command for ID: {}", id);
        ProjectOperation updateCommand = () -> projectService.updateProject(id, request);
        return projectService.executeOperation(updateCommand);
    }
    
    /**
     * Delete project using Command Pattern
     * Encapsulates deletion as a command operation with audit trail
     */
    @DeleteMapping("/command/{id}")
    @ResponseBody
    public void deleteProjectCommand(@PathVariable Long id) {
        log.info("Executing DELETE PROJECT command for ID: {}", id);
        ProjectOperation deleteCommand = () -> {
            projectService.deleteProject(id);
            return null;
        };
        projectService.executeOperation(deleteCommand);
    }
    
    /**
     * Get operation history
     * Returns audit trail of all executed project operations
     */
    @GetMapping("/api/history")
    @ResponseBody
    public List<String> getOperationHistory() {
        return projectService.getOperationHistory();
    }
    
    /**
     * Clear operation history
     * Clears the audit trail of executed operations
     */
    @DeleteMapping("/api/history")
    @ResponseBody
    public void clearOperationHistory() {
        projectService.clearOperationHistory();
    }
}