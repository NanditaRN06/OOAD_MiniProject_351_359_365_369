package com.ooadproject.capstone_project_sharing_platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ooadproject.capstone_project_sharing_platform.dto.request.StudentRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.StudentResponse;
import com.ooadproject.capstone_project_sharing_platform.entity.Student;
import com.ooadproject.capstone_project_sharing_platform.repository.StudentRepository;
import com.ooadproject.capstone_project_sharing_platform.service.StudentService;
/*
 * GRASP Principle: Information Expert
 * 
 * StudentServiceImpl is responsible for handling business logic 
 * related to Student because it has access to StudentRepository.
 * 
 * It knows how to:
 * - Fetch student details
 * - Update student information
 * - Convert entity to DTO
 * 
 * Hence, it is the "Information Expert".
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentResponse getStudentDetails(String email) {
        Student student = studentRepository.findByEmail(email)
                .orElse(null);

        if (student == null) {
            return null;
        }

        return mapToResponse(student);
    }

    @Override
    public StudentResponse updateStudentDetails(String email, StudentRequest request) {

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setUsn(request.getUsn());
        student.setBranch(request.getBranch());

        studentRepository.save(student);

        return mapToResponse(student);
    }

    private StudentResponse mapToResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .role(student.getRole().name())
                .usn(student.getUsn())
                .branch(student.getBranch())
                .build();
    }
}