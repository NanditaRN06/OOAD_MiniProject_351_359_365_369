package com.ooadproject.capstone_project_sharing_platform.entity;

import jakarta.persistence.*;
import lombok.*;
/*
 * GRASP Principle: Information Expert
 * 
 * The Student class holds all student-related data (usn, branch).
 * Therefore, it is the appropriate place to define behaviors 
 * related to student operations such as viewing projects and commenting.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User {

    private String usn;
    private String branch;

    // Information Expert responsibility
    public void viewProjects() {
        // logic later via ProjectService
    }

    public void addComment() {
        // will be handled later (CommentService)
    }
}