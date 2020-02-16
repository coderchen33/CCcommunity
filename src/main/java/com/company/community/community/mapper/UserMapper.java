package com.company.community.community.mapper;

import com.company.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user values (null,#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified})")
    void insertUser(User user);
}
