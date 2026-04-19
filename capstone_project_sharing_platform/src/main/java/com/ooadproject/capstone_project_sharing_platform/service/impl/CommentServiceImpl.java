package com.ooadproject.capstone_project_sharing_platform.service.impl;

import org.springframework.stereotype.Service;

import com.ooadproject.capstone_project_sharing_platform.dto.request.CommentRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.CommentResponse;
import com.ooadproject.capstone_project_sharing_platform.entity.Comment;
import com.ooadproject.capstone_project_sharing_platform.entity.CommentType;
import com.ooadproject.capstone_project_sharing_platform.entity.Project;
import com.ooadproject.capstone_project_sharing_platform.entity.User;
import com.ooadproject.capstone_project_sharing_platform.repository.CommentRepository;
import com.ooadproject.capstone_project_sharing_platform.repository.ProjectRepository;
import com.ooadproject.capstone_project_sharing_platform.repository.UserRepository;
import com.ooadproject.capstone_project_sharing_platform.service.CommentService;
import com.ooadproject.capstone_project_sharing_platform.adapter.CommentAdapterFactory;

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

        //  Fetch user
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch project
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // Handle type safely
        CommentType type = request.getType() != null
                ? CommentType.valueOf(request.getType().toUpperCase())
                : CommentType.TEXT;

        // 🏗 Create comment
        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setType(type);
        comment.setUser(user);
        comment.setProject(project);

        // ⭐ Only set rating if it's a rating comment
        if (type == CommentType.RATING) {

    Integer rating = request.getRating();

    if (rating == null) {
        throw new RuntimeException("Rating is required");
    }

    if (rating < 1 || rating > 5) {
        throw new RuntimeException("Rating must be between 1 and 5");
    }

    comment.setRating(rating);
}

        // 💾 Save
        Comment saved = commentRepository.save(comment);
       
        // 🔄 Use Adapter Pattern
        return CommentAdapterFactory
                .getAdapter(saved)
                .getFormattedComment();
    }

    @Override
    public List<CommentResponse> getCommentsByProject(Long projectId) {

        return commentRepository.findByProject_Id(projectId)
                .stream()
                .map(comment ->
                        CommentAdapterFactory
                                .getAdapter(comment)
                                .getFormattedComment()
                )
                .toList();
    }
@Override
public void deleteComment(Long commentId, String email) {

    Comment comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new RuntimeException("Comment not found"));

    // 🔐 Check ownership
    if (!comment.getUser().getEmail().equals(email)) {
        throw new RuntimeException("You can delete only your own comment");
    }

    commentRepository.deleteById(commentId);
}
}