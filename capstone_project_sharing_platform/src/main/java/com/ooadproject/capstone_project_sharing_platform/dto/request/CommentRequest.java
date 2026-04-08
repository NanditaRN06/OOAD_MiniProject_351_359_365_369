package com.ooadproject.capstone_project_sharing_platform.dto.request;

import lombok.*;

@Data
public class CommentRequest {
    private String content;
    private Long projectId;
    private String email; // who is commenting
}