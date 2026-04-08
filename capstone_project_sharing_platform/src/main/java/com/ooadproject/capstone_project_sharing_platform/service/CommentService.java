package com.ooadproject.capstone_project_sharing_platform.service;

import com.ooadproject.capstone_project_sharing_platform.dto.request.CommentRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.CommentResponse;

import java.util.List;
public interface CommentService {
    CommentResponse addComment(CommentRequest request);
    List<CommentResponse> getCommentsByProject(Long projectId);
}