package com.ooadproject.capstone_project_sharing_platform.service;

import com.ooadproject.capstone_project_sharing_platform.dto.request.FacultyRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.FacultyResponse;

public interface FacultyService {
    FacultyResponse getFacultyDetails(String email);
    FacultyResponse updateFacultyDetails(String email, FacultyRequest request);
}
