package com.ooadproject.capstone_project_sharing_platform.adapter;

import com.ooadproject.capstone_project_sharing_platform.entity.Comment;
import com.ooadproject.capstone_project_sharing_platform.entity.CommentType;

public class CommentAdapterFactory {

    public static CommentAdapter getAdapter(Comment comment) {

        if (comment.getType() == CommentType.RATING) {
            return new RatingCommentAdapter(comment);
        }

        return new TextCommentAdapter(comment);
    }
}