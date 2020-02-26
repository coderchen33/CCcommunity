package com.company.community.community.mapper;

import com.company.community.community.model.Question;

public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
}