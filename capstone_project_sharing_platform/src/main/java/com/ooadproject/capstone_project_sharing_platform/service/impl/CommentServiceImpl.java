package com.ooadproject.capstone_project_sharing_platform.service.impl;


import org.springframework.stereotype.Service;

import com.ooadproject.capstone_project_sharing_platform.dto.request.CommentRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.CommentResponse;
import com.ooadproject.capstone_project_sharing_platform.entity.Comment;
import com.ooadproject.capstone_project_sharing_platform.repository.CommentRepository;
import com.ooadproject.capstone_project_sharing_platform.service.CommentService;
import com.ooadproject.capstone_project_sharing_platform.repository.UserRepository;
import com.ooadproject.capstone_project_sharing_platform.repository.ProjectRepository;
import com.ooadproject.capstone_project_sharing_platform.entity.User;
import com.ooadproject.capstone_project_sharing_platform.entity.Project;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Override
    public CommentResponse addComment(CommentRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setUser(user);
        comment.setProject(project);

        Comment saved = commentRepository.save(comment);

        return CommentResponse.builder()
                .id(saved.getId())
                .content(saved.getContent())
                .userName(user.getName())
                .projectId(project.getId())
                .build();
    }

    @Override
    public List<CommentResponse> getCommentsByProject(Long projectId) {

        return commentRepository.findByProject_Id(projectId)
                .stream()
                .map(c -> CommentResponse.builder()
                        .id(c.getId())
                        .content(c.getContent())
                        .userName(c.getUser().getName())
                        .projectId(c.getProject().getId())
                        .build())
                .toList();
    }
}