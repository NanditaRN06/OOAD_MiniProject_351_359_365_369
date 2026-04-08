package com.ooadproject.capstone_project_sharing_platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ooadproject.capstone_project_sharing_platform.dto.request.FacultyRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.FacultyResponse;
import com.ooadproject.capstone_project_sharing_platform.entity.Faculty;
import com.ooadproject.capstone_project_sharing_platform.repository.FacultyRepository;
import com.ooadproject.capstone_project_sharing_platform.service.FacultyService;

@Service // Singleton design pattern is implicitly handled by Spring container
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public FacultyResponse getFacultyDetails(String email) {
        Faculty faculty = facultyRepository.findByEmail(email)
                .orElse(null);
        
        if (faculty == null) {
            // If faculty record doesn't exist yet but user is FACULTY, create it or handle error
            // As per requirements: "doesn't own projects → just views all"
            return null;
        }

        return mapToResponse(faculty);
    }

    @Override
    public FacultyResponse updateFacultyDetails(String email, FacultyRequest request) {
        Faculty faculty = facultyRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));

        faculty.setDesignation(request.getDesignation());
        faculty.setDepartment(request.getDepartment());

        facultyRepository.save(faculty);

        return mapToResponse(faculty);
    }

    private FacultyResponse mapToResponse(Faculty faculty) {
        return FacultyResponse.builder()
                .id(faculty.getId())
                .name(faculty.getName())
                .email(faculty.getEmail())
                .role(faculty.getRole().name())
                .designation(faculty.getDesignation())
                .department(faculty.getDepartment())
                .build();
    }
}
