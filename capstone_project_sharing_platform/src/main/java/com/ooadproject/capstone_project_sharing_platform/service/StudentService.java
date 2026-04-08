package com.ooadproject.capstone_project_sharing_platform.service;

import com.ooadproject.capstone_project_sharing_platform.dto.request.StudentRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.StudentResponse;

public interface StudentService {

    StudentResponse getStudentDetails(String email);

    StudentResponse updateStudentDetails(String email, StudentRequest request);
}