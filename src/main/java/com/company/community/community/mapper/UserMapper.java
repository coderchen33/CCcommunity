package com.company.community.community.mapper;

import com.company.community.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user values (null,#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insertUser(User user);

    @Select("SELECT * FROM user WHERE token = #{token} ")
    User findByToken(@Param("token") String token);

    @Select("SELECT * FROM user WHERE id = #{creatorId}")
    User selectById(@Param("creatorId") Integer creatorId);

    @Select("SELECT * FROM user WHERE account_Id = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("UPDATE user SET name = #{name},token = #{token},gmt_modified = #{gmtModified},avatar_url = #{avatarUrl} WHERE id = #{id}")
    void update(User user);

}
