package com.company.community.community.mapper;

import com.company.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {

    @Insert("INSERT INTO question values(null,#{title},#{description},#{gmtCreate}," +
            "#{gmtModified},#{creatorId},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    void publishQuestion(Question question);

}
