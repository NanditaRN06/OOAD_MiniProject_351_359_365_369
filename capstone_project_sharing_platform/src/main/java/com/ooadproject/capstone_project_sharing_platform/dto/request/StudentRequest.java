package com.ooadproject.capstone_project_sharing_platform.dto.request;

import lombok.*;
/*
 * Design Pattern: Builder Pattern
 * 
 * Lombok's @Builder is used to construct StudentRequest objects.
 * This improves readability and avoids constructor overloading issues.
 * It is especially useful when creating objects with multiple fields.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRequest {
    private String usn;
    private String branch;
}