package com.ooadproject.capstone_project_sharing_platform.dto.request;

import com.ooadproject.capstone_project_sharing_platform.entity.DifficultyLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackRequest {
    private String email;
    private Long subjectId;
    private int relevance;
    private String gradingPattern;
    private String resources;
    private DifficultyLevel difficulty;
}
