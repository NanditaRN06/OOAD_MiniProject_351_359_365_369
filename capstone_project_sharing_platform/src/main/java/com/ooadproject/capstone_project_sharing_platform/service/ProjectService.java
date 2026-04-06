package com.ooadproject.capstone_project_sharing_platform.service;

import com.ooadproject.capstone_project_sharing_platform.dto.request.ProjectRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.ProjectResponse;
import java.util.List;

public interface ProjectService {

	ProjectResponse createProject(ProjectRequest request);

	List<ProjectResponse> getAllProjects();

	ProjectResponse getProjectById(Long id);

	ProjectResponse updateProject(Long id, ProjectRequest request);

	void deleteProject(Long id);
}
