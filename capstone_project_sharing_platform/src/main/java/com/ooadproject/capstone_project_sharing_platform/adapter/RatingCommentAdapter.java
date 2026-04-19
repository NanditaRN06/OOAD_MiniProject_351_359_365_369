package com.ooadproject.capstone_project_sharing_platform.adapter;

import com.ooadproject.capstone_project_sharing_platform.dto.response.CommentResponse;
import com.ooadproject.capstone_project_sharing_platform.entity.Comment;

public class RatingCommentAdapter implements CommentAdapter {

    private final Comment comment;

    public RatingCommentAdapter(Comment comment) {
        this.comment = comment;
    }

    @Override
    public CommentResponse getFormattedComment() {

        String formatted = "Rating: " + comment.getRating();

        if (comment.getContent() != null && !comment.getContent().isEmpty()) {
            formatted += " - " + comment.getContent();
        }

        return CommentResponse.builder()
                .id(comment.getId())
                .content(formatted)
                .userName(comment.getUser().getName())
                .email(comment.getUser().getEmail()) 
                .projectId(comment.getProject().getId())
                .build();
    }
}