package com.ooadproject.capstone_project_sharing_platform.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacultyResponse {
    private Long id;
    private String name;
    private String email;
    private String role;
    private String designation;
    private String department;
}
