package com.ooadproject.capstone_project_sharing_platform.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    // Who commented
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // On which project
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}