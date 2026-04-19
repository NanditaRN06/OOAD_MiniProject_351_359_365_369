package com.ooadproject.capstone_project_sharing_platform.adapter;

import com.ooadproject.capstone_project_sharing_platform.dto.response.CommentResponse;
import com.ooadproject.capstone_project_sharing_platform.entity.Comment;

public class TextCommentAdapter implements CommentAdapter {

    private final Comment comment;

    public TextCommentAdapter(Comment comment) {
        this.comment = comment;
    }

    @Override
    public CommentResponse getFormattedComment() {
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .userName(comment.getUser().getName())
                .email(comment.getUser().getEmail()) 
                .projectId(comment.getProject().getId())
                .build();
    }
}