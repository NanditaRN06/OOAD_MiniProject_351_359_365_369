package com.ooadproject.capstone_project_sharing_platform.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "faculty")
@PrimaryKeyJoinColumn(name = "user_id")
public class Faculty extends User {

    private String designation;
    private String department;

    // Operations as requested
    public void viewProjects() {
        // Logic for viewing projects (can be handled by SearchService/ProjectService later)
    }

    public void commentOnProjects() {
        // Logic for commenting on projects (can be handled by CommentService/FeedbackService later)
    }
}
