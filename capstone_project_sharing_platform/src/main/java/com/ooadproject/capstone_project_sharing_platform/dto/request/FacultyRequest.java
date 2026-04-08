package com.ooadproject.capstone_project_sharing_platform.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacultyRequest {
    private String designation;
    private String department;
    
    // Optional: add fields from User if needed for generic creation,
    // but here we focus on the specialized attributes as per the user's dashboard logic.
}
