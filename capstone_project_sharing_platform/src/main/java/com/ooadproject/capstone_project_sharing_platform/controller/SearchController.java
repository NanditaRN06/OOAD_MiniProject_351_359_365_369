package com.ooadproject.capstone_project_sharing_platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

import com.ooadproject.capstone_project_sharing_platform.dto.response.ProjectResponse;
import com.ooadproject.capstone_project_sharing_platform.service.SearchService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    // View for Thymeleaf UI
    @GetMapping
    public String searchView(@RequestParam(required = false) String keyword,
                             @RequestParam String email, 
                             Model model) {
        List<ProjectResponse> projects = searchService.searchProjects(keyword);
        model.addAttribute("projects", projects);
        model.addAttribute("keyword", keyword);
        model.addAttribute("email", email);
        return "projects-list"; // re-use the projects-list template
    }

    // API Endpoint
    @GetMapping("/api")
    @ResponseBody
    public List<ProjectResponse> searchApi(@RequestParam String keyword) {
        return searchService.searchProjects(keyword);
    }
}
