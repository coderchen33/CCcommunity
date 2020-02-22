package com.company.community.community.mapper;

import com.company.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("INSERT INTO question values(null,#{title},#{description},#{gmtCreate}," +
            "#{gmtModified},#{creatorId},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    void publishQuestion(Question question);

    @Select("SELECT * FROM question LIMIT #{offset},#{size}")
    List<Question> selectAll(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("SELECT COUNT(1) FROM question")
    Integer count();

    @Select("SELECT * FROM question WHERE creator_id = #{userId} LIMIT #{offset},#{size}")
    List<Question> listbyUser(@Param("userId") Integer userId, @Param("offset") Integer offset, @Param("size") Integer size);

    @Select("SELECT COUNT(1) FROM question WHERE creator_id = #{userId}")
    Integer countbyUserId(@Param("userId") Integer userId);

    @Select("SELECT * FROM question WHERE id = #{questionId}")
    Question getQuestionById(@Param("questionId") Integer questionId);
}
