package com.ooadproject.capstone_project_sharing_platform.dto.response;

import lombok.*;
/*
 * Design Pattern: Builder Pattern
 * 
 * Used to create immutable and flexible response objects.
 * Helps in mapping entity to DTO cleanly.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponse {
    private Long id;
    private String name;
    private String email;
    private String role;
    private String usn;
    private String branch;
}