package com.company.community.community.mapper;

import com.company.community.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}