package com.czk.dao;

import com.czk.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserDao {
    /**
     * 查找用户是否存在
     * @param user
     * @return
     */
    @Select("select * from user where username = #{username} and password = #{password}")
    User selectUser(User user);

    @Select("select * from user where email = #{email}")
    User selectUserByEmail(String email);

    @Select("select * from user where username = #{username}")
    User selectUserByUserName(String username);

    @Insert("insert into user(username, password, email) VALUES(#{username},#{password},#{email})")
    int addUser(String username, String password, String email);

    @Update("update user set headpath = #{headPath} where username = #{username} and password = #{password}")
    int upDataUserHeadPath(User user);

    @Update("update user set password = #{password} where username = #{username}")
    int changePassword(String username, String password);
}
