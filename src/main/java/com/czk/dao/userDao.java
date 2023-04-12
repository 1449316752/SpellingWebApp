package com.czk.dao;

import com.czk.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface userDao {
    /**
     * 查找用户是否存在
     * @param user
     * @return
     */
    @Select("select * from user where username = #{username} and password = #{password}")
    User selectUser(User user);


}
