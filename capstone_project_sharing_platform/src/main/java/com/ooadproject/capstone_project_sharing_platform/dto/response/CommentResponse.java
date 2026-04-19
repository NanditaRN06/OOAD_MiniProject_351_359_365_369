package com.ooadproject.capstone_project_sharing_platform.dto.response;

import lombok.*;

@Data
@Builder
public class CommentResponse {
    private Long id;
    private String content;
    private String userName;
    private Long projectId;
    private String email;
}