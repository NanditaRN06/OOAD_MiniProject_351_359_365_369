package com.ooadproject.capstone_project_sharing_platform.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ooadproject.capstone_project_sharing_platform.dto.response.ProjectResponse;
import com.ooadproject.capstone_project_sharing_platform.entity.Project;
import com.ooadproject.capstone_project_sharing_platform.repository.ProjectRepository;
import com.ooadproject.capstone_project_sharing_platform.service.SearchService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final ProjectRepository projectRepository;

    @Override
    public List<ProjectResponse> searchProjects(String keyword) {
        List<Project> projects;
        if (keyword == null || keyword.trim().isEmpty()) {
            projects = projectRepository.findAll();
        } else {
            projects = projectRepository.searchProjects(keyword);
        }

        return projects.stream().map(this::mapToResponse).toList();
    }

    private ProjectResponse mapToResponse(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getDomain(),
                project.getTechnologies(),
                project.getStatus());
    }
}
