package com.ooadproject.capstone_project_sharing_platform.controller;


import org.springframework.web.bind.annotation.*;

import com.ooadproject.capstone_project_sharing_platform.service.CommentService;
import com.ooadproject.capstone_project_sharing_platform.dto.request.CommentRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.CommentResponse;
import org.springframework.http.ResponseEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponse> addComment(@RequestBody CommentRequest request) {
        return ResponseEntity.ok(commentService.addComment(request));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Long projectId) {
        return ResponseEntity.ok(commentService.getCommentsByProject(projectId));
    }
}