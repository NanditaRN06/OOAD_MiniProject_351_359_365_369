package com.ooadproject.capstone_project_sharing_platform.dto.response;

import com.ooadproject.capstone_project_sharing_platform.entity.DifficultyLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackResponse {
    private String feedbackId;
    private Long subjectId;
    private String subjectName;
    private String studentName;
    private int relevance;
    private String gradingPattern;
    private String resources;
    private LocalDateTime dateSubmitted;
    private DifficultyLevel difficulty;
}
