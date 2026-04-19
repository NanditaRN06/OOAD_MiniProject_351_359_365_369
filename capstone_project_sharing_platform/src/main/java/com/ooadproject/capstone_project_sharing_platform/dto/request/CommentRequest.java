package com.ooadproject.capstone_project_sharing_platform.dto.request;

import lombok.Data;

@Data
public class CommentRequest {
    private String content;
    private Integer rating;   
    private String type;      // "TEXT" or "RATING"
    private Long projectId;
    private String email;
}