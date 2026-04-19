package com.ooadproject.capstone_project_sharing_platform.service;

import java.util.List;
import com.ooadproject.capstone_project_sharing_platform.dto.response.ProjectResponse;

public interface SearchService {
    List<ProjectResponse> searchProjects(String keyword);
}
