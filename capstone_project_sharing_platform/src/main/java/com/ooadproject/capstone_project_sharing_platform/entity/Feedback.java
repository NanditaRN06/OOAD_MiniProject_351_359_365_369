package com.ooadproject.capstone_project_sharing_platform.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String feedbackId;

    @Column(nullable = false)
    private int relevance;

    @Column(nullable = false)
    private String gradingPattern;

    @Column(nullable = false)
    private String resources;

    @Column(nullable = false)
    private LocalDateTime dateSubmitted;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DifficultyLevel difficulty;

    @ManyToOne(optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
}
